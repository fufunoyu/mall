<%--
  Created by IntelliJ IDEA.
  User: 314-d04
  Date: 2018/03/28
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.rhinoceros.mall.core.po.Product" %>
<div class="recommends">
    <h2>推荐商品</h2>
    <c:forEach items="${recommends}" var="product">
        <a href="${pageContext.request.contextPath}/product?pid=${product.id}">
            <img class="item" src="${fn:split(product.imageUrls,Product.IMAGE_SEPARATION)[0]}"/>
        </a>
    </c:forEach>
</div>
