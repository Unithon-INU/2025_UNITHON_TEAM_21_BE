package UNITON.demo.login.controller;

import UNITON.demo.login.dto.LoginRequestDto;
import UNITON.demo.login.dto.LoginResponseDto;
import UNITON.demo.login.dto.ErrorResponseDto;
import UNITON.demo.login.dto.ResponseDto;
import UNITON.demo.login.entity.OrganizationAdmin;
import UNITON.demo.login.entity.RefreshToken;
import UNITON.demo.login.jwt.JWTUtil;
import UNITON.demo.login.repository.OrganizationAdminRepository;
import UNITON.demo.login.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/org")
public class OrgAdminLoginController {

    private final OrganizationAdminRepository orgAdminRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        OrganizationAdmin admin = orgAdminRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("기관 관리자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "비밀번호가 일치하지 않습니다."));
        }

        String accessToken = jwtUtil.createJwt(admin.getEmail(), "ORG_ADMIN", 60 * 60 * 1000L);
        RefreshToken refreshToken = refreshTokenService.createorUpdateRefreshToken(admin.getId());

        LoginResponseDto responseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .email(admin.getEmail())
                .nickname(admin.getAdminname())
                .message("기관 로그인 성공")
                .build();

        return ResponseEntity.ok(responseDto);
    }
}
