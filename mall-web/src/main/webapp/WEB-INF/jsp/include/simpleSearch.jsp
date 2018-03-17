<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div>
    <a href="${pageContext.request.contextPath}/">
        <img id="simpleLogo" class="simpleLogo" src="${pageContext.request.contextPath}/static/img/site/logo.jpg">
    </a>

    <form action="${pageContext.request.contextPath}/product/search" method="post">
        <div class="simpleSearchDiv pull-right">
            <input type="text" placeholder="平衡车 原汁机" value="${query}" name="query">
            <button class="searchButton" type="submit">搜索</button>
        </div>
    </form>
    <div style="clear:both"></div>
</div>