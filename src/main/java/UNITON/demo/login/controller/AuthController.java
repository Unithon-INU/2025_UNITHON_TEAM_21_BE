package UNITON.demo.login.controller;

import UNITON.demo.login.dto.RefreshRequestDto;
import UNITON.demo.login.entity.RefreshToken;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.entity.UserRole;
import UNITON.demo.login.jwt.JWTUtil;
import UNITON.demo.login.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final JWTUtil jwtUtil;

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequestDto request) {
        String refreshToken = request.getRefreshToken();

        RefreshToken token = refreshTokenService.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 refresh token"));

        refreshTokenService.verifyExpiration(token); // 만료 검증 (만료되면 삭제 + 예외)

        UserEntity user = token.getUser(); // 연관된 사용자

        String newAccessToken = jwtUtil.createJwt(
                user.getEmail(),
                String.valueOf(user.getRole()),
                60 * 60 * 1000L
        );

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}
