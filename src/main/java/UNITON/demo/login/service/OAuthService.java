package UNITON.demo.login.service;


import UNITON.demo.login.dto.KakaoOAuthDto;
import UNITON.demo.login.dto.LoginResponseDto;
import UNITON.demo.login.entity.RefreshToken;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.entity.UserRole;
import UNITON.demo.login.jwt.JWTUtil;
import UNITON.demo.login.repository.RefreshTokenRepository;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;

    public LoginResponseDto loginOrRegisterWithKakao(KakaoOAuthDto kakaoDto) {
        // 1. 사용자 존재 여부 확인
        Optional<UserEntity> existing = userRepository.findByEmail(kakaoDto.getEmail());

        UserEntity user = existing.orElseGet(() -> {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(kakaoDto.getEmail());
            newUser.setNickname(kakaoDto.getNickname());
            newUser.setProvider("KAKAO");
            newUser.setRole(UserRole.USER);
            newUser.setEmailVerified(true);
            return userRepository.save(newUser);
        });

        // 2. 자체 access / refresh 토큰 발급
        String accessToken = jwtUtil.createJwt(user.getEmail(),"USER",60 * 60 * 1000L);
        RefreshToken refreshToken = refreshTokenService.createorUpdateRefreshToken(user.getId());


        LoginResponseDto responseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .message("카카오로그인에 성공했습니다.")
                .id(user.getId())
                .userRole(0)
                .build();

        return responseDto;
    }
}
