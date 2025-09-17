package UNITON.demo.login.controller;


import UNITON.demo.login.dto.KakaoOAuthDto;
import UNITON.demo.login.dto.LoginResponseDto;
import UNITON.demo.login.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class KakaoDBController {
    private final OAuthService oAuthService;

    @PostMapping("/kakao")
    public ResponseEntity<LoginResponseDto> kakaoLogin(@RequestBody KakaoOAuthDto kakaoDto) {
        LoginResponseDto response = oAuthService.loginOrRegisterWithKakao(kakaoDto);
        return ResponseEntity.ok(response);
    }
}
