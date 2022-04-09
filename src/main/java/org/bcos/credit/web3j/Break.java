package org.bcos.credit.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Break extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5060405160208061037f8339810180604052810190808051906020019092919050505060008060008392508273ffffffffffffffffffffffffffffffffffffffff1663c6683c856040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561009f57600080fd5b505af11580156100b3573d6000803e3d6000fd5b505050506040513d60208110156100c957600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663f96339306040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561014057600080fd5b505af1158015610154573d6000803e3d6000fd5b505050506040513d602081101561016a57600080fd5b810190808051906020019092919050505015610338578273ffffffffffffffffffffffffffffffffffffffff166346998f406040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156101e457600080fd5b505af11580156101f8573d6000803e3d6000fd5b505050506040513d602081101561020e57600080fd5b810190808051906020019092919050505090508273ffffffffffffffffffffffffffffffffffffffff16631190ce73600583036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561029357600080fd5b505af11580156102a7573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16633927f6af60006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561031f57600080fd5b505af1158015610333573d6000803e3d6000fd5b505050505b5050505060358061034a6000396000f3006080604052600080fd00a165627a7a72305820908b4f03c4d42579282a2a4da80261e0db2b558abcb3b611392ae1c4430c87550029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"name\":\"add\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5060405160208061037f8339810180604052810190808051906020019092919050505060008060008392508273ffffffffffffffffffffffffffffffffffffffff16633c8487846040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561009f57600080fd5b505af11580156100b3573d6000803e3d6000fd5b505050506040513d60208110156100c957600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff166337742e276040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561014057600080fd5b505af1158015610154573d6000803e3d6000fd5b505050506040513d602081101561016a57600080fd5b810190808051906020019092919050505015610338578273ffffffffffffffffffffffffffffffffffffffff1663322a941f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156101e457600080fd5b505af11580156101f8573d6000803e3d6000fd5b505050506040513d602081101561020e57600080fd5b810190808051906020019092919050505090508273ffffffffffffffffffffffffffffffffffffffff1663b4694425600583036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561029357600080fd5b505af11580156102a7573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663327cfb8260006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561031f57600080fd5b505af1158015610333573d6000803e3d6000fd5b505050505b5050505060358061034a6000396000f3006080604052600080fd00a165627a7a72305820203dc6dfc5e291d68b01d69695d324b3e7d4d9b0244e55becfbb2cb35fa7d3250029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    @Deprecated
    protected Break(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Break(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Break(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Break(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    @Deprecated
    public static Break load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Break(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Break load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Break(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Break load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Break(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Break load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Break(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Break> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(Break.class, web3j, credentials, contractGasProvider, getBinary(), encodedConstructor);
    }

    public static RemoteCall<Break> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(Break.class, web3j, transactionManager, contractGasProvider, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Break> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(Break.class, web3j, credentials, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Break> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(Break.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }
}
