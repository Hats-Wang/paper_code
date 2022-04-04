package org.bcos.credit.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
    public static final String[] BINARY_ARRAY = {"608060405260016000806101000a81548160ff02191690831515021790555034801561002a57600080fd5b506104168061003a6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806395a2251f1461005c578063a79c60ac1461009f578063c4a4647e146100ce575b600080fd5b34801561006857600080fd5b5061009d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061011b565b005b3480156100ab57600080fd5b506100b46101b0565b604051808215151515815260200191505060405180910390f35b3480156100da57600080fd5b50610119600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506101c6565b005b60008190508073ffffffffffffffffffffffffffffffffffffffff1663a426cefc60006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561019457600080fd5b505af11580156101a8573d6000803e3d6000fd5b505050505050565b60008060009054906101000a900460ff16905090565b6000808391508173ffffffffffffffffffffffffffffffffffffffff1663209652556040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561023057600080fd5b505af1158015610244573d6000803e3d6000fd5b505050506040513d602081101561025a57600080fd5b8101908080519060200190929190505050905082811215801561031957508173ffffffffffffffffffffffffffffffffffffffff1663c8f5acb66040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156102dc57600080fd5b505af11580156102f0573d6000803e3d6000fd5b505050506040513d602081101561030657600080fd5b8101908080519060200190929190505050155b156103c9578173ffffffffffffffffffffffffffffffffffffffff1663a426cefc60016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561039257600080fd5b505af11580156103a6573d6000803e3d6000fd5b5050505060016000806101000a81548160ff0219169083151502179055506103e4565b60008060006101000a81548160ff0219169083151502179055505b505050505600a165627a7a723058205d02d9696c0331a9041af30d350aa7b5c46c179a1029cb69f57ce21a5b628f190029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"add\",\"type\":\"address\"}],\"name\":\"redeem\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getSuc\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"add\",\"type\":\"address\"},{\"name\":\"num\",\"type\":\"int256\"}],\"name\":\"mortgage\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String[] SM_BINARY_ARRAY = {"608060405260016000806101000a81548160ff02191690831515021790555034801561002a57600080fd5b506104168061003a6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680636351e9401461005c578063b0f3f48b1461008b578063ec706743146100ce575b600080fd5b34801561006857600080fd5b5061007161011b565b604051808215151515815260200191505060405180910390f35b34801561009757600080fd5b506100cc600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610131565b005b3480156100da57600080fd5b50610119600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506101c6565b005b60008060009054906101000a900460ff16905090565b60008190508073ffffffffffffffffffffffffffffffffffffffff16631864e58860006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b1580156101aa57600080fd5b505af11580156101be573d6000803e3d6000fd5b505050505050565b6000808391508173ffffffffffffffffffffffffffffffffffffffff16639f00d7356040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561023057600080fd5b505af1158015610244573d6000803e3d6000fd5b505050506040513d602081101561025a57600080fd5b8101908080519060200190929190505050905082811215801561031957508173ffffffffffffffffffffffffffffffffffffffff166334f196316040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156102dc57600080fd5b505af11580156102f0573d6000803e3d6000fd5b505050506040513d602081101561030657600080fd5b8101908080519060200190929190505050155b156103c9578173ffffffffffffffffffffffffffffffffffffffff16631864e58860016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561039257600080fd5b505af11580156103a6573d6000803e3d6000fd5b5050505060016000806101000a81548160ff0219169083151502179055506103e4565b60008060006101000a81548160ff0219169083151502179055505b505050505600a165627a7a723058206a9a511ea083565a79f5f3b7530f2b7b75dcdfcf326c228097f8ee704e0cb06b0029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String FUNC_REDEEM = "redeem";

    public static final String FUNC_GETSUC = "getSuc";

    public static final String FUNC_MORTGAGE = "mortgage";

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

    public RemoteCall<TransactionReceipt> redeem(String add) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new Address(add)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void redeem(String add, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new Address(add)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String redeemSeq(String add) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new Address(add)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getRedeemInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REDEEM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> getSuc() {
        final Function function = new Function(FUNC_GETSUC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> mortgage(String add, BigInteger num) {
        final Function function = new Function(
                FUNC_MORTGAGE, 
                Arrays.<Type>asList(new Address(add),
                new Int256(num)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void mortgage(String add, BigInteger num, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MORTGAGE, 
                Arrays.<Type>asList(new Address(add),
                new Int256(num)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String mortgageSeq(String add, BigInteger num) {
        final Function function = new Function(
                FUNC_MORTGAGE, 
                Arrays.<Type>asList(new Address(add),
                new Int256(num)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getMortgageInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MORTGAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
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

    public static RemoteCall<Mortgage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Mortgage.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Mortgage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Mortgage.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    public static RemoteCall<Mortgage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Mortgage.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Mortgage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Mortgage.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }
}
