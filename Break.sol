pragma solidity ^0.4.4;

import "./Credit.sol";
import "./Borrow.sol";

contract Break{
     function Break(address add) public {
         Credit cre = Credit(add);
         Borrow b = Borrow(cre.getAddBorrow());
         if(b.getFlag()) {
             int256 grd = cre.getGrade();
             cre.setGrade(grd - 5);
             b.setFlag(false);
         }
     }
}
