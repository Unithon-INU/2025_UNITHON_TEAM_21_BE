package UNITON.demo.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgJoinRequestDto {
    private String email;
    private String password;
    private String adminname;
    private String organizationname;  // organization 객체 연관
}
