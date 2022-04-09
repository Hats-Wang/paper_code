pragma solidity ^0.4.4;

import "./Credit.sol";

contract Mortgage{
    bool success = true;

    function Mortgage(address add, int256 num) public{
        Credit cre = Credit(add);
        int256 value = cre.getValue();
        if((value >= num) && (!cre.getPledge())) {
            cre.setPledge(true);
            cre.setAddMortgage(address(this));
            success = true;
        }
        else{
            success = false;
        }
    }


    function getSuc()public constant returns(bool){
        return success;
    }
}
