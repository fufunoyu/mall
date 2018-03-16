<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

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
</div>
