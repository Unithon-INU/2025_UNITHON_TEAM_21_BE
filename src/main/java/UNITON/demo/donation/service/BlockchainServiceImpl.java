package UNITON.demo.donation.service;

import UNITON.demo.contracts.DonationContract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class BlockchainServiceImpl implements BlockchainService{
    private final Web3j web3j;
    private final Credentials credentials;

    private final String contractAddress = "0x274b0bb2a1b9ddaa7eef17a825513509913d6fee";

    @Override
    public void recordDonation(Long organizationId, int amount) {
        try {
            DonationContract contract = DonationContract.load(
                    contractAddress,
                    web3j,
                    credentials,
                    new StaticGasProvider(
                            BigInteger.valueOf(20000000000L),
                            BigInteger.valueOf(6721975)
                    )
            );

            contract.donate(
                    BigInteger.valueOf(organizationId),
                    BigInteger.valueOf(amount)
            ).send();

        } catch (Exception e) {
            throw new RuntimeException("스마트컨트랙트 호출 실패", e);
        }
    }
}
