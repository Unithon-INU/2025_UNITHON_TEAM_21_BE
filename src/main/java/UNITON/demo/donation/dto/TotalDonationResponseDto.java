package UNITON.demo.donation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class TotalDonationResponseDto {
    private Long orgId;
    private BigInteger totalAmount;
}
