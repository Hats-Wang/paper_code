pragma solidity ^0.4.4;
import "./Credit.sol";

contract CreditSignersData{
        address[] signers;
	event newCreditEvent(address addr);
        function newCredit(string grd, string name, bool p, int256 vl,uint8 v, bytes32 r,bytes32 s)public returns(address)
        {
            Credit credit = new Credit(grd, name, p, vl, v, r, s, this, msg.sender);
            newCreditEvent(credit);
            return credit;
        }

        function CreditSignersData(address[] creditSigners)public{
            for(uint i=0; i<creditSigners.length; ++i) {
            signers.push(creditSigners[i]);
			}
		}

    function verify(address addr)public constant returns(bool){
    for(uint i=0; i<signers.length; ++i) {
        if (addr == signers[i])
        {
            return true;
        }
    }
    return false;
}

    function getSigner(uint index)public constant returns(address){
        uint listSize = signers.length;
        if(index < listSize)
        {
            return signers[index];
        }
        else
        {
            return 0;
        }

    }

    function getSignersSize() public constant returns(uint){
        return signers.length;
    }

    function getSigners() public constant returns(address[]){
        return signers;
    }

}
