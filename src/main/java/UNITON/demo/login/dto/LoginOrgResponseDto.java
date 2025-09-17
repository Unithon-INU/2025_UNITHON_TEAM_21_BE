package UNITON.demo.login.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginOrgResponseDto {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String nickname;
    private String message;
    private Long id;
    private Long org_id;
    private int userRole;  // 여기에 숫자값으로 role 표현 (0 or 1)
}
