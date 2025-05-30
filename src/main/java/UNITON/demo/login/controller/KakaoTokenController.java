package UNITON.demo.login.controller;

import UNITON.demo.login.dto.KakaoUserDto;
import UNITON.demo.login.entity.RefreshToken;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.service.KakaoOauthService;
import UNITON.demo.login.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/kakao")
@RequiredArgsConstructor
public class KakaoTokenController {

    private final KakaoOauthService kakaoOAuthService;
    private final RefreshTokenService refreshTokenService;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

     @GetMapping("/token")
     public ResponseEntity<?> getKakaoTokens(@RequestParam("code") String code) {

         WebClient webClient = WebClient.create();

        String responseBody = webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters
                        .fromFormData("grant_type", "authorization_code")
                        .with("client_id", clientId)
                        .with("redirect_uri", redirectUri)
                        .with("code", code)
                        .with("scope", "profile_nickname profile_image account_email"))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(responseBody);

        // JSON 구성
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", json.getString("access_token"));
        tokenMap.put("refresh_token", json.optString("refresh_token", null));
        tokenMap.put("expires_in", json.getInt("expires_in"));
        tokenMap.put("refresh_token_expires_in", json.optInt("refresh_token_expires_in", 0));
        tokenMap.put("scope", json.optString("scope", ""));
        tokenMap.put("token_type", json.getString("token_type"));

         KakaoUserDto kakaoUser = kakaoOAuthService.getUserInfo(code);

         // 2. DB 등록 or 기존 유저 반환
         UserEntity user = kakaoOAuthService.registerOrLogin(kakaoUser);
         RefreshToken refreshToken = refreshTokenService.createorUpdateRefreshToken(user.getId());
        return ResponseEntity.ok(tokenMap);
    }
}
