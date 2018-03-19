<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page import="com.rhinoceros.mall.core.constant.ConstantValue" %>
<%@ page import="com.rhinoceros.mall.core.po.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%--引入kkpager--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/kkpager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/kkpager/kkpager_orange.css"/>

<script>
    var deleteOrder = false;
    var deleteOrderid = 0;

    <%--使用kkpager--%>
    $(function () {
        //生成分页控件
        kkpager.generPageHtml({
            pno: '${nowPage}',
            //总页码
            total: '${(orderNum+pageSize-1)/pageSize}',
            //总数据条数
            totalRecords: '${orderNum}',
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
                if("${orderStatus}" == "ALL"){
                    return this.hrefFormer + '?page=' + n + this.hrefLatter;
                }else{
                    return this.hrefFormer + '?page=' + n + '&status=' + '${orderStatus}' + this.hrefLatter;
                }
                <%--if (n == 1) {--%>
                <%--return this.hrefFormer + this.hrefLatter + '?pid= ${productVo.product.id}';--%>
                <%--}--%>
                <%--return this.hrefFormer + this.hrefLatter + '?page=' + n + '&pid=${productVo.product.id}';--%>

            }

        });
    });


    $(function () {
        $("a[orderStatus]").click(function () {
            var orderStatus = $(this).attr("orderStatus");
            /*if ('all' == orderStatus) {
                $("table[orderStatus]").show();
            }
            else {
                $("table[orderStatus]").hide();
                $("table[orderStatus=" + orderStatus + "]").show();
            }*/

            /*$("div.orderType div").removeClass("selectedOrderType");
            $(this).parent("div").addClass("selectedOrderType");*/
            if(orderStatus == "ALL"){
                window.location.href = 'list' + '?page=' + 1 ;
            }else{
                window.location.href = 'list' + '?page=' + 1 + '&status=' + orderStatus ;
            }

        });
        //判断订单状态
        var orderHStatus = "${orderStatus}"
        if(orderHStatus != null){
            // $("div.selectedOrderType").removeClass("selectedOrderType");
            $("#button_${orderStatus}").addClass("selectedOrderType");

            if ('ALL' == orderHStatus) {
                $("table[orderStatus]").show();
            }
            else {
                $("table[orderStatus]").hide();
                $("table[orderStatus=" + orderHStatus + "]").show();
            }
        }

        $("a.deleteOrderLink").click(function () {
            deleteOrderid = $(this).attr("oid");
            deleteOrder = false;
            $("#deleteConfirmModal").modal("show");
        });

        $("button.deleteConfirmButton").click(function () {
            deleteOrder = true;
            $("#deleteConfirmModal").modal('hide');
        });

        $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
            if (deleteOrder) {
                var page = "foredeleteOrder";
                $.post(
                    page,
                    {"oid": deleteOrderid},
                    function (result) {
                        if ("success" == result) {
                            $("table.orderListItemTable[oid=" + deleteOrderid + "]").hide();
                        }
                        else {
                            location.href = "login.jsp";
                        }
                    }
                );

            }
        })

        $(".ask2delivery").click(function () {
            var link = $(this).attr("link");
            $(this).hide();
            page = link;
            $.ajax({
                url: page,
                success: function (result) {
                    alert("卖家已秒发，刷新当前页面，即可进行确认收货")
                }
            });

        });
    });



</script>

<div class="boughtDiv">
    <div class="orderType">
        <div id="button_ALL" ><a orderStatus="ALL" href="#nowhere">所有订单</a></div>
        <div id="button_WAIT_PAY"><a orderStatus="WAIT_PAY" href="#nowhere">待付款</a></div>
        <div id="button_WAIT_SHIP"><a orderStatus="WAIT_SHIP" href="#nowhere">待发货</a></div>
        <div id="button_WAIT_RECEIVE"><a orderStatus="WAIT_RECEIVE" href="#nowhere">待收货</a></div>
        <div id="button_WAIT_COMMENT"><a orderStatus="WAIT_COMMENT" href="#nowhere">待评价</a></div>
        <div id="button_COMPLETED"><a orderStatus="COMPLETED" href="#nowhere">已完成</a></div>
        <div id="button_WAIT_RETURN"><a orderStatus="WAIT_RETURN" href="#nowhere">待退货</a></div>
        <div id="button_RETURN_ING"><a orderStatus="RETURN_ING" href="#nowhere">退货中</a></div>
        <div id="button_RETURN_COMPLETED"><a orderStatus="RETURN_COMPLETED" href="#nowhere">退货成功</a></div>
        <div id="button_CANCEL"><a orderStatus="CANCEL" href="#nowhere" class="noRightborder">已取消</a></div>
        <div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
    </div>
    <div style="clear:both"></div>
    <div class="orderListTitle">
        <table class="orderListTitleTable">
            <tr>
                <td>宝贝</td>
                <td width="100px">单价</td>
                <td width="100px">数量</td>
                <td width="120px">实付款</td>
                <td width="100px">交易操作</td>
            </tr>
        </table>

    </div>

    <div class="orderListItem">
        <c:forEach items="${orderVos}" var="o">
            <table class="orderListItemTable" orderStatus="${o.order.status}" oid="${o.order.id}">
                <tr class="orderListItemFirstTR">
                    <td colspan="2">
                        <b><fmt:formatDate value="${o.order.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/></b>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="dingdanhao">订单号: </span>
                        <span>${o.order.identifier}</span>
                    </td>
                    <td colspan="2">
                        <%--<img width="13px" src="img/site/orderItemTmall.png">--%>
                        民生商城</td>
                    <td colspan="1">
                        <%--<a class="wangwanglink" href="#nowhere">
                            <div class="orderItemWangWangGif"></div>
                        </a>--%>

                    </td>
                    <td class="orderItemDeleteTD">
                        <%--<a class="deleteOrderLink" oid="${o.order.id}" href="#nowhere">
                            <span class="orderListItemDelete glyphicon glyphicon-trash"></span>
                        </a>--%>

                    </td>
                </tr>
                <%--<c:forEach items="${o.orderProductVos}" var="oi" varStatus="st">--%>
                    <tr class="orderItemProductInfoPartTR">
                        <td class="orderItemProductInfoPartTD"><img width="80" height="80"
                                                                    src="${o.productVo.firstImageUrl}"></td>
                        <td class="orderItemProductInfoPartTD">
                            <div class="orderListItemProductLinkOutDiv">
                                <a href="${pageContext.request.contextPath}/product?pid=${o.productVo.product.id}">${o.productVo.product.name}</a>
                                <div class="orderListItemProductLinkInnerDiv">
                                    <img src="${pageContext.request.contextPath}/static/img/site/creditcard.png"
                                         title="支持信用卡支付">
                                    <img src="${pageContext.request.contextPath}/static/img/site/7day.png"
                                         title="消费者保障服务,承诺7天退货">
                                    <img src="${pageContext.request.contextPath}/static/img/site/promise.png"
                                         title="消费者保障服务,承诺如实描述">
                                </div>
                            </div>
                        </td>
                        <td class="orderItemProductInfoPartTD" width="100px">

                            <div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number"
                                                                                              value="${o.productVo.product.price}"
                                                                                              minFractionDigits="2"/></div>
                            <div class="orderListItemProductPrice">￥<fmt:formatNumber type="number"
                                                                                      value="${o.productVo.product.discount}"
                                                                                      minFractionDigits="2"/></div>


                        </td>
                        <td align="center" class="orderItemProductInfoPartTD" width="100px">
                            <span class="orderListItemNumber">${o.order.productNum}</span>
                        </td>

                        <%--<c:if test="${st.count==1}">--%>
                            <%--<td valign="top" rowspan="${fn:length(o.orderProductVos)}" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
                                <span class="orderListItemNumber">${oi.num}</span>
                            </td>--%>

                            <td valign="top" rowspan="1" width="120px" <%--rowspan="${fn:length(o.productVo)}"--%>
                                class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
                                <div class="orderListItemProductRealPrice">￥<fmt:formatNumber minFractionDigits="2"
                                                                                              maxFractionDigits="2"
                                                                                              type="number"
                                                                                              value="${o.order.totalPrice}"/></div>
                                <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                            </td>
                            <td valign="top" rowspan="1" align="center" <%--rowspan="${fn:length(o.productVo)}"--%>
                                class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
                                <c:if test="${o.order.status=='WAIT_RECEIVE' }">
                                    <a href="${pageContext.request.contextPath}/order/confirmPayPage?oid=${o.order.id}">
                                        <button class="orderListItemConfirm">确认收货</button>
                                    </a>
                                    <div class="cancleWaitPayOrder">
                                        <a href="${pageContext.request.contextPath}/order/returnOrder?oid=${o.order.id}">申请退货</a>
                                    </div>
                                </c:if>
                                <c:if test="${o.order.status=='WAIT_PAY' }">

                                    <button class="orderListItemConfirm">付款</button>
                                    <div class="cancleWaitPayOrder">
                                    <a href="${pageContext.request.contextPath}/order/cancelOrder?oid=${o.order.id}">取消订单</a>
                                    </div>
                                </c:if>
                                    <%--<c:if test="${o.order.status=='WAIT_PAY' }">
                                    <a href="forealipay?oid=${o.id}&total=${o.total}">
                                        <button class="orderListItemConfirm">付款</button>
                                    </a>
                                </c:if>--%>

                                <c:if test="${o.order.status=='WAIT_SHIP' }">
                                    <span>待发货</span>
                                    <div class="cancleWaitPayOrder">
                                        <a href="${pageContext.request.contextPath}/order/returnOrder?oid=${o.order.id}">申请退货</a>
                                    </div>
                                    <%-- 									<button class="btn btn-info btn-sm ask2delivery" link="admin_order_delivery?id=${o.id}">催卖家发货</button> --%>

                                </c:if>

                                <c:if test="${o.order.status=='WAIT_COMMENT' }">
                                    <a href="${pageContext.request.contextPath}/order/comment?oid=${o.order.id}">
                                        <button class="orderListItemReview">评价</button>
                                    </a>
                                    <div class="cancleWaitPayOrder">
                                        <a href="${pageContext.request.contextPath}/order/returnOrder?oid=${o.order.id}">申请退货</a>
                                    </div>
                                </c:if>

                                <c:if test="${o.order.status=='COMPLETED'}">
                                    <span>已完成</span>
                                    <div class="cancleWaitPayOrder">
                                        <a href="${pageContext.request.contextPath}/order/returnOrder?oid=${o.order.id}">申请退货</a>
                                    </div>
                                </c:if>

                                <c:if test="${o.order.status=='CANCEL'}">
                                    <span>已取消</span>
                                </c:if>

                            </td>
                        <%--</c:if>--%>
                    </tr>
                <%--</c:forEach>--%>

            </table>
        </c:forEach>

    </div>
    <%--显示kkpager--%>
    <div id="kkpager"></div>

</div>