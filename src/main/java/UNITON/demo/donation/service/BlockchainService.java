package UNITON.demo.donation.service;

public interface BlockchainService {
    void recordDonation(Long organizationId, int amount);
}
