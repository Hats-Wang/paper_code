package org.bcos.credit.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
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
public class Mortgage extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405260016000806101000a81548160ff02191690831515021790555034801561002a57600080fd5b506040516040806103ec83398101806040528101908080519060200190929190805190602001909291905050506000808391508173ffffffffffffffffffffffffffffffffffffffff1663209652556040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100c157600080fd5b505af11580156100d5573d6000803e3d6000fd5b505050506040513d60208110156100eb57600080fd5b810190808051906020019092919050505090508281121580156101aa57508173ffffffffffffffffffffffffffffffffffffffff1663c8f5acb66040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561016d57600080fd5b505af1158015610181573d6000803e3d6000fd5b505050506040513d602081101561019757600080fd5b8101908080519060200190929190505050155b1561030d578173ffffffffffffffffffffffffffffffffffffffff1663a426cefc60016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561022357600080fd5b505af1158015610237573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663ab46159e306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156102d657600080fd5b505af11580156102ea573d6000803e3d6000fd5b5050505060016000806101000a81548160ff021916908315150217905550610328565b60008060006101000a81548160ff0219169083151502179055505b5050505060b28061033a6000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063a79c60ac146044575b600080fd5b348015604f57600080fd5b5060566070565b604051808215151515815260200191505060405180910390f35b60008060009054906101000a900460ff169050905600a165627a7a72305820da5685a2b3cce10c8da7ccd8a94f266161ce7b26edd4d032b1da6dc8620bcc780029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"getSuc\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"add\",\"type\":\"address\"},{\"name\":\"num\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String[] SM_BINARY_ARRAY = {"608060405260016000806101000a81548160ff02191690831515021790555034801561002a57600080fd5b506040516040806103ec83398101806040528101908080519060200190929190805190602001909291905050506000808391508173ffffffffffffffffffffffffffffffffffffffff16639f00d7356040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100c157600080fd5b505af11580156100d5573d6000803e3d6000fd5b505050506040513d60208110156100eb57600080fd5b810190808051906020019092919050505090508281121580156101aa57508173ffffffffffffffffffffffffffffffffffffffff166334f196316040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561016d57600080fd5b505af1158015610181573d6000803e3d6000fd5b505050506040513d602081101561019757600080fd5b8101908080519060200190929190505050155b1561030d578173ffffffffffffffffffffffffffffffffffffffff16631864e58860016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561022357600080fd5b505af1158015610237573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663069ee212306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156102d657600080fd5b505af11580156102ea573d6000803e3d6000fd5b5050505060016000806101000a81548160ff021916908315150217905550610328565b60008060006101000a81548160ff0219169083151502179055505b5050505060b28061033a6000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680636351e940146044575b600080fd5b348015604f57600080fd5b5060566070565b604051808215151515815260200191505060405180910390f35b60008060009054906101000a900460ff169050905600a165627a7a72305820c33a73303c3ef241b738253f327b9d28a113e8accdc6c26c624ce2cf95ca4b810029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String FUNC_GETSUC = "getSuc";

    @Deprecated
    protected Mortgage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Mortgage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Mortgage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Mortgage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Boolean> getSuc() {
        final Function function = new Function(FUNC_GETSUC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Mortgage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mortgage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Mortgage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mortgage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Mortgage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Mortgage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Mortgage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Mortgage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Mortgage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String add, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(num)));
        return deployRemoteCall(Mortgage.class, web3j, credentials, contractGasProvider, getBinary(), encodedConstructor);
    }

    public static RemoteCall<Mortgage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String add, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(num)));
        return deployRemoteCall(Mortgage.class, web3j, transactionManager, contractGasProvider, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Mortgage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String add, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(num)));
        return deployRemoteCall(Mortgage.class, web3j, credentials, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Mortgage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String add, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(num)));
        return deployRemoteCall(Mortgage.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }
}
