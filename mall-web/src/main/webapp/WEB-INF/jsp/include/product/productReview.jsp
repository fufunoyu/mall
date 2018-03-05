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
            pno: '${nowPage}',
            //总页码
            total: '${(productVo.product.commentNum+10-1)/10}',
            //总数据条数
            totalRecords: '${productVo.product.commentNum}',
            //链接前部
            hrefFormer: '',
            //链接尾部
            hrefLatter: '',
            mode: 'link', //可选，默认就是link
            //链接算法
            getLink: function (n) {
                //这里是默认算法，算法适用于比如：
                //hrefFormer=http://www.xx.com/news/20131212
                //hrefLatter=.html
                //那么首页（第1页）就是http://www.xx.com/news/20131212.html
                //第2页就是http://www.xx.com/news/20131212_2.html
                //第n页就是http://www.xx.com/news/20131212_n.html
                return this.hrefFormer + '?page=' + n + '&pid=${productVo.product.id}' + this.hrefLatter + '#comment';
                <%--if (n == 1) {--%>
                <%--return this.hrefFormer + this.hrefLatter + '?pid= ${productVo.product.id}';--%>
                <%--}--%>
                <%--return this.hrefFormer + this.hrefLatter + '?page=' + n + '&pid=${productVo.product.id}';--%>

            }

        });
    });

</script>

<c:if test="${isComment}">
    <script>
        $(function () {
            $("div.productReviewDiv").show();
            $("div.productDetailDiv").hide();
        });
    </script>
</c:if>

<div id="comment" class="productReviewDiv">
    <div class="productReviewTopPart">
        <a href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
        <a href="#nowhere" class="selected">累计评价 <span
                class="productReviewTopReviewLinkNumber">${productVo.product.commentNum}</span> </a>
    </div>


    <div class="productReviewContentPart">
        <c:forEach items="${comments}" var="r">
            <div class="productReviewItem">

                <div class="productReviewItemDesc">
                    <div class="productReviewItemContent">
                            ${r.comment.content }
                    </div>
                    <div class="productReviewItemDate"><fmt:formatDate value="${r.comment.createAt}"
                                                                       pattern="yyyy-MM-dd"/></div>
                </div>
                <div class="productReviewItemUserInfo">
                    <span class="userInfoGrayPart">${r.user.nickname}</span>
                </div>

                <div style="clear:both"></div>

            </div>
        </c:forEach>

        <%--显示kkpager--%>
        <div id="kkpager"></div>

    </div>

</div>
