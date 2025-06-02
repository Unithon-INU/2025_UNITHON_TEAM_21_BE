package UNITON.demo.donation.dto;

import lombok.Data;

@Data
public class DonationRequestDto {
    private Long organizationId;
    private int amount;
}
