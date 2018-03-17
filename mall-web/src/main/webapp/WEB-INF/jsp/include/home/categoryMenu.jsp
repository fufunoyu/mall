<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script>
</script>
<div class="categoryMenu">
    <c:forEach items="${categories}" var="category">
        <div cid="${category.id}" class="eachCategory">
            <span class="glyphicon glyphicon-link"></span>
            <a href="${pageContext.request.contextPath}/category/product/list?cid=${category.id}">
                    ${category.name}
            </a>
        </div>
    </c:forEach>
</div>