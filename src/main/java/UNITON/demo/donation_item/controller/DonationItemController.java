package UNITON.demo.donation_item.controller;

import UNITON.demo.donation_item.dto.DonationItemDto;
import UNITON.demo.donation_item.dto.MessageResponse;
import UNITON.demo.donation_item.service.DonationItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donation-items")
public class DonationItemController {
    private final DonationItemService donationItemService;

    @PostMapping("/{org_id}")
    public ResponseEntity<Long> createItem(@PathVariable Long org_id,@RequestBody DonationItemDto dto) {
        Long itemId=donationItemService.create(org_id,dto);
        return ResponseEntity.ok(itemId);
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<DonationItemDto>> getByOrganization(@PathVariable Long orgId) {
        List<DonationItemDto> items = donationItemService.getItemsByOrganization(orgId);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateItem(@PathVariable Long id, @RequestBody DonationItemDto dto) {
        donationItemService.update(id, dto);
        return ResponseEntity.ok(new MessageResponse("물품이 성공적으로 수정되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteItem(@PathVariable Long id) {
        donationItemService.delete(id);
        return ResponseEntity.ok(new MessageResponse("물품이 성공적으로 삭제되었습니다."));
    }
}
