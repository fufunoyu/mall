<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/16
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>
<style>
    .sLogo{
        padding: 22px 50px;
        border-bottom: 2px solid #575757;
        background-image: url(${pageContext.request.contextPath}/static/img/site/topbg0.jpg);
        background-repeat: no-repeat;
        background-size: cover;
    }
</style>
<div class="sLogo">
    <a href="${pageContext.request.contextPath}/"><img
            src="${pageContext.request.contextPath}/static/img/site/logo.jpg"></a>
</div>

<%@include file="include/user/userPage.jsp"%>
</body>
</html>
