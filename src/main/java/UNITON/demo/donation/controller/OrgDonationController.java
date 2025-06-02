package UNITON.demo.donation.controller;

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

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmDonation(@PathVariable Long orgid) {
        donationService.confirmDonation(orgid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectDonation(@PathVariable Long id) {
        donationService.rejectDonation(id);
        return ResponseEntity.ok().build();
    }
}
