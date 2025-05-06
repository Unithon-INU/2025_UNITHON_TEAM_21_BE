package UNITON.demo.login.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String nickname;
    private String message;
}
