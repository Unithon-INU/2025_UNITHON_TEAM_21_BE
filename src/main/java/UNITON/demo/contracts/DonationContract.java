package UNITON.demo.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class DonationContract extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b506102d48061001c5f395ff3fe608060405234801561000f575f5ffd5b506004361061003f575f3560e01c80630cdd53f6146100435780639f1482e41461005f578063e38d87741461008f575b5f5ffd5b61005d60048036038101906100589190610186565b6100bf565b005b610079600480360381019061007491906101c4565b610122565b60405161008691906101fe565b60405180910390f35b6100a960048036038101906100a491906101c4565b610136565b6040516100b691906101fe565b60405180910390f35b805f5f8481526020019081526020015f205f8282546100de9190610244565b925050819055507fb4c840d31547b7f92c269bce1cac20980b98fdec4225c8d335fd4d2341a727008282604051610116929190610277565b60405180910390a15050565b5f602052805f5260405f205f915090505481565b5f5f5f8381526020019081526020015f20549050919050565b5f5ffd5b5f819050919050565b61016581610153565b811461016f575f5ffd5b50565b5f813590506101808161015c565b92915050565b5f5f6040838503121561019c5761019b61014f565b5b5f6101a985828601610172565b92505060206101ba85828601610172565b9150509250929050565b5f602082840312156101d9576101d861014f565b5b5f6101e684828501610172565b91505092915050565b6101f881610153565b82525050565b5f6020820190506102115f8301846101ef565b92915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f61024e82610153565b915061025983610153565b925082820190508082111561027157610270610217565b5b92915050565b5f60408201905061028a5f8301856101ef565b61029760208301846101ef565b939250505056fea2646970667358221220021e51066592fb70ff17aabbc6539a575251d0d17dd1976cb054cca93ae7cbdb64736f6c634300081e0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DONATE = "donate";

    public static final String FUNC_GETTOTALDONATIONS = "getTotalDonations";

    public static final String FUNC_TOTALDONATIONS = "totalDonations";

    public static final Event DONATIONRECORDED_EVENT = new Event("DonationRecorded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected DonationContract(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DonationContract(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DonationContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DonationContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> donate(BigInteger organizationId,
            BigInteger amount) {
        final Function function = new Function(
                FUNC_DONATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(organizationId), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static List<DonationRecordedEventResponse> getDonationRecordedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DONATIONRECORDED_EVENT, transactionReceipt);
        ArrayList<DonationRecordedEventResponse> responses = new ArrayList<DonationRecordedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DonationRecordedEventResponse typedResponse = new DonationRecordedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizationId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DonationRecordedEventResponse getDonationRecordedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DONATIONRECORDED_EVENT, log);
        DonationRecordedEventResponse typedResponse = new DonationRecordedEventResponse();
        typedResponse.log = log;
        typedResponse.organizationId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DonationRecordedEventResponse> donationRecordedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDonationRecordedEventFromLog(log));
    }

    public Flowable<DonationRecordedEventResponse> donationRecordedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DONATIONRECORDED_EVENT));
        return donationRecordedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> getTotalDonations(BigInteger organizationId) {
        final Function function = new Function(FUNC_GETTOTALDONATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(organizationId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalDonations(BigInteger param0) {
        final Function function = new Function(FUNC_TOTALDONATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DonationContract load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DonationContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DonationContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DonationContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DonationContract load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DonationContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DonationContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DonationContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DonationContract> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DonationContract.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DonationContract> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DonationContract.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DonationContract> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DonationContract.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DonationContract> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DonationContract.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class DonationRecordedEventResponse extends BaseEventResponse {
        public BigInteger organizationId;

        public BigInteger amount;
    }
}
