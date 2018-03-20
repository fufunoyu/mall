<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/16
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<html>
<head>
    <title>我的评价</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/myAddress.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>

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
                total: '${(commentNum+10-1)/10}',
                //总数据条数
                totalRecords: '${commentNum}',
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
                    return this.hrefFormer + '?page=' + n + '&uid=${user.id}' + this.hrefLatter + '#comment';
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
            });
        </script>
    </c:if>

</head>
<body>
<div class="left">
    <div class="headImg">
        <img src="${user.avatar}" alt="头像">
        <div class="desc">
            <span>昵称：</span>${user.nickname}
        </div>
    </div>
</div>
<div class="right">
    <div class="info">
        我的评价
    </div>
    <div id="comment" class="productReviewDiv">
        <div class="productReviewContentPart">
            <c:forEach items="${comments}" var="r">
                <div class="productReviewItem">

                    <div class="productReviewItemDesc">
                        <div class="productReviewItemContent">
                                ${r.content }
                        </div>
                        <div class="productReviewItemDate"><fmt:formatDate value="${r.createAt}"
                                                                           pattern="yyyy-MM-dd"/></div>
                    </div>
                    <div class="productReviewItemUserInfo">
                        <span class="userInfoGrayPart">${user.nickname}</span>
                    </div>

                    <div style="clear:both"></div>

                </div>
            </c:forEach>

            <%--显示kkpager--%>
            <div id="kkpager"></div>

        </div>

    </div>


</div>
</body>
</html>
