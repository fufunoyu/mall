<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%--京东样式--%>
<%--<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/static/css/comment/jd1.css"/>
<script type="text/javascript">window.pageConfig={compatible:true};</script>
<script type="text/javascript" src="//misc.360buyimg.com/jdf/1.0.0/unit/??base/5.0.0/base.js"></script>
<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/static/css/comment/jd2.css" source="widget"/>
<link rel="icon" href="//www.jd.com/favicon.ico" mce_href="//www.jd.com/favicon.ico" type="image/x-icon">--%>
<%--京东样式--%>

<c:forEach items="${orderListVo.orderProductVos}" var="oi" varStatus="st">
    <div class="reviewDiv">
        <div class="reviewProductInfoDiv">
            <div class="reviewProductInfoImg"><img width="400px" height="400px"
                                                   src="${oi.productVo.firstImageUrl}">
            </div>
            <div class="reviewProductInfoRightDiv">
                <div class="reviewProductInfoRightText">
                        ${oi.productVo.product.name}
                </div>
                <table class="reviewProductInfoTable">
                    <tr>
                        <td width="75px">价格:</td>
                        <td><span class="reviewProductInfoTablePrice">￥<fmt:formatNumber type="number"
                                                                                         value="${oi.productVo.product.discount}"
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
                        <td><span class="reviewProductInfoTableSellNumber">${oi.productVo.product.saleNum}</span> 件</td>
                    </tr>
                </table>

                <div class="reviewProductInfoRightBelowDiv">
                    <span class="reviewProductInfoRightBelowImg"><img1
                            src="${pageContext.request.contextPath}/static/img/site/reviewLight.png"></img1></span>
                    <span class="reviewProductInfoRightBelowText">现在查看的是 您所购买商品的信息
于<fmt:formatDate value="${orderListVo.order.createAt}" pattern="yyyy年MM月dd"/>下单购买了此商品 </span>

                </div>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="reviewStasticsDiv">
            <div class="reviewStasticsLeft">
                <div class="reviewStasticsLeftTop"></div>
                <div class="reviewStasticsLeftContent">累计评价 <span class="reviewStasticsNumber"> ${oi.productVo.product.commentNum}</span>
                </div>
                <div class="reviewStasticsLeftFoot"></div>
            </div>
            <div class="reviewStasticsRight">
                <div class="reviewStasticsRightEmpty"></div>
                <div class="reviewStasticsFoot"></div>
            </div>
        </div>

        <c:if test="${param.showonly==true}">
            <div class="reviewDivlistReviews">
                <c:forEach items="${reviews}" var="r">
                    <div class="reviewDivlistReviewsEach">
                        <div class="reviewDate"><fmt:formatDate value="${r.createDate}" pattern="yyyy-MM-dd"/></div>
                        <div class="reviewContent">${r.content}</div>
                        <div class="reviewUserInfo pull-right">${r.user.anonymousName}<span
                                class="reviewUserInfoAnonymous">(匿名)</span></div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${param.showonly!=true}">
            <div class="makeReviewDiv">
                <form method="post" action="foredoreview">
                    <div class="makeReviewText">分享体验心得，给万千想买的人一个参考</div>
                    <table class="makeReviewTable">
                        <tr>
                            <td class="makeReviewTableFirstTD">评价商品</td>
                            <td><textarea name="content"></textarea></td>
                        </tr>
                    </table>
                    <div class="makeReviewButtonDiv">
                        <input type="hidden" name="oid" value="${o.id}">
                        <input type="hidden" name="pid" value="${p.id}">
                        <button type="submit">提交评价</button>
                    </div>
                </form>
            </div>
        </c:if>

    </div>

</c:forEach>

<%--京东样式--%>
<%--
<div class="f-item f-goods product-15026724332" voucherStatus="0" cateFi="1672" cateSe="2576" cateTh="2584">
    <div class="fi-info">
        <div class="comment-goods">
            <div class="p-img">
                <a clstag="pageclick|keycount|fabupingjia_201608055|2" href="//item.jd.com/15026724332.html" target="_blank">
                    <img src="//img30.360buyimg.com/n4/jfs/t5911/99/8582587101/114894/e1cddbfc/598aafb8Nfe0078b0.jpg" alt="">
                </a>
            </div>
            <div class="p-name">
                <a clstag="pageclick|keycount|fabupingjia_201608055|3" href="//item.jd.com/15026724332.html" target="_blank">POLO 耳机数据线收纳盒 赠品，勿拍，单独拍不发货</a>
            </div>
            <div class="p-price">
                <strong></strong>
            </div>
            <div class="p-attr"> </div>
        </div>
    </div>
    <div class="fi-operate">
        <div class="fop-item fop-star  ">
            <div class="fop-label">商品评分</div>
            <div class="fop-main">
                <span class="commstar">
                    <span class="star star1">
                        <i class="face"></i>
                    </span>
                    <span class="star star2">
                        <i class="face"></i>
                    </span>
                    <span class="star star3">
                        <i class="face"></i>
                    </span>
                    <span class="star star4">
                        <i class="face"></i>
                    </span>
                    <span class="star star5">
                        <i class="face"></i>
                    </span>
                    <span class="star-info">0分</span>
                </span>
            </div>
            <div class="fop-tip">
                <i class="tip-icon"></i>
                <em class="tip-text"></em>
            </div>
        </div>

        <div class="fop-item ">
            <div class="fop-label">评价晒单</div>
            <div class="fop-main">
                <div class="f-textarea">
                    <textarea name="" id="" placeholder="分享体验心得，给万千想买的人一个参考~"></textarea>
                    <div class="textarea-ext">
                        <em class="textarea-num">
                            <b>0</b> / 500</em>
                        <span class="tips">（评价多于
                            <span class="ftc1">10</span>个字,有机会奖励京豆哦~）</span>
                    </div>
                </div>
            </div>
            <div class="fop-tip">
                <i class="tip-icon"></i>
                <em class="tip-text"></em>
            </div>
        </div>

    </div>
</div>--%>
