<%--
  Created by IntelliJ IDEA.
  User: wzm
  Date: 4/19/22
  Time: 5:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>企业征信共享系统</title>
  </head>

  <style>
    body{
      background: url("./picture/back.jpg")center no-repeat;
      background-size:100%,100%;
    }

    div{
      float:right;
      margin:2cm 4cm 3cm 4cm;
    }

  </style>


  <body>
  <img src="./picture/big.png" height="60" width="60">

  <div>
  <form action="/deploy" method="get">
    <button>
      <img src="./picture/deploy.png" height="80" width="80"><br>
      获取接口地址
    </button>
  </form><br>

  <a href="http://127.0.0.1:8081/new.html">
    <button>
      <img src="./picture/new.png" height="80" width="80"><br>
      注册信用信息
    </button>
  </a><br><br>

  <a href="http://127.0.0.1:8081/borrow.html">
    <button>
      <img src="./picture/borrow.png" height="80" width="80"><br>
      申请贷款
    </button>
  </a><br><br>

  <a href="http://127.0.0.1:8081/pay.html">
    <button>
      <img src="./picture/pay.png" height="80" width="80"><br>
      偿还贷款
    </button>
  </a><br><br>

  <a href="http://127.0.0.1:8081/get.html">
    <button>
      <img src="./picture/get.png" height="80" width="80"><br>
      查询信用信息
    </button>
  </a>


  </body>
</html>
