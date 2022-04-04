pragma solidity ^0.4.4;

import "./Credit.sol";

contract Mortgage{
    bool success = true;

    function mortgage(address add, int256 num) public{
        Credit cre = Credit(add);
        int256 value = cre.getValue();
        if((value >= num) && (!cre.getPledge())) {
            cre.setPledge(true);
            success = true;
        }
        else{
            success = false;
        }
    }

    function redeem(address add)public{
        Credit cre = Credit(add);
        cre.setPledge(false);
    }

    function getSuc()public constant returns(bool){
        return success;
    }
}
