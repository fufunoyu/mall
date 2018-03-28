<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    function showProductsAsideCategorys(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "white");
        $("div.eachCategory[cid=" + cid + "] a").css("color", "red");
        var row = $("div.productsAsideCategorys[cid=" + cid + "] div.row")
        if (row.children().length <=0) {
            $.ajax({
                url: '${pageContext.request.contextPath}/category/list.json?parentId=' + cid,
                method: 'get',
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var a = $("<a href='${pageContext.request.contextPath}/category/product/list?cid=" + data[i].id + "'>" + data[i].name + "</a>")
                        row.append(a)
                    }
                    row.append($("<div class='seperator'></div>"))
                }
            })
        }
        $("div.productsAsideCategorys[cid=" + cid + "]").show();
    }


    function hideProductsAsideCategorys(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "");

        $("div.eachCategory[cid=" + cid + "] a").css("color", "#fff");
        $("div.productsAsideCategorys[cid=" + cid + "]").hide();
    }

    $(function () {
        $("div.eachCategory").mouseenter(function () {
            var cid = $(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.eachCategory").mouseleave(function () {
            var cid = $(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseenter(function () {
            var cid = $(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseleave(function () {
            var cid = $(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });

        $("div.rightMenu span").mouseenter(function () {
            var left = $(this).position().left;
            var top = $(this).position().top;
            var width = $(this).css("width");
            var destLeft = parseInt(left) + parseInt(width) / 2;
            $("img#catear").css("left", destLeft);
            $("img#catear").css("top", top - 20);
            $("img#catear").fadeIn(500);

        });
        $("div.rightMenu span").mouseleave(function () {
            $("img#catear").hide();
        });

    });
</script>


<%--<img src="${pageContext.request.contextPath}/static/img/site/xiniu.jpg" id="catear" class="catear"/>--%>
<div class="categoryWithCarousel">
    <div class="headbar show1">
        <div class="head">
            <span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
            <span style="margin-left:10px">商品分类</span>
        </div>
        <div class="rightMenu">
            <c:forEach items="${categories}" var="category" varStatus="st">
                <c:if test="${st.count<=8}">
                    <span>
                        <a href="${pageContext.request.contextPath}/category/product/list?cid=${category.id}">${category.name}</a>
                    </span>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <div style="position: relative">
        <%@include file="categoryMenu.jsp" %>
    </div>

    <div style="position: relative;left: 0;top: 0;">
        <%@include file="productsAsideCategorys.jsp" %>
    </div>


    <%@include file="carousel.jsp" %>


    <div class="carouselBackgroundDiv">
    </div>

</div>





