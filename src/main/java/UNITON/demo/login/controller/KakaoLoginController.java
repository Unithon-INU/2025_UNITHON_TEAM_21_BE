package UNITON.demo.login.controller;

import UNITON.demo.login.dto.KakaoUserDto;
import UNITON.demo.login.entity.RefreshToken;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.entity.UserRole;
import UNITON.demo.login.jwt.JWTUtil;
import UNITON.demo.login.service.KakaoOauthService;
import UNITON.demo.login.service.RefreshTokenService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/login/oauth")
public class KakaoLoginController {

    private final KakaoOauthService kakaoOAuthService;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/kakao")
    @ResponseBody
    public Map<String, Object> kakaoCallback(@RequestParam("code") String code) throws IOException {
        // 1. 인가코드로 사용자 정보 요청
        KakaoUserDto kakaoUser = kakaoOAuthService.getUserInfo(code);

        // 2. DB 등록 or 기존 유저 반환
        UserEntity user = kakaoOAuthService.registerOrLogin(kakaoUser);

        RefreshToken refreshToken = refreshTokenService.createorUpdateRefreshToken(user.getId());

        // 3. JWT 발급
        String accessToken = jwtUtil.createJwt(
                user.getEmail(),
                String.valueOf(user.getRole()),
                60 * 60 * 1000L // 1시간
        );

        // 4. 앱으로 리다이렉트 (딥링크 방식)
        //String redirectUri = "givebongsa://callback?accessToken=" + accessToken;
        //response.sendRedirect(redirectUri);
        return Map.of(
                "accessToken", accessToken,
                "email", user.getEmail(),
                "nickname", user.getNickname(),
                "refreshToken", refreshToken.getToken()
        );
    }
}
