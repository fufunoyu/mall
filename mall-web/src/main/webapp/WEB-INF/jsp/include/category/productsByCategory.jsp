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
            total: '${(total+page.size)/page.size}',
            //总数据条数
            totalRecords: '${total}',
            //链接前部
            hrefFormer: '',
            //链接尾部
            hrefLatter: '',
            mode: 'link', //可选，默认就是link
            //链接算法
            getLink: function (n) {
                return this.hrefFormer + '?cid=${category.id}&page=' + n + "${sorts.size()>0?'&sort='.concat(sorts[0]):''}"
            }

        });
    });

</script>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

<div class="categoryProducts">
    <c:forEach items="${products}" var="p" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="productUnit" price="${p.product.discount}">
                <div class="productUnitFrame">
                    <a href="${pageContext.request.contextPath}/product?pid=${p.product.id}">
                        <img class="productImage" src="${p.firstImageUrl}">
                    </a>
                    <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.product.discount}"
                                                                  minFractionDigits="2"/></span>
                    <a class="productLink" href="${pageContext.request.contextPath}/product?pid=${p.product.id}">
                            ${fn:substring(p.product.name, 0, 50)}
                    </a>

                    <div class="show1 productInfo">
                        <span class="monthDeal ">成交量 <span class="productDealNumber">${p.product.saleNum}笔</span></span>
                        <span class="productReview">评价<span
                                class="productReviewNumber">${p.product.commentNum}</span></span>
                            <%--<span class="wangwang">
                                <a class="wangwanglink" href="#nowhere">
                                    <img src="${pageContext.request.contextPath}/static/img/site/wangwang.png">
                                </a>
                            </span>--%>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div style="clear:both"></div>
    <%--显示kkpager--%>
    <div id="kkpager"></div>
</div>