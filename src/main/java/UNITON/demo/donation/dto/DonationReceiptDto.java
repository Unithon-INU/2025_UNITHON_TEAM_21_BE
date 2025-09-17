package UNITON.demo.donation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonationReceiptDto {
    private String message;
    private Long orgId;
    private int amount;
    private String txHash;
    private String etherscanLink;
}
