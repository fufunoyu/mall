<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/16
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.rhinoceros.mall.core.enumeration.Gender" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/myInfo.css">
</head>
<body>
<div class="left">
    <div class="headImg">
        <img src="${user.avatar}" alt="头像">
        <div class="desc">
            <span>昵称：</span>${user.nickname}
        </div>
    </div>
</div>
<div class="right">
    <div class="info">
        基本资料
    </div>
    <ul>
        <li id="l_username">
            <label>真实姓名：</label>
            <span class="username" id="username">${user.username}</span>
        </li>
        <li id="l_gender">
            <label>性别：</label>
            <span class="gender" id="gender">${user.gender==Gender.FEMALE?'女':'男'}</span>
        </li>
        <li id="l_birthday">
            <label>生日：</label>
            <span class="birthday" id="birthday">
                <fmt:formatDate value="${user.birthday}" pattern="yyyy/MM/dd"/>
            </span>
        </li>
        <li id="l_email">
            <label>邮箱：</label>
            <span class="email" id="email">${user.email}</span>
        </li>
        <li id="l_telephone">
            <label>电话：</label>
            <span class="telephone" id="telephone">${user.telephone}</span>
        </li>
    </ul>
</div>
</body>
</html>
