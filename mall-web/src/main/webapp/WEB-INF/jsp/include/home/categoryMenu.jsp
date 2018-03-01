<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div class="categoryMenu">
    <c:forEach items="${categories}" var="categoryVo">
        <div cid="${categoryVo.category.id}" class="eachCategory">
            <span class="glyphicon glyphicon-link"></span>
            <a href="category?cid=${categoryVo.category.id}">
                    ${categoryVo.category.name}
            </a>
        </div>
    </c:forEach>
</div>