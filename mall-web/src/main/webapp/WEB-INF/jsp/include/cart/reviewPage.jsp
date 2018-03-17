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
            total: '${(orderVo.productVo.product.commentNum+10-1)/10}',
            //总数据条数
            totalRecords: '${orderVo.productVo.product.commentNum}',
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
                return this.hrefFormer + '?page=' + n + '&oid=${orderVo.order.id}' + this.hrefLatter + '#orderComment';
                <%--if (n == 1) {--%>
                <%--return this.hrefFormer + this.hrefLatter + '?pid= ${productVo.product.id}';--%>
                <%--}--%>
                <%--return this.hrefFormer + this.hrefLatter + '?page=' + n + '&pid=${productVo.product.id}';--%>

            }

        });
        //提交订单按钮
        $("form.submitComment").submit(function () {
            if ($("#content").val().trim().length < 5 ) {
                $("span.errorMessage").html("请输入至少5个字符");
                return false;
            }
            if ($("#content").val().trim().length > 500) {
                $("span.errorMessage").html("最多可输入500个字符");
                return false;
            }
            return true;
        });
    });

</script>



    <div class="reviewDiv">
        <div class="reviewProductInfoDiv">
            <div class="reviewProductInfoImg"><img width="400px" height="400px"
                                                   src="${orderVo.productVo.firstImageUrl}">
            </div>
            <div class="reviewProductInfoRightDiv">
                <div class="reviewProductInfoRightText">
                    <a  href="${pageContext.request.contextPath}/product?pid=${orderVo.productVo.product.id}" class="orderItemProductLink">
                        ${orderVo.productVo.product.name}
                    </a>
                </div>
                <table class="reviewProductInfoTable">
                    <tr>
                        <td width="75px">价格:</td>
                        <td><span class="reviewProductInfoTablePrice">￥<fmt:formatNumber type="number"
                                                                                         value="${orderVo.productVo.product.discount}"
                                                                                         minFractionDigits="2"/></span>
                            元
                        </td>
                    </tr>
                    <tr>
                        <td>配送</td>
                        <td>快递: 0.00</td>
                    </tr>
                    <tr>
                        <td>销量:</td>
                        <td><span class="reviewProductInfoTableSellNumber">${orderVo.productVo.product.saleNum}</span> 件</td>
                    </tr>
                </table>

                <div class="reviewProductInfoRightBelowDiv">
                    <span class="reviewProductInfoRightBelowImg"><img1
                            src="${pageContext.request.contextPath}/static/img/site/reviewLight.png"></img1></span>
                    <span class="reviewProductInfoRightBelowText">现在查看的是 您所购买商品的信息于<fmt:formatDate value="${orderVo.order.createAt}" pattern="yyyy年MM月dd"/>下单购买了此商品 </span>

                </div>
            </div>
            <div style="clear:both"></div>
        </div>

        <c:if test="${orderVo.order.status=='COMPLETED'}">
            <div class="completeReviewDiv">
                <div class="orderFinishTextDiv">
                    <img src="${pageContext.request.contextPath}/static/img/site/commentFinish.png">
                    <span>评论成功，感谢您分享体验心得</span>
                </div>
            </div>
        </c:if>

        <c:if test="${orderVo.order.status=='WAIT_COMMENT'}">
            <div class="makeReviewDiv">
                <form class="submitComment" method="post" action="${pageContext.request.contextPath}/order/completeComment">
                    <div class="makeReviewText">分享体验心得，给万千想买的人一个参考</div>
                    <table class="makeReviewTable">
                        <tr>
                            <td class="makeReviewTableFirstTD">评价商品</td>
                            <td><textarea id="content" name="content"></textarea></td>
                        </tr>
                    </table>
                    <div class="makeReviewButtonDiv">
                        <input type="hidden" name="oid" value="${orderVo.order.id}">
                        <div>
                        <span class="errorMessage"></span>
                        </div>
                        <button type="submit">提交评价</button>
                    </div>
                </form>
            </div>
        </c:if>

        <div class="reviewStasticsDiv" id="orderComment">
            <div class="reviewStasticsLeft">
                <div class="reviewStasticsLeftTop"></div>
                <div class="reviewStasticsLeftContent">累计评价 <span
                        class="reviewStasticsNumber"> ${orderVo.productVo.product.commentNum}</span>
                </div>
                <div class="reviewStasticsLeftFoot"></div>
            </div>
            <div class="reviewStasticsRight">
                <div class="reviewStasticsRightEmpty"></div>
                <div class="reviewStasticsFoot"></div>
            </div>
        </div>
        <%--评论列表--%>
        <div class="reviewDivlistReviews">
            <c:forEach items="${comments}" var="r">
                <div class="reviewDivlistReviewsEach">
                    <div class="reviewDate"><fmt:formatDate value="${r.comment.createAt}" pattern="yyyy-MM-dd"/></div>
                    <div class="reviewContent">${r.comment.content}</div>
                    <div class="reviewUserInfo pull-right">${r.user.nickname}
                        <%--<span class="reviewUserInfoAnonymous">(匿名)</span>--%>
                    </div>
                </div>
            </c:forEach>
        </div>
        <%--显示kkpager--%>
        <div id="kkpager"></div>
    </div>





