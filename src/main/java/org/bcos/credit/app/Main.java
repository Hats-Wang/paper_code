package org.bcos.credit.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import org.bcos.credit.sample.CreditData;
import org.fisco.bcos.web3j.abi.datatypes.Address;



public class Main {


    public static void main(String[] args) throws Exception {
        BcosApp app = new BcosApp();
        Address newCreditAddress = null;
        boolean configure = app.loadConfig();

        if (!configure) {
            System.err.println("error in load configure, init failed !!!");
            System.exit(0);
        }
        while(true) {
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            String str = scanner.readLine();
            String[] arg = str.split("\\s+");
            for (int i = 0; i < arg.length; i++) System.out.println(arg[i]);

            switch (arg[0]) {
                //deploy
                case "deploy":
                    //此方法需要传入3个参数，参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword
                    app.deployContract(arg[1], arg[2], arg[3]);
                    break;

                //newCredit
                case "new":
                    //参数1为keyStoreFileName（私钥文件名），参数2为keyStorePassword，参数3为keyPassword,deployAddress grade companyName nameHash pledge companyValue
                    newCreditAddress = app.newCredit(arg[1], arg[2], arg[3], arg[4], arg[5], arg[6], arg[7], new Boolean(arg[8]), new BigInteger(arg[9]));
                    System.out.println("------------newCredit success, newCreditAddress: " + newCreditAddress.toString());
                    break;

                //sendSignatureToBlockChain
                case "send":
                    //1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCreditAddress
                    //通过证据地址获取证据信息
                    CreditData creditData2 = app.getCredit(arg[1], arg[2], arg[3], arg[4]);

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
                    CreditData creditData = app.getCredit(arg[1], arg[2], arg[3], arg[4]);
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
                    CreditData creditData1 = app.getCredit(arg[1], arg[2], arg[3], arg[4]);
                    boolean flag2 = app.verifyCredit(creditData1);
                    if (flag2) {
                        System.out.println("--------verifyCredit success!" + flag2);
                    } else {
                        System.out.println("--------verifyCredit failed!" + flag2);
                    }
                    break;

                case "borrow":
                    //传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCredit返回地址 5.time 6.loan_num
                    Boolean ok = app.borrowMoney(arg[1],arg[2],arg[3],arg[4],arg[5],arg[6]);
                    if(ok)
                        System.out.println("Company borrow money successfully!");
                    else
                        System.out.println("Company failed to borrow money!");
                    break;

                case "getPublicKey":
                    String publicKey = app.getPublicKey(arg[1], arg[2], arg[3]);
                    System.out.println("---------publicKey:" + publicKey);
                    break;

                case "exit":
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }
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