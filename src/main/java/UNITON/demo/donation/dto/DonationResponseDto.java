package UNITON.demo.donation.dto;


import UNITON.demo.donation.entity.Donation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonationResponseDto {
    private Long donationId;
    private String donorEmail;
    private String donorNickName;
    private String organization;
    private int amount;
    private String status;
    private LocalDateTime donatedAt;

    public static DonationResponseDto from(Donation d) {
        DonationResponseDto dto = new DonationResponseDto();
        dto.setDonationId(d.getId());
        dto.setDonorEmail(d.getUser().getEmail()); // 또는 nickname
        dto.setDonorNickName(d.getUser().getNickname());
        dto.setOrganization(d.getOrganization().getName());
        dto.setAmount(d.getAmount());
        dto.setStatus(d.getStatus().name());
        dto.setDonatedAt(d.getDonatedAt());
        return dto;
    }
}
