package UNITON.demo.donation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class TotalDonationDto {
    private String email;
    private int totalDonation;
    private String asOfDate;
}
