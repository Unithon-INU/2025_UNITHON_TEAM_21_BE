package UNITON.demo.donation_item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationItemDto {
    private Long id;
    private String itemName;
    private int requiredQuantity;
    private int currentQuantity;
}
