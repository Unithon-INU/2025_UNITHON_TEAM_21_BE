package UNITON.demo.donation.controller;

import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.donation.dto.DonationRequestDto;
import UNITON.demo.donation.dto.DonationResponseDto;
import UNITON.demo.donation.dto.TotalDonationResponseDto;
import UNITON.demo.donation.entity.Donation;
import UNITON.demo.donation.repository.DonationRepository;
import UNITON.demo.donation.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blockchain")
public class BlockchainController {
    private final BlockchainService blockchainService;
    private final OrganizationRepository organizationRepository;
    private final DonationRepository donationRepository;


    @GetMapping("/total")
    public ResponseEntity<TotalDonationResponseDto> getTotalDonation(@RequestParam Long orgId) {
        if (!organizationRepository.existsById(orgId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        BigInteger total = blockchainService.getTotalDonations(orgId);
        return ResponseEntity.ok(new TotalDonationResponseDto(orgId, total));
    }

    @GetMapping("/donations")
    public ResponseEntity<List<DonationResponseDto>> getDonationList(@RequestParam Long orgId) {
        if (!organizationRepository.existsById(orgId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Donation> donations = donationRepository.findByOrganizationId(orgId);

        List<DonationResponseDto> response = donations.stream()
                .map(DonationResponseDto::from)  // 여기서 사용됨!!
                .toList();

        return ResponseEntity.ok(response);
    }

}
