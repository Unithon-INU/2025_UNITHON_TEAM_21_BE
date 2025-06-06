package UNITON.demo.donation.service;

import java.math.BigInteger;

public interface BlockchainService {
    void recordDonation(Long organizationId, int amount);
    BigInteger getTotalDonations(Long orgId);  // 🔹 추가
}
