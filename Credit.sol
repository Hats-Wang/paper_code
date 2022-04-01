pragma solidity ^0.4.4;
contract CreditSignersDataABI{ function verify(address addr)public constant returns(bool){}
function getSigner(uint index)public constant returns(address){} 
function getSignersSize() public constant returns(uint){}
}

contract Credit{
    
    string grade;
    string companyName;
    bool pledge;
    int256 value;
    uint8[] _v;
    bytes32[] _r;
    bytes32[] _s;
    address[] signers;
    address public signersAddr;
    
        event addSignaturesEvent(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s);
        event newSignaturesEvent(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s,address addr);
        event errorNewSignaturesEvent(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s,address addr);
        event errorAddSignaturesEvent(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s,address addr);
        event addRepeatSignaturesEvent(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s);
        event errorRepeatSignaturesEvent(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s, address addr);

    function CallVerify(address addr) public constant returns(bool) {
        return CreditSignersDataABI(signersAddr).verify(addr);
    }

    function Credit(string grd, string name, bool p, int256 vl, uint8 v, bytes32 r, bytes32 s, address addr, address sender) public {
       signersAddr = addr;
       if(CallVerify(sender))
       {
           grade = grd;
           companyName = name;
           pledge = p;
           value = vl;
           _v.push(v);
           _r.push(r);
           _s.push(s);
           signers.push(sender);
           emit newSignaturesEvent(grd,name,p,vl,v,r,s,addr);
       }
       else
       {
           emit errorNewSignaturesEvent(grd,name,p,vl,v,r,s,addr);
       }
    }


    function getCredit() public constant returns(string,string,bool,int256,uint8[],bytes32[],bytes32[],address[]){
        uint length = CreditSignersDataABI(signersAddr).getSignersSize();
         address[] memory signerList = new address[](length);
         for(uint i= 0 ;i<length ;i++)
         {
             signerList[i] = (CreditSignersDataABI(signersAddr).getSigner(i));
         }
        return(grade,companyName,pledge,value,_v,_r,_s,signerList);
    }

    function addSignatures(uint8 v, bytes32 r, bytes32 s) public returns(bool) {
        for(uint i= 0 ;i<signers.length ;i++)
        {
            if(msg.sender == signers[i])
            {
                if( _v[i] == v && _r[i] == r && _s[i] == s)
                {
                    emit addRepeatSignaturesEvent(grade,companyName,pledge,value,v,r,s);
                    return true;
                }
                else
                {
                     emit errorRepeatSignaturesEvent(grade,companyName,pledge,value,v,r,s,msg.sender);
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
            emit addSignaturesEvent(grade,companyName,pledge,value,v,r,s);
            return true;
       }
       else
       {
           emit errorAddSignaturesEvent(grade,companyName,pledge,value,v,r,s,msg.sender);
           return false;
       }
    }
    
    function getSigners()public constant returns(address[])
    {
         uint length = CreditSignersDataABI(signersAddr).getSignersSize();
         address[] memory signerList = new address[](length);
         for(uint i= 0 ;i<length ;i++)
         {
             signerList[i] = (CreditSignersDataABI(signersAddr).getSigner(i));
         }
         return signerList;
    }

    function getPledge() public constant returns(bool){
         return pledge;
    }

    function getValue() public constant returns(int256){
         return value;
    }

    function getGrade() public constant returns(string){
         return grade;
    }

    function getCompanyName() public constant returns(string){
         return companyName;
    }


}
