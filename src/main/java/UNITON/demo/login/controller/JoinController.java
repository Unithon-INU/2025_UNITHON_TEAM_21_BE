package UNITON.demo.login.controller;

import UNITON.demo.login.dto.JoinByEmailDto;
import UNITON.demo.login.dto.JoinByOAuthDto;
import UNITON.demo.login.dto.ResponseDto;
import UNITON.demo.login.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/join")
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/email")
    public ResponseEntity<ResponseDto<Void>> joinByEmail(@RequestBody @Valid JoinByEmailDto dto) {
        joinService.joinByEmail(dto);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .status(200)
                .message("일반 회원가입 성공")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/oauth")
    public ResponseEntity<ResponseDto<Void>> joinByOAuth(@RequestBody @Valid JoinByOAuthDto dto) {
        joinService.joinByOAuth(dto);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .status(200)
                .message("OAuth 회원가입 성공")
                .build();
        return ResponseEntity.ok(response);
    }
}
