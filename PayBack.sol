pragma solidity ^0.4.4;

import "./Credit.sol";
import "./Borrow.sol";

contract PayBack {
    function PayBack(address add)public {
        Credit cre = Credit(add);
        cre.setPledge(false);
        Borrow b = Borrow(cre.getAddBorrow());
        b.setFlag(false);
    }
}
