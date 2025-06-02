package UNITON.demo.donation.controller;

import UNITON.demo.donation.dto.DonationRequestDto;
import UNITON.demo.donation.service.DonationService;
import UNITON.demo.login.dto.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donations")
public class DonationController {
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<String> donate(@RequestBody DonationRequestDto dto, @AuthenticationPrincipal CustomerUserDetails customerUserDetails) {
        donationService.createDonation(dto, customerUserDetails.getUsername());
        return ResponseEntity.ok("기부 요청 완료");
    }
}
