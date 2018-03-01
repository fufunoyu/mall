<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {
        $("div.productsAsideCategorys div.row a").each(function () {
            var v = Math.round(Math.random() * 6);
            if (v == 1)
                $(this).css("color", "#87CEFA");
        });
    });

</script>
<c:forEach items="${categories}" var="categoryVo">
    <div cid="${categoryVo.category.id}" class="productsAsideCategorys">
        <c:forEach items="${categoryVo.children}" var="category">
            <div class="row show1">
                <a href="category?cid=${category.id}">${category.name}</a>
                <div class="seperator"></div>
            </div>
        </c:forEach>
    </div>
</c:forEach>
	
