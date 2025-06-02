package UNITON.demo.donation.controller;

import UNITON.demo.donation.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blockchain")
public class BlockchainController {
    private final BlockchainService blockchainService;

    @PostMapping("/donate")
    public ResponseEntity<String> donate(@RequestParam Long orgId, @RequestParam int amount) {
        blockchainService.recordDonation(orgId, amount);
        return ResponseEntity.ok("🟢 블록체인에 기부 기록 완료!");
    }
}
