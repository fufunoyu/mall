<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<nav class="top ">
    <a href="${pageContext.request.contextPath}/">
        <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
        民生商城首页
    </a>

    <span>欢迎来民生商城</span>

    <c:if test="${!empty sessionScope[ConstantValue.CURRENT_USER]}">
        <a href="${pageContext.request.contextPath}/user/me">${sessionScope[ConstantValue.CURRENT_USER].nickname}</a>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </c:if>

    <c:if test="${empty sessionScope[ConstantValue.CURRENT_USER]}">
        <a href="${pageContext.request.contextPath}/login">请登录</a>
        <a href="${pageContext.request.contextPath}/register">免费注册</a>
    </c:if>


    <span class="pull-right">
			<a href="${pageContext.request.contextPath}/order/list">我的订单</a>
        <a href="${pageContext.request.contextPath}/cart/list">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
			购物车
            </a>
    </span>
</nav>



