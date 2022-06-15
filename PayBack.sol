pragma solidity ^0.4.4;

import "./Credit.sol";
import "./Borrow.sol";

contract PayBack {
    function PayBack(address add)public {
        Credit cre = Credit(add);
        cre.setPledge(false);
        int256 grd = cre.getGrade();
        if(grd < 100){
            cre.setGrade(grd + 1);
        }
        Borrow b = Borrow(cre.getAddBorrow());
        b.setFlag(false);
    }
}
