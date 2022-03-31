package org.bcos.credit.app;

import java.math.BigInteger;
import java.util.List;

import org.bcos.credit.sample.CreditData;
import org.fisco.bcos.web3j.abi.datatypes.Address;


public class Main {

    private static String LOGO = "\n"
            + "命令输入参考如下！ \n"
            + "工厂合约部署：./credit deploy keyStoreFileName keyStorePassword keyPassword  \n"
            + "创建新证据：./credit new  keyStoreFileName keyStorePassword keyPassword deployAddress grade companyName nameHash pledge companyValue  \n"
            + "发送签名：./credit send keyStoreFileName keyStorePassword keyPassword newCreditAddress name_hash \n"
            + "获取证据：./credit get keyStoreFileName keyStorePassword keyPassword newCreditAddress \n"
            + "证据和签名校验：./credit verify keyStoreFileName keyStorePassword keyPassword newCreditAddress \n"
            + "获取公钥：./credit getPublicKey keyStoreFileName keyStorePassword keyPassword  \n"
            + "抵押公司：./credit mortgage keyStoreFileName keyStorePassword keyPassword newCreditAddress loan_num \n";

    public static void main(String[] args) throws Exception {
        BcosApp app = new BcosApp();
        Address address = null;
        Address newCreditAddress = null;
        boolean configure = app.loadConfig();
        if (args.length < 4) {
            System.out.println("输入参数最小为4");
            System.exit(0);
        }
        if (!configure) {
            System.err.println("error in load configure, init failed !!!");
            System.exit(0);
        }
        System.out.println(LOGO);
        switch (args[0]) {
            //deploy
            case "deploy":
                //此方法需要传入3个参数，参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword
                address = app.deployContract(args[1], args[2], args[3]);
                System.out.println("-----------deploy factoryContract success, address: " + address.toString());
                break;
            //newCredit
            case "new":
                //参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword,
                newCreditAddress = app.newCredit(args[1], args[2], args[3], args[4], args[5], args[6], args[7], new Boolean(args[8]), new BigInteger(args[9]));
                System.out.println("------------newCredit success, newCreditAddress: " + newCreditAddress.toString());
                break;
            //sendSignatureToBlockChain
            case "send":
                //1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCreditAddress
                //通过证据地址获取证据信息
                CreditData creditData2 = app.getCredit(args[1], args[2], args[3], args[4]);
                boolean flag = app.sendSignatureToBlockChain(args, creditData2.getNameHash());
                if (flag) {
                    System.out.println("-----------sendSignatureToBlockChain success！" + flag);
                } else {
                    System.out.println("------------sendSignatureToBlockChain failed！" + flag);
                }
                break;

            //getCredit
            case "get":
                //传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCredit返回地址
                CreditData creditData = app.getCredit(args[1], args[2], args[3], args[4]);
                System.out.println("the CompanyName: " + creditData.getCompanyName());
                System.out.println("the NameHash: " + creditData.getNameHash());
                System.out.println("the credit grade of company: " + creditData.getGrade());
                System.out.println("the CompanyValue: " + creditData.getCompanyValue());
                if (creditData.getPledge()) {
                    System.out.println("the Company is pledged.");
                } else {
                    System.out.println("the Company is not pledged.");
                }
                List<String> signatureslist = creditData.getSignatures();
                for (int i = 0; i < signatureslist.size(); i++) {
                    String signatures = signatureslist.get(i);
                    System.out.println("the signature[" + i + "]: " + signatures);
                }
                List<String> publicKeyslist = creditData.getPublicKeys();
                for (int i = 0; i < publicKeyslist.size(); i++) {
                    String publicKey = publicKeyslist.get(i);
                    System.out.println("the publicKey[" + i + "]: " + publicKey);
                }
                break;
            case "verify":
                //传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCredit返回地址
                CreditData creditData1 = app.getCredit(args[1], args[2], args[3], args[4]);
                boolean flag2 = app.verifyCredit(creditData1);
                if (flag2) {
                    System.out.println("--------verifyCredit success:" + flag2);
                } else {
                    System.out.println("--------verifyCredit failed:" + flag2);
                }
                break;
            case "getPublicKey":
                String publicKey = app.getPublicKey(args[1], args[2], args[3]);
                System.out.println("---------publicKey:" + publicKey);
                break;
            default:
                break;
        }
        System.exit(0);
    }

}