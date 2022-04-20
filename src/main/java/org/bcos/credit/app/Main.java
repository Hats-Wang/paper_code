package org.bcos.credit.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import org.bcos.credit.sample.CreditData;
import org.fisco.bcos.web3j.abi.datatypes.Address;

import static org.bcos.credit.app.BcosApp.byteArrayToHex;


public class Main {
    private static BcosApp app = new BcosApp();


    public Address Deploy() throws Exception {
        boolean configure = app.loadConfig();
        Address add = app.deployContract("user.jks", "123456", "123456");
        return add;
    }

    public Address NewCredit(String add, String grade, String name, String pledge, String value) throws Exception{
        Address newCreditAddress = null;
        MessageDigest messageDigest =MessageDigest.getInstance("MD5");
        messageDigest.update(name.getBytes());
        byte[] resultByteArray = messageDigest.digest();
        String nameHash = "0x" + byteArrayToHex(resultByteArray);

        newCreditAddress = app.newCredit("user.jks", "123456", "123456", add, grade, name, nameHash, new Boolean(pledge), new BigInteger(value));
        return newCreditAddress;
    }

    public Boolean borrow(String add, String time, String num){
        Boolean ok = app.borrowMoney("user.jks", "123456", "123456", add, time, num);
        return ok;
    }
    public static void main(String[] args) throws Exception {
        boolean configure = app.loadConfig();
        Address newCreditAddress = null;

        if (!configure) {
            System.err.println("error in load configure, init failed !!!");
            System.exit(0);
        }
        while (true) {
            System.out.println("\n\n");
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            String str = scanner.readLine();
            String[] arg = str.split("\\s+");

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

                    boolean flag = app.sendSignatureToBlockChain(arg, creditData2.getNameHash());
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
                        System.out.println("the borrow contract address:" + creditData.getAddBorrow());
                        System.out.println("the mortgage contract address:" + creditData.getAddMortgage());
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
                    Boolean ok = app.borrowMoney(arg[1], arg[2], arg[3], arg[4], arg[5], arg[6]);
                    if (ok)
                        System.out.println("Company borrow money successfully!");
                    else
                        System.out.println("Company failed to borrow money!");
                    break;

                case "payback":
                    //传入参数为1.私钥文件名 2.keyStorePassword 3.keyPassword 4.newCredit返回地址
                    Boolean ok2 = app.payBack(arg[1], arg[2], arg[3], arg[4]);
                    if (ok2)
                        System.out.println("Company payback successfully!");
                    else
                        System.out.println("Company failed to payback!");
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

}