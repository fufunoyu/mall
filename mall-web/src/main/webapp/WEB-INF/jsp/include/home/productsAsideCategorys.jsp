<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<c:forEach items="${categories}" var="category">
    <div cid="${category.id}" class="productsAsideCategorys">
        <div class="row show1">
            <c:forEach items="${categories}" var="subCategory">
                <a href="${pageContext.request.contextPath}/category/product/list?cid=${subCategory.id}">${subCategory.name}</a>
            </c:forEach>
            <div class="seperator"></div>
        </div>
    </div>
</c:forEach>
	
