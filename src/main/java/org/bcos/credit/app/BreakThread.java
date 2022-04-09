package org.bcos.credit.app;

import org.bcos.credit.web3j.Break;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class BreakThread extends Thread{
    private Web3j web3j;
    private Credentials credentials;
    private String add;
    private BigInteger time;
    private static BigInteger gasPrice = new BigInteger("99999999999");
    private static BigInteger gasLimited = new BigInteger("9999999999999");

    public BreakThread(Web3j web3j, Credentials credentials,String add, String time) {
        this.add = add;
        this.web3j = web3j;
        this.credentials = credentials;
        this.time = new BigInteger(time);
        this.time = this.time.multiply(new BigInteger(String.valueOf(1000)));
    }

    public void run() {
        try{
            //System.out.println("Start to Sleep" + new Long(String.valueOf(time)));
            sleep(new Long(String.valueOf(time)));
            Break b = Break.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimited),add).send();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
