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
public class PayBack extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506040516020806103708339810180604052810190808051906020019092919050505060008060008392508273ffffffffffffffffffffffffffffffffffffffff1663a426cefc60006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b1580156100af57600080fd5b505af11580156100c3573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166346998f406040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561012b57600080fd5b505af115801561013f573d6000803e3d6000fd5b505050506040513d602081101561015557600080fd5b8101908080519060200190929190505050915060648212156101fc578273ffffffffffffffffffffffffffffffffffffffff16631190ce73600184016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156101e357600080fd5b505af11580156101f7573d6000803e3d6000fd5b505050505b8273ffffffffffffffffffffffffffffffffffffffff1663c6683c856040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561026057600080fd5b505af1158015610274573d6000803e3d6000fd5b505050506040513d602081101561028a57600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff16633927f6af60006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561031157600080fd5b505af1158015610325573d6000803e3d6000fd5b505050505050505060358061033b6000396000f3006080604052600080fd00a165627a7a72305820f74a5908ca66a9ac3ea0da86ee75a3dcea26c56a24f59819530ea4087dc50d770029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"name\":\"add\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b506040516020806103708339810180604052810190808051906020019092919050505060008060008392508273ffffffffffffffffffffffffffffffffffffffff16631864e58860006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b1580156100af57600080fd5b505af11580156100c3573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663322a941f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561012b57600080fd5b505af115801561013f573d6000803e3d6000fd5b505050506040513d602081101561015557600080fd5b8101908080519060200190929190505050915060648212156101fc578273ffffffffffffffffffffffffffffffffffffffff1663b4694425600184016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156101e357600080fd5b505af11580156101f7573d6000803e3d6000fd5b505050505b8273ffffffffffffffffffffffffffffffffffffffff16633c8487846040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561026057600080fd5b505af1158015610274573d6000803e3d6000fd5b505050506040513d602081101561028a57600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff1663327cfb8260006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561031157600080fd5b505af1158015610325573d6000803e3d6000fd5b505050505050505060358061033b6000396000f3006080604052600080fd00a165627a7a7230582062756aeacebb43392796081c9e0a9c36f2ce0eaa9e34340f78efc08dda37ca700029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    @Deprecated
    protected PayBack(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PayBack(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PayBack(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PayBack(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    @Deprecated
    public static PayBack load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PayBack(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PayBack load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PayBack(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PayBack load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PayBack(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PayBack load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PayBack(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PayBack> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(PayBack.class, web3j, credentials, contractGasProvider, getBinary(), encodedConstructor);
    }

    public static RemoteCall<PayBack> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(PayBack.class, web3j, transactionManager, contractGasProvider, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PayBack> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(PayBack.class, web3j, credentials, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PayBack> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String add) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add)));
        return deployRemoteCall(PayBack.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }
}
