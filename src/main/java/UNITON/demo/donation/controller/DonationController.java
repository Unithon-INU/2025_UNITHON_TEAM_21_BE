package UNITON.demo.donation.controller;

import UNITON.demo.donation.dto.DonationRequestDto;
import UNITON.demo.donation.dto.DonationResponseDto;
import UNITON.demo.donation.dto.TotalDonationDto;
import UNITON.demo.donation.service.DonationService;
import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/my")
    public ResponseEntity<List<DonationResponseDto>> getMyDonations(@AuthenticationPrincipal CustomerUserDetails userDetails) {
        String email = userDetails.getUsername();
        List<DonationResponseDto> list = donationService.getDonationsByEmail(email);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/my-total")
    public ResponseEntity<TotalDonationDto> getMyTotalDonation(
            @AuthenticationPrincipal CustomerUserDetails userDetails) {
        int total = donationService.getTotalDonationsByEmail(userDetails.getUsername());

        TotalDonationDto response = TotalDonationDto.builder()
                .totalDonation(total)
                .asOfDate(LocalDate.now().toString())
                .email(userDetails.getUsername())
                .build();

        return ResponseEntity.ok(response);
    }
}
