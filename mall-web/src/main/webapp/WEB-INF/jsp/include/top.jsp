<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top ">
    <a href="/">
        <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
        民生商城首页
    </a>

    <span>欢迎来民生商城</span>

    <c:if test="${!empty user}">
        <a href="/user/me">${user.nickname}</a>
        <a href="/logout">退出</a>
    </c:if>

    <c:if test="${empty user}">
        <a href="/login">请登录</a>
        <a href="/register">免费注册</a>
    </c:if>


    <span class="pull-right">
			<a href="/order/list">我的订单</a>
			<a href="/cart/list">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
			购物车
		</span>


</nav>



