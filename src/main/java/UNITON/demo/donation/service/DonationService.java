package UNITON.demo.donation.service;

import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.donation.dto.DonationRequestDto;
import UNITON.demo.donation.entity.Donation;
import UNITON.demo.donation.entity.DonationStatus;
import UNITON.demo.donation.repository.DonationRepository;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final DonationRepository donationRepository;
    private final BlockchainService blockchainService;

    public void createDonation(DonationRequestDto dto, String userEmail) {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Organization org = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        Donation donation = new Donation();
        donation.setUser(user);
        donation.setOrganization(org);
        donation.setAmount(dto.getAmount());
        donation.setStatus(DonationStatus.PENDING);

        donationRepository.save(donation);
    }

    @Transactional
    public void confirmDonation(Long donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        if (donation.getStatus() != DonationStatus.PENDING) {
            throw new IllegalStateException("이미 처리된 기부입니다.");
        }

        // 상태 변경
        donation.setStatus(DonationStatus.CONFIRMED);

        // 기관 누적 금액 증가
        Organization org = donation.getOrganization();
        org.setTotalReceivedAmount(org.getTotalReceivedAmount() + donation.getAmount());

        // 블록체인 기록 (선택)
        blockchainService.recordDonation(org.getId(), donation.getAmount());
    }

    @Transactional
    public void rejectDonation(Long donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("기부 내역을 찾을 수 없습니다."));

        if (donation.getStatus() != DonationStatus.PENDING) {
            throw new IllegalStateException("이미 처리된 기부입니다.");
        }

        donation.setStatus(DonationStatus.REJECTED);

        // 👉 선택: 사용자에게 알림 또는 메일 발송 가능
    }
}