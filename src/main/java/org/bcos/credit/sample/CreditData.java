package org.bcos.credit.sample;

import java.util.List;

public class CreditData {
    private List<String> signatures;//证据签名列表
    private String creditHash;//证据hash
    private String creditID;//证据ID
    private String creditInfo;//证据说明信息
    private List<String> publicKeys;//证据生效需要的公钥列表

    public CreditData(List<String> signatures, String creditHash, String creditID, String creditInfo, List<String> publicKeys) {
        this.signatures = signatures;
        this.creditHash = creditHash;
        this.creditInfo = creditInfo;
        this.creditID = creditID;
        this.publicKeys = publicKeys;
    }
    public CreditData() {

    }

    public String getCreditID() {
        return creditID;
    }

    public void setCreditID(String evidenceID) {
        this.creditID = evidenceID;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }

    public String getCreditHash() {
        return creditHash;
    }

    public void setCreditHash(String creditHash) {
        this.creditHash = creditHash;
    }

    public String getCreditInfo() {
        return creditInfo;
    }

    public void setEvidenceInfo(String evidenceInfo) {
        this.creditInfo = evidenceInfo;
    }

    public List<String> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(List<String> publicKeys) {
        this.publicKeys = publicKeys;
    }
}
