package UNITON.demo.login.service;

import UNITON.demo.login.dto.KakaoUserDto;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.entity.UserRole;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoOauthService {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    private final UserRepository userRepository;

    public KakaoUserDto getUserInfo(String code) {
        // 1. 토큰 요청
        String accessToken = getAccessToken(code);

        // 2. 사용자 정보 요청
        return getUserProfile(accessToken);
    }

    private String getAccessToken(String code) {
        WebClient webClient = WebClient.create();

        String response = webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters
                        .fromFormData("grant_type", "authorization_code")
                        .with("client_id", clientId)
                        .with("redirect_uri", redirectUri)
                        .with("code", code))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(response);
        return json.getString("access_token");
    }

    private KakaoUserDto getUserProfile(String accessToken) {
        WebClient webClient = WebClient.create();

        String userInfo = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(userInfo);
        JSONObject kakaoAccount = json.getJSONObject("kakao_account");
        JSONObject profile = kakaoAccount.getJSONObject("profile");

        return new KakaoUserDto(
                kakaoAccount.getString("email"),
                profile.getString("nickname"),
                profile.getString("profile_image_url")
        );
    }

    public UserEntity registerOrLogin(KakaoUserDto kakaoUser) {
        return userRepository.findByEmail(kakaoUser.getEmail())
                .orElseGet(() -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setEmail(kakaoUser.getEmail());
                    newUser.setNickname(kakaoUser.getNickname());
                    newUser.setRole(UserRole.USER);
                    return userRepository.save(newUser);
                });
    }
}
