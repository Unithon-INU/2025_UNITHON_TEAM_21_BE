package UNITON.demo.donation.controller;

import UNITON.demo.donation.dto.DonationReceiptDto;
import UNITON.demo.donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/org/donations")
@PreAuthorize("hasRole('ORG_ADMIN')")
public class OrgDonationController {
    private final DonationService donationService;

    @PostMapping("/{donation}/confirm")
    public ResponseEntity<DonationReceiptDto> confirmDonation(@PathVariable Long donation) {
        DonationReceiptDto receipt=donationService.confirmDonation(donation);
        return ResponseEntity.ok(receipt);
    }

    @PostMapping("/{donation}/reject")
    public ResponseEntity<Void> rejectDonation(@PathVariable Long donation) {
        donationService.rejectDonation(donation);
        return ResponseEntity.ok().build();
    }
}
