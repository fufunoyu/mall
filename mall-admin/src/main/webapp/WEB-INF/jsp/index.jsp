<%--
  Created by IntelliJ IDEA.
  User: 314-d04
  Date: 2018/03/05
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.rhinoceros.mall.core.constant.ConstantValue" %>
<html>
<head>
    <title>后台管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/material/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/icon.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery-easyui-texteditor/texteditor.css">
    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        .button {
            text-decoration: none;
            color: inherit;
        }

        .header {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
        }

        .title{
            font-size: 18px;
            font-weight: bold;
            margin: 0;
            padding: 10px;
        }

        .header a {
            text-decoration: none;
            color: lightskyblue;
        }

        .header a:hover {
            color: blue;
        }

        .header > div>* {
            margin-left: 10px;
        }
    </style>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery-easyui-texteditor/jquery.texteditor.js"></script>

    <script type="application/javascript">

    </script>
</head>
<body>
<div class="header">
    <h2 class="title">民生银行网上商城后台管理系统</h2>
    <div>
        <span>${sessionScope[ConstantValue.CURRENT_USER].username}</span>
        <a href="${pageContext.request.contextPath}/logout">退出登录</a>
    </div>
</div>
<div class="easyui-layout" style="width: 100%;height: 100%;">
    <div data-options="region:'west'" title="菜单栏" style="width:200px;height: 100%;">
        <ul id="menu" class="easyui-tree" data-options="{
                 onClick:function (node) {
                    if(node.attributes&&node.attributes.url){
                        var tabs = $('#tt')
                        if(tabs.tabs('getTab',node.text)){
                            tabs.tabs('select',node.text)
                        }else{
                            tabs.tabs('add',{
                                title:node.text,
                                href:node.attributes.url,
                                closable:true
                            })
                        }
                    }
                }
            }">
            <li data-options="attributes:{'url':'${pageContext.request.contextPath}/product'}">
                <span>商品分类管理</span>
            </li>
            <li>
                <span>订单管理</span>
                <ul>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/order/waitShip'}">
                        <span>待发货</span>
                    </li>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/order/shipIngPage'}">
                        <span>已发货</span>
                    </li>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/order/waitCommentPage'}">
                        <span>待评价</span>
                    </li>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/order/completedPage'}">
                        <span>已完成</span>
                    </li>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/order/returnIngPage'}">
                        <span>退货中</span>
                    </li>
                </ul>
            </li>
            <li>
                <span>首页管理</span>
                <ul>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/slideshow'}">
                        <span>轮播图管理</span>
                    </li>
                    <li data-options="attributes:{'url':'${pageContext.request.contextPath}/home'}">
                        <span>分类商品广告管理</span>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <div data-options="region:'center'" style="height: 100%;">
        <div id="tt" class="easyui-tabs" style="width:100%;height: 100%;">
        </div>
    </div>
</div>

</body>
</html>
