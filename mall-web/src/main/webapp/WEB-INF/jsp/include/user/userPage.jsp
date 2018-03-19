<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/15
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/userInfo.css">

<div class="container-fluid">
    <%--导航栏--%>
    <ul class="sidenav col-md-2">
        <h2>
            个人中心
        </h2>
        <li><a href="${pageContext.request.contextPath}/user/detail" target="iframe_a">查看个人资料</a></li>
        <li><a href="${pageContext.request.contextPath}/user/modify" target="iframe_a">修改个人资料</a></li>
        <li><a href="${pageContext.request.contextPath}/user/address" target="iframe_a">收货地址管理</a></li>
        <li><a href="${pageContext.request.contextPath}/user/evaluation" target="iframe_a">评价管理</a></li>
        <li><a href="${pageContext.request.contextPath}/cart/list">我的购物车</a></li>
        <li><a href="${pageContext.request.contextPath}/order/list">我的订单</a></li>
    </ul>
    <%--内容--%>
    <iframe class="content col-md-10" src="${pageContext.request.contextPath}/user/detail"
            name="iframe_a">
    </iframe>
</div>



