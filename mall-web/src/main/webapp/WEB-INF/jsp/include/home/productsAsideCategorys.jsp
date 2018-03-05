<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    // $(function () {
    //     $("div.productsAsideCategorys div.row a").each(function () {
    //         var v = Math.round(Math.random() * 6);
    //         if (v == 1)
    //             $(this).css("color", "#87CEFA");
    //     });
    // });

</script>
<c:forEach items="${categories}" var="category">
    <c:if test="${category.parentId==null}">
        <div cid="${category.id}" class="productsAsideCategorys">
            <div class="row show1">
                <c:forEach items="${categories}" var="subCategory">
                    <c:if test="${subCategory.parentId==category.id}">
                        <a href="${pageContext.request.contextPath}/category?cid=${subCategory.id}">${subCategory.name}</a>
                    </c:if>
                </c:forEach>
                <div class="seperator"></div>
            </div>
        </div>
    </c:if>
</c:forEach>
	
