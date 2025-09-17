package UNITON.demo.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCheckResponseDto {
    private Long organizationId;
    private boolean isRegistered;
}
