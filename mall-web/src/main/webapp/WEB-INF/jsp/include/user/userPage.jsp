<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/15
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/userInfo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore//nav.css">


<div class="container-fluid">
    <%--导航栏--%>
<%--    <ul class="sidenav col-md-2">
        <h2>
            个人中心
        </h2>
        <li><a href="${pageContext.request.contextPath}/user/detail" target="iframe_a">查看个人资料</a></li>
        <li><a href="${pageContext.request.contextPath}/user/modify" target="iframe_a">修改个人资料</a></li>
        <li><a href="${pageContext.request.contextPath}/user/address" target="iframe_a">收货地址管理</a></li>
        <li><a href="${pageContext.request.contextPath}/user/evaluation" target="iframe_a">评价管理</a></li>
        <li><a href="${pageContext.request.contextPath}/cart/list">我的购物车</a></li>
        <li><a href="${pageContext.request.contextPath}/order/list">我的订单</a></li>
    </ul>--%>
        <div class="col-md-2">
        <section class="demo">
            <dl class="list nigiri">
                <dt>个人中心</dt>
                <dd><a href="${pageContext.request.contextPath}/user/detail" target="iframe_a">查看个人资料</a></dd>
                <dd><a href="${pageContext.request.contextPath}/user/modify" target="iframe_a">修改个人资料</a></dd>
                <dd><a href="${pageContext.request.contextPath}/user/address" target="iframe_a">收货地址管理</a></dd>
                <dd><a href="${pageContext.request.contextPath}/user/evaluation" target="iframe_a">评价管理</a></dd>
                <dd><a href="${pageContext.request.contextPath}/cart/list">我的购物车</a></dd>
                <dd><a href="${pageContext.request.contextPath}/order/list">我的订单</a></dd>

            </dl>
        </section>
        </div>
    <%--内容--%>
    <iframe class="content col-md-10" src="${pageContext.request.contextPath}/user/detail"
            name="iframe_a">
    </iframe>
</div>

<script src="${pageContext.request.contextPath}/static/js/makisu.min.js"></script>
<script>

    // The `enabled` flag will be `false` if CSS 3D isn't available

    if ( $.fn.makisu.enabled ) {

        var $sashimi = $( '.sashimi' );
        var $nigiri = $( '.nigiri' );
        var $maki = $( '.maki' );

        // Create Makisus

        $nigiri.makisu({
            selector: 'dd',
            overlap: 0.85,
            speed: 1.7
        });

        $maki.makisu({
            selector: 'dd',
            overlap: 0.6,
            speed: 0.85
        });

        $sashimi.makisu({
            selector: 'dd',
            overlap: 0.2,
            speed: 0.5
        });

        // Open all

        $( '.list' ).makisu( 'open' );

        // Toggle on click

        $( '.toggle' ).on( 'click', function() {
            $( '.list' ).makisu( 'toggle' );
        });

        // Disable all links

        $( '.demo a' ).click( function( event ) {
            event.preventDefault();
        });

    } else {

        $( '.warning' ).show();
    }

</script>





