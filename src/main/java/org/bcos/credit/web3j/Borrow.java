package org.bcos.credit.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
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
public class Borrow extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506040516060806109c48339810180604052810190808051906020019092919080519060200190929190805190602001909291905050506000836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600181905550816002819055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c8f5acb66040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561011c57600080fd5b505af1158015610130573d6000803e3d6000fd5b505050506040513d602081101561014657600080fd5b8101908080519060200190929190505050806102215750603c6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166346998f406040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156101e457600080fd5b505af11580156101f8573d6000803e3d6000fd5b505050506040513d602081101561020e57600080fd5b8101908080519060200190929190505050125b15610246576000600360006101000a81548160ff021916908315150217905550610461565b838261025061046a565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f0801580156102a9573d6000803e3d6000fd5b5090508073ffffffffffffffffffffffffffffffffffffffff1663a79c60ac6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561031057600080fd5b505af1158015610324573d6000803e3d6000fd5b505050506040513d602081101561033a57600080fd5b810190808051906020019092919050505015610444576001600360006101000a81548160ff0219169083151502179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166307ee6045306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561042757600080fd5b505af115801561043b573d6000803e3d6000fd5b50505050610460565b6000600360006101000a81548160ff0219169083151502179055505b5b5050505061047a565b6040516103ec806105d883390190565b61014f806104896000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631d8f78921461005c5780633927f6af14610087578063f9633930146100b6575b600080fd5b34801561006857600080fd5b506100716100e5565b6040518082815260200191505060405180910390f35b34801561009357600080fd5b506100b46004803603810190808035151590602001909291905050506100ef565b005b3480156100c257600080fd5b506100cb61010c565b604051808215151515815260200191505060405180910390f35b6000600154905090565b80600360006101000a81548160ff02191690831515021790555050565b6000600360009054906101000a900460ff169050905600a165627a7a723058207cac0b69967defabd5ffc68617d71205d253013b82f36c23fd132df7ac748f3f0029608060405260016000806101000a81548160ff02191690831515021790555034801561002a57600080fd5b506040516040806103ec83398101806040528101908080519060200190929190805190602001909291905050506000808391508173ffffffffffffffffffffffffffffffffffffffff1663209652556040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100c157600080fd5b505af11580156100d5573d6000803e3d6000fd5b505050506040513d60208110156100eb57600080fd5b810190808051906020019092919050505090508281121580156101aa57508173ffffffffffffffffffffffffffffffffffffffff1663c8f5acb66040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561016d57600080fd5b505af1158015610181573d6000803e3d6000fd5b505050506040513d602081101561019757600080fd5b8101908080519060200190929190505050155b1561030d578173ffffffffffffffffffffffffffffffffffffffff1663a426cefc60016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561022357600080fd5b505af1158015610237573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663ab46159e306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156102d657600080fd5b505af11580156102ea573d6000803e3d6000fd5b5050505060016000806101000a81548160ff021916908315150217905550610328565b60008060006101000a81548160ff0219169083151502179055505b5050505060b28061033a6000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063a79c60ac146044575b600080fd5b348015604f57600080fd5b5060566070565b604051808215151515815260200191505060405180910390f35b60008060009054906101000a900460ff169050905600a165627a7a72305820da5685a2b3cce10c8da7ccd8a94f266161ce7b26edd4d032b1da6dc8620bcc780029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"getMonth\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"b\",\"type\":\"bool\"}],\"name\":\"setFlag\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getFlag\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"add\",\"type\":\"address\"},{\"name\":\"time\",\"type\":\"int256\"},{\"name\":\"num\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b506040516060806109c48339810180604052810190808051906020019092919080519060200190929190805190602001909291905050506000836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600181905550816002819055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166334f196316040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561011c57600080fd5b505af1158015610130573d6000803e3d6000fd5b505050506040513d602081101561014657600080fd5b8101908080519060200190929190505050806102215750603c6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663322a941f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156101e457600080fd5b505af11580156101f8573d6000803e3d6000fd5b505050506040513d602081101561020e57600080fd5b8101908080519060200190929190505050125b15610246576000600360006101000a81548160ff021916908315150217905550610461565b838261025061046a565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f0801580156102a9573d6000803e3d6000fd5b5090508073ffffffffffffffffffffffffffffffffffffffff16636351e9406040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561031057600080fd5b505af1158015610324573d6000803e3d6000fd5b505050506040513d602081101561033a57600080fd5b810190808051906020019092919050505015610444576001600360006101000a81548160ff0219169083151502179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166318558f57306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561042757600080fd5b505af115801561043b573d6000803e3d6000fd5b50505050610460565b6000600360006101000a81548160ff0219169083151502179055505b5b5050505061047a565b6040516103ec806105d883390190565b61014f806104896000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632f4df0e31461005c578063327cfb821461008757806337742e27146100b6575b600080fd5b34801561006857600080fd5b506100716100e5565b6040518082815260200191505060405180910390f35b34801561009357600080fd5b506100b46004803603810190808035151590602001909291905050506100ef565b005b3480156100c257600080fd5b506100cb61010c565b604051808215151515815260200191505060405180910390f35b6000600154905090565b80600360006101000a81548160ff02191690831515021790555050565b6000600360009054906101000a900460ff169050905600a165627a7a7230582038767fd8a798cb6585e26e5d4725535e4dacf469e454e3a3e6c6ded4835781460029608060405260016000806101000a81548160ff02191690831515021790555034801561002a57600080fd5b506040516040806103ec83398101806040528101908080519060200190929190805190602001909291905050506000808391508173ffffffffffffffffffffffffffffffffffffffff16639f00d7356040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100c157600080fd5b505af11580156100d5573d6000803e3d6000fd5b505050506040513d60208110156100eb57600080fd5b810190808051906020019092919050505090508281121580156101aa57508173ffffffffffffffffffffffffffffffffffffffff166334f196316040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561016d57600080fd5b505af1158015610181573d6000803e3d6000fd5b505050506040513d602081101561019757600080fd5b8101908080519060200190929190505050155b1561030d578173ffffffffffffffffffffffffffffffffffffffff16631864e58860016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082151515158152602001915050600060405180830381600087803b15801561022357600080fd5b505af1158015610237573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663069ee212306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156102d657600080fd5b505af11580156102ea573d6000803e3d6000fd5b5050505060016000806101000a81548160ff021916908315150217905550610328565b60008060006101000a81548160ff0219169083151502179055505b5050505060b28061033a6000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680636351e940146044575b600080fd5b348015604f57600080fd5b5060566070565b604051808215151515815260200191505060405180910390f35b60008060009054906101000a900460ff169050905600a165627a7a72305820c33a73303c3ef241b738253f327b9d28a113e8accdc6c26c624ce2cf95ca4b810029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String FUNC_GETMONTH = "getMonth";

    public static final String FUNC_SETFLAG = "setFlag";

    public static final String FUNC_GETFLAG = "getFlag";

    @Deprecated
    protected Borrow(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Borrow(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Borrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Borrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<BigInteger> getMonth() {
        final Function function = new Function(FUNC_GETMONTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setFlag(Boolean b) {
        final Function function = new Function(
                FUNC_SETFLAG, 
                Arrays.<Type>asList(new Bool(b)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setFlag(Boolean b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETFLAG, 
                Arrays.<Type>asList(new Bool(b)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setFlagSeq(Boolean b) {
        final Function function = new Function(
                FUNC_SETFLAG, 
                Arrays.<Type>asList(new Bool(b)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<Boolean> getSetFlagInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETFLAG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> getFlag() {
        final Function function = new Function(FUNC_GETFLAG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Borrow load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Borrow(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Borrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Borrow(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Borrow load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Borrow(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Borrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Borrow(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Borrow> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String add, BigInteger time, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new Int256(time),
                new Int256(num)));
        return deployRemoteCall(Borrow.class, web3j, credentials, contractGasProvider, getBinary(), encodedConstructor);
    }

    public static RemoteCall<Borrow> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String add, BigInteger time, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new Int256(time),
                new Int256(num)));
        return deployRemoteCall(Borrow.class, web3j, transactionManager, contractGasProvider, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Borrow> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String add, BigInteger time, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new Int256(time),
                new Int256(num)));
        return deployRemoteCall(Borrow.class, web3j, credentials, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Borrow> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String add, BigInteger time, BigInteger num) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(add), 
                new Int256(time),
                new Int256(num)));
        return deployRemoteCall(Borrow.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), encodedConstructor);
    }
}
