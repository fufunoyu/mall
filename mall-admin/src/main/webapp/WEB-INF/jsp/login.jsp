<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/5
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>管理员登录</title>
    <style>
        .body{
            background-image: url("//www.51pptmoban.com/d/file/2014/08/24/fa3534816e89cd2840cc52c6ec7eff7d.jpg");
            background-color: #6583af;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: bottom;
            height: 100%;
            width: 100%;
            color: #fff;

        }
        .boxlogin{
            margin: 50px auto;
            width: 500px;
            height: 320px;
            background: rgba(255, 255, 255, 0.25);
        }
        .titlogin{
            padding-top: 40px;
            font-size: 22px;
            font-weight: 100;
            color: rgba(255, 255, 255, 0.8);
        }
        .meslogin{
            font-size: 14px;
        }
        .meslogin td{
            display: inline-block;
            margin-bottom: 20px;
        }
        .rember{
            font-size: 12px;
        }
        .usermsg{
            width: 206px;
            height: 24px;
            padding: 5px;
            background: rgba(255, 255, 255, 0.7);
            border: none;
        }
        .sublogin input{
            width: 260px;
            height: 30px;
            color: #eee;
            font-size: 14px;
            background: rgba(51, 107, 135, 0.85);;
            border: none;
        }
        .sublogin input:hover{
            background: rgba(57, 113, 146, 0.85);
        }
        .sublogin input:active{
            background: #336b87;
        }
        .error{
            font-size: 12px;
            color: #a9758c;
            padding: 5px;
        }
    </style>
</head>
<body class="body">
<center>
    <div class="boxlogin">
        <h1 class="titlogin">管理员登录</h1>
        <form id="indexform" name="indexForm" action="${pageContext.request.contextPath}/loginSubmit" method="post">
            <c:if test="${!empty error}">
                <span class="error">${error}</span>
                <br>
                <br>
                <br>
            </c:if>
            <table  class="meslogin" border="0">
                <tr>
                    <td>账号：</td>
                    <td><input class="usermsg" type="text" value="${loginUser.username}" name="username" placeholder="请输入用户名"></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input class="usermsg" type="password" value="${loginUser.password}" name="password" placeholder="请输入密码">
                    </td>
                </tr>
                <%-- <tr>
                <td class="rember">记住我：</td>
                <td><input class="remberBtn" id="remberBtn" name="rememberMe" type="checkbox"></td>
            </tr>--%>
            </table>
            <div class="sublogin">
                <input type="submit" value="登 录">
            </div>
        </form>
    </div>

</center>
</body>
</html>
