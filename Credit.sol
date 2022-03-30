pragma solidity ^0.4.4;
contract CreditSignersDataABI{ function verify(address addr)public constant returns(bool){}
function getSigner(uint index)public constant returns(address){} 
function getSignersSize() public constant returns(uint){}
}

contract Credit{
    
    string credit;
    string creditInfo;
    string creditId;
    uint8[] _v;
    bytes32[] _r;
    bytes32[] _s;
    address[] signers;
    address public signersAddr;
    
        event addSignaturesEvent(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s);
        event newSignaturesEvent(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s,address addr);
        event errorNewSignaturesEvent(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s,address addr);
        event errorAddSignaturesEvent(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s,address addr);
        event addRepeatSignaturesEvent(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s);
        event errorRepeatSignaturesEvent(string evi, string id, uint8 v, bytes32 r, bytes32 s, address addr);

    function CallVerify(address addr) public constant returns(bool) {
        return CreditSignersDataABI(signersAddr).verify(addr);
    }

       function credit(string cre, string info, string id, uint8 v, bytes32 r, bytes32 s, address addr, address sender) public {
       signersAddr = addr;
       if(CallVerify(sender))
       {
           credit = cre;
           creditInfo = info;
           creditId = id;
           _v.push(v);
           _r.push(r);
           _s.push(s);
           signers.push(sender);
           newSignaturesEvent(cre,info,id,v,r,s,addr);
       }
       else
       {
           errorNewSignaturesEvent(cre,info,id,v,r,s,addr);
       }
    }

        function getEvidenceInfo() public constant returns(string){
            return creditInfo;
    }

    function getCredit() public constant returns(string,string,string,uint8[],bytes32[],bytes32[],address[]){
        uint length = CreditSignersDataABI(signersAddr).getSignersSize();
         address[] memory signerList = new address[](length);
         for(uint i= 0 ;i<length ;i++)
         {
             signerList[i] = (CreditSignersDataABI(signersAddr).getSigner(i));
         }
        return(credit,creditInfo,creditId,_v,_r,_s,signerList);
    }

    function addSignatures(uint8 v, bytes32 r, bytes32 s) public returns(bool) {
        for(uint i= 0 ;i<signers.length ;i++)
        {
            if(msg.sender == signers[i])
            {
                if( _v[i] == v && _r[i] == r && _s[i] == s)
                {
                    addRepeatSignaturesEvent(credit,creditInfo,creditId,v,r,s);
                    return true;
                }
                else
                {
                     errorRepeatSignaturesEvent(credit,creditId,v,r,s,msg.sender);
                     return false;
                }
            }
        }
       if(CallVerify(msg.sender))
       {
            _v.push(v);
            _r.push(r);
            _s.push(s);
            signers.push(msg.sender);
            addSignaturesEvent(credit,creditInfo,creditId,v,r,s);
            return true;
       }
       else
       {
           errorAddSignaturesEvent(credit,creditInfo,creditId,v,r,s,msg.sender);
           return false;
       }
    }
    
    function getSigners()public constant returns(address[])
    {
         uint length = CreditSignersDataABI(signersAddr).getSignersSize();
         address[] memory signerList = new address[](length);
         for(uint i= 0 ;i<length ;i++)
         {
             signerList[i] = (EvidenceSignersDataABI(signersAddr).getSigner(i));
         }
         return signerList;
    }
}
