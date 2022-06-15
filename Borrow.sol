pragma solidity ^0.4.4;

import "./Credit.sol";
import"./Mortgage.sol";

contract Borrow{
    Credit cre;
    int256 month;
    int256 money;
    bool flag;//表示借贷是否还能影响credit grade


    function Borrow(address add, int256 time, int256 num) public{
        cre = Credit(add);
        month = time;
        money = num;
        if(cre.getPledge() || cre.getGrade() < 60) {
            flag = false;
            return ;
        }

        Mortgage m = new Mortgage(add, num);
        if(m.getSuc()){
            flag = true;
            cre.setAddBorrow(address(this));
        }else{
            flag = false;
        }
    }


    function getFlag() public constant returns (bool){
        return flag;
    }

    function getMonth() public constant returns (int256){
        return month;
    }

    function setFlag(bool b) public{
        flag = b;
    }

}
