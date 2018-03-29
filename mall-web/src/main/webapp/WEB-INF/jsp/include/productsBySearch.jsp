<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%--引入kkpager--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/kkpager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/kkpager/kkpager_orange.css"/>
<%--使用kkpager--%>
<script type="text/javascript">
    $(function () {
        //生成分页控件
        kkpager.generPageHtml({
            pno: '${page.page}',
            //总页码
            total: '${(total+page.size-1)/page.size}',
            //总数据条数
            totalRecords: '${total}',
            //链接前部
            hrefFormer: '',
            //链接尾部
            hrefLatter: '',
            mode: 'click', //可选，默认就是link
            //链接算法
            click: function (n) {
                $("#search_form").attr('action','${pageContext.request.contextPath}/product/search?page='+n)
                $("#search_form").submit()
            }

        });
    });

</script>
<div class="searchProducts">
    <c:forEach items="${productList}" var="productVo">
        <div class="productUnit" price="${productVo.product.discount}">
            <a href="${pageContext.request.contextPath}/product?pid=${productVo.product.id}">
                <img class="productImage" src="${productVo.firstImageUrl}">
            </a>
            <span class="productPrice">¥<fmt:formatNumber type="number" value="${productVo.product.discount}"
                                                          minFractionDigits="2"/></span>
            <a class="productLink" href="${pageContext.request.contextPath}/product?pid=${productVo.product.id}">
                    ${fn:substring(productVo.product.name, 0, 50)}
            </a>

            <a class="tmallLink" href="${pageContext.request.contextPath}/product?pid=${productVo.product.id}">天猫专卖</a>
            <div class="show1 productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">${productVo.product.saleNum}笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">${productVo.product.commentNum}</span></span>
                <span class="wangwang"><img
                        src="${pageContext.request.contextPath}/static/img/site/wangwang.png"></span>
            </div>
        </div>
    </c:forEach>
    <c:if test="${empty productList}">
        <div class="noMatch">没有满足条件的产品
        </div>
    </c:if>
    <div style="clear:both"></div>
    <%--显示kkpager--%>
    <div id="kkpager"></div>
</div>
