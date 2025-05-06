package UNITON.demo.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserDto {
    private String email;
    private String nickname;
    private String profileImage;
}