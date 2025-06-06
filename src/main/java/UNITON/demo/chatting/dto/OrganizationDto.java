package UNITON.demo.chatting.dto;

import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private Long id;

    private String name;            // 기관 이름
    private String address;          //
    private String contactEmail;
    private String accountnumber;   // 계좌번호
    private String phoneNumber;

    private int donationGoalAmount;     // 🎯 목표 금액
    private int totalReceivedAmount = 0; // 💰 누적 수령 금액

    public static OrganizationDto fromEntity(Organization org) {
        return new OrganizationDto(
                org.getId(),
                org.getName(),
                org.getAddress(),
                org.getContactEmail(),
                org.getAccountnumber(),
                org.getPhoneNumber(),
                org.getDonationGoalAmount(),
                org.getTotalReceivedAmount()
        );
    }
}
