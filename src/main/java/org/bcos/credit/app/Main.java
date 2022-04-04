package org.bcos.credit.app;

import java.math.BigInteger;
import java.util.List;
import org.bcos.credit.sample.CreditData;
import org.bcos.credit.web3j.Mortgage;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;


public class Main {


    public static void main(String[] args) throws Exception {
        BcosApp app = new BcosApp();
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

        switch (args[0]) {
            //deploy
            case "deploy":
                //此方法需要传入3个参数，参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword
                app.deployContract(args[1], args[2], args[3]);
                break;

            //newCredit
            case "new":
                //参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword,deployAddress grade companyName nameHash pledge companyValue
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
                    System.out.println("--------verifyCredit success!" + flag2);
                } else {
                    System.out.println("--------verifyCredit failed!" + flag2);
                }
                break;

            case "mortgage":
                //传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCredit返回地址 5.Mortgage返回地址 6抵押金额
                creditData1 = app.getCredit(args[1], args[2], args[3], args[4]);
                Mortgage mor = app.loadMortgage(args[1], args[2], args[3],args[5]);
                mor.mortgage(args[4],new BigInteger(args[6])).send();
                Boolean f = mor.getSuc().send();
                if(f)
                    System.out.println("company is mortgageed successfully!");
                else
                    System.out.println("company is mortgaged abortively!");
                break;

            case "redeem":
                //传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCredit返回地址 5.Mortgage返回地址
                Mortgage mor2 = app.loadMortgage(args[1], args[2], args[3],args[5]);
                mor2.redeem(args[4]).send();
                System.out.println("");
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

}