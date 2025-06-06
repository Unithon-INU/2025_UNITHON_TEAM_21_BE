package UNITON.demo.donation.controller;

import UNITON.demo.donation.dto.DonationRequestDto;
import UNITON.demo.donation.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blockchain")
public class BlockchainController {
    private final BlockchainService blockchainService;

    @PostMapping("/donate")
    public ResponseEntity<String> donate(@RequestBody DonationRequestDto dto) {
        blockchainService.recordDonation(dto.getOrganizationId(), dto.getAmount());
        return ResponseEntity.ok("🟢 블록체인에 기부 기록 완료!");
    }

    @GetMapping("/total")
    public ResponseEntity<String> getTotalDonation(@RequestParam Long orgId) {
        BigInteger total = blockchainService.getTotalDonations(orgId);
        return ResponseEntity.ok("🔢 총 기부 금액: " + total.toString());
    }
}
