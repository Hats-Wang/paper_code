package org.bcos.credit.app;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.util.*;

import org.bcos.credit.web3j.Mortgage;
import org.fisco.bcos.channel.client.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.bcos.credit.sample.CreditData;
import org.bcos.credit.sample.PublicAddressConf;
import org.bcos.credit.util.Tools;
import org.bcos.credit.web3j.Credit;
import org.bcos.credit.web3j.CreditSignersData;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.Sign;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;


public class BcosApp {

	private CreditSignersData creditSignersData;
	private Web3j web3j;
	ApplicationContext context;

	public static BigInteger gasPrice = new BigInteger("99999999999");
	public static BigInteger gasLimited = new BigInteger("9999999999999");

	public BcosApp() {
        creditSignersData =null;
		web3j = null;
		context=null;
	}

	//loadConfig
	public boolean loadConfig() throws Exception{
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
        service.run();
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        web3j = Web3j.build(channelEthereumService,service.getGroupId());
        boolean flag=false;
        if(web3j!=null){
        	flag=true;
        }
        return flag;
	}

	//deploy
	public Address deployContract(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception {
		if (web3j == null )
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
	    //Service service = context.getBean(Service.class);
	    //service.run();
	    PublicAddressConf conf = context.getBean(PublicAddressConf.class);
        ConcurrentHashMap<String, String> addressConf = conf.getAllPublicAddress();
        List<String> arrayList = addressConf.values().stream().map(String::new).collect(Collectors.toCollection(ArrayList::new));
        try {
            creditSignersData = CreditSignersData.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimited), arrayList).send();
			String address = creditSignersData.getContractAddress();
			System.out.println("-----------deploy SignersData Contract success, address: " + address);
			Mortgage mor  = Mortgage.deploy(web3j, credentials,new StaticGasProvider(gasPrice, gasLimited)).send();
			address = mor.getContractAddress();
			System.out.println("-----------deploy Mortgage Contract success, address: " + address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Address(creditSignersData.getContractAddress());

	}

	//newCredit
	public Address newCredit(String keyStoreFileName, String keyStorePassword, String keyPassword, String address, String grade, String companyName, String nameHash, Boolean pledge, BigInteger companyValue) throws Exception {
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
		if (web3j == null)
			return null;

		if (address != null) {
            creditSignersData = CreditSignersData.load(address.toString(), web3j,  credentials, new StaticGasProvider(gasPrice, gasLimited));
		}

		String name_hash=nameHash;
		//通过hash和key算出一个用户机构签名数据
		Sign.SignatureData data = Sign.getSignInterface().signMessage(name_hash.getBytes(), credentials.getEcKeyPair());
		String sign_data=Tools.signatureDataToString(data);
		TransactionReceipt receipt = null;
		try {
			Sign.SignatureData signatureData = Tools.stringToSignatureData(sign_data);
			System.out.println("正在执行！");
			receipt = creditSignersData.newCredit(new BigInteger(grade), companyName,pledge, companyValue, BigInteger.valueOf(signatureData.getV()),signatureData.getR(),signatureData.getS()).sendAsync().get();

			List<CreditSignersData.NewCreditEventEventResponse> newCreditList = creditSignersData.getNewCreditEventEvents(receipt);
			if (newCreditList.size() > 0) {
	               return new Address(newCreditList.get(0).addr);
	        } else {
	               return null;
	        }
		} catch (InterruptedException | ExecutionException e) {
			throw e;
		}

	}

	//sendSignatureToBlockChain
	//1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCreditAddress 5.name_hash
	public boolean sendSignatureToBlockChain(String[] args,String name_hash) throws Exception{
		Credentials credentials=loadkey(args[1],args[2],args[3]);
        Credit credit = Credit.load(args[4], web3j, credentials,  gasPrice, gasLimited);
	    Sign.SignatureData data = Sign.getSignInterface().signMessage(name_hash.getBytes(), credentials.getEcKeyPair());
		boolean flag=false;
		try {
				String signatureString=Tools.signatureDataToString(data);
				Sign.SignatureData signature = Tools.stringToSignatureData(signatureString);
	            String recoverAddress = verifySignedMessage(name_hash,signatureString);
	            if(!credentials.getAddress().equals(recoverAddress))
	            {
	                throw new SignatureException();
	            }
	            System.out.println("开始发送！");
	            TransactionReceipt receipt = credit.addSignatures(BigInteger.valueOf(signature.getV()),
	                    signature.getR(),
	                    signature.getS()).sendAsync().get();
	            List<Credit.AddSignaturesEventEventResponse> addList = credit.getAddSignaturesEventEvents(receipt);
	            List<Credit.AddRepeatSignaturesEventEventResponse> addList2 = credit.getAddRepeatSignaturesEventEvents(receipt);

	            if (addList.size() > 0 || addList2.size() >0)
	            {
	            	flag=true;
	            }
	        } catch (InterruptedException e) {
	            throw e;
	        } catch (ExecutionException e) {
	            throw e;
	     }
		return flag;
	}


	//getCredit
	public CreditData getCredit(String keyStoreFileName,String keyStorePassword, String keyPassword,String transactionHash) throws Exception{
		if (web3j == null )
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
        Credit credit = Credit.load(transactionHash, web3j, credentials,  gasPrice, gasLimited);
		CreditData creditData = new CreditData();
		try {
			Tuple8<BigInteger, String, Boolean, BigInteger, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>> result2 = credit.getCredit().send();
			if (result2 == null)
				return null;
			//证据字段为6个
            creditData.setGrade(result2.getValue1());
            creditData.setCompanyName(result2.getValue2());
            creditData.setPledge(result2.getValue3());
			creditData.setCompanyValue(result2.getValue4());
			List<BigInteger> vlist = result2.getValue5();
			List<byte[]> rlist = result2.getValue6();
			List<byte[]> slist = result2.getValue7();

			MessageDigest messageDigest =MessageDigest.getInstance("MD5");
			messageDigest.update(creditData.getCompanyName().getBytes());
			byte[] resultByteArray = messageDigest.digest();
			String s = "0x" + byteArrayToHex(resultByteArray);
			creditData.setNameHash(s);

			ArrayList<String> signatureList = new ArrayList<String>();
			for (int i = 0; i < vlist.size(); i++) {
				Sign.SignatureData signature = new Sign.SignatureData(
						vlist.get(i).byteValue(), rlist.get(i), slist.get(i));
				signatureList.add(Tools.signatureDataToString(signature));
			}
            creditData.setSignatures(signatureList);
			List<String> addresses = result2.getValue8();
			ArrayList<String> addressesList = new ArrayList<String>();
			for (int i = 0; i < addresses.size(); i++) {
				String str = addresses.get(i);
				addressesList.add(str);
			}
            creditData.setPublicKeys(addressesList);
		}  catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e;
        }
		return creditData;
	}

	public static String byteArrayToHex(byte[] byteArray) {
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
		char[] resultCharArray =new char[byteArray.length * 2];
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b& 0xf];
		}
		return new String(resultCharArray);
	}



	//verifyCredit
	public boolean verifyCredit(CreditData data) throws SignatureException {
		 ArrayList<String> addressList = new ArrayList<>();
	        for (String str : data.getSignatures()) {
	            try {
	                addressList.add(verifySignedMessage(data.getNameHash(), str));
	            } catch (SignatureException e) {
	                throw e;
	            }
	        }
	        for (String addr : data.getPublicKeys()) {
	            boolean flag = false;
	            for (String str : addressList) {
	                if (str.equals(addr)) {
	                    flag = true;
	                    break;
	                }
	            }
	            if (!flag) {
	                return false;
	            }
	        }
	        return true;
	}


    public String verifySignedMessage(String message, String signatureData) throws SignatureException {
        Sign.SignatureData signatureData1 = Tools.stringToSignatureData(signatureData);
        try {
            return "0x" + Keys.getAddress(Sign.signedMessageToKey(message.getBytes(), signatureData1));
        } catch (SignatureException e) {
            throw e;
        }
    }

    public Credentials loadkey(String keyStoreFileName,String keyStorePassword, String keyPassword) {
    	InputStream ksInputStream = null;
    	try {
    		 KeyStore ks = KeyStore.getInstance("JKS");
    		 ksInputStream =  BcosApp.class.getClassLoader().getResourceAsStream(keyStoreFileName);
    		 ks.load(ksInputStream, keyStorePassword.toCharArray());
    		 Key key = ks.getKey("ec", keyPassword.toCharArray());
    		 ECKeyPair keyPair = ECKeyPair.create(((ECPrivateKey) key).getS());
    		 Credentials credentials = Credentials.create(keyPair);
    		 if(credentials!=null){
    		    return credentials;
    		 }else{
    			 System.out.println("秘钥参数输入有误！");
    		 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			    if(null != ksInputStream)
			    {
				    ksInputStream.close();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return null;
    }

    public String getPublicKey(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception{
    	Credentials credentials=loadkey(keyStoreFileName, keyStorePassword, keyPassword);
    	return credentials.getAddress();
    }

	public Mortgage loadMortgage(String keyStoreFileName,String keyStorePassword, String keyPassword,String add) {
		if (web3j == null )
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
		Mortgage mor = Mortgage.load(add, web3j, credentials, gasPrice, gasLimited);
		return mor;
	}
}
