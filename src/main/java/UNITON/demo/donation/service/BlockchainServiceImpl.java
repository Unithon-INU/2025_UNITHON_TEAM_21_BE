package UNITON.demo.donation.service;

import UNITON.demo.contracts.DonationContract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class BlockchainServiceImpl implements BlockchainService{
    private final Web3j web3j;
    private final Credentials credentials;

    private final String contractAddress = "0x274b0bb2a1b9ddaa7eef17a825513509913d6fee";

    @Override
    public String recordDonation(Long organizationId, int amount) {
        try {
            DonationContract contract = DonationContract.load(
                    contractAddress,
                    web3j,
                    credentials,
                    new StaticGasProvider(
                            BigInteger.valueOf(5_000_000_000L),
                            BigInteger.valueOf(300_000)
                    )
            );

            TransactionReceipt receipt=contract.donate(
                    BigInteger.valueOf(organizationId),
                    BigInteger.valueOf(amount)
            ).send();

            return receipt.getTransactionHash();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("스마트컨트랙트 호출 실패", e);
        }
    }

    @Override
    public BigInteger getTotalDonations(Long orgId) {
        try {
            DonationContract contract = DonationContract.load(
                    contractAddress,
                    web3j,
                    credentials,
                    new StaticGasProvider(
                            BigInteger.valueOf(5_000_000_000L),
                            BigInteger.valueOf(300_000)
                    )
            );
            return contract.totalDonations(BigInteger.valueOf(orgId)).send();
        } catch (Exception e) {
            e.printStackTrace(); // <-- 여기로 실제 오류 로그 출력됨
            throw new RuntimeException("블록체인 조회 실패", e);
        }
    }

}
