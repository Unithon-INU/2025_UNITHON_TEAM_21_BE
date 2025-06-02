package UNITON.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3Config {
    @Value("${blockchain.rpc-url}")
    private String rpcUrl;

    @Value("${blockchain.private-key}")
    private String privateKey;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(rpcUrl)); // 예: http://localhost:8545 또는 Infura URL
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(privateKey); // 개인키 (절대 외부에 노출하지 마세요)
    }
}
