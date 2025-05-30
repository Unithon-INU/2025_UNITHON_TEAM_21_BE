package UNITON.demo.login.controller;

import UNITON.demo.login.dto.JoinByEmailDto;
import UNITON.demo.login.dto.ResponseDto;
import UNITON.demo.login.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "회원가입 API", description = "이메일 회원가입 관련 API입니다.")
public class JoinController {
    private final JoinService joinService;

    @Operation(
            summary = "이메일 회원가입",
            description = "이메일, 비밀번호, 닉네임으로 회원가입을 수행합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "회원가입 성공"),
                    @ApiResponse(responseCode = "400", description = "중복 이메일")
            }
    )
    @PostMapping("/email")
    public ResponseEntity<ResponseDto<Void>> joinByEmail(@RequestBody @Valid JoinByEmailDto dto) {
        joinService.joinByEmail(dto);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .status(200)
                .message("일반 회원가입 성공")
                .build();
        return ResponseEntity.ok(response);
    }

}
