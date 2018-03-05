<%--
  Created by IntelliJ IDEA.
  User: 314-d04
  Date: 2018/03/05
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/default/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/icon.css">
    <style>
        .button {
            text-decoration: none;
            color: inherit;
        }
    </style>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script type="application/javascript">
        function addPanel(name, title) {
            if ($('#tt').tabs('exists', title)) {
                $('#tt').tabs('select', title);
                return;
            }
            $('#tt').tabs('add', {
                title: title,
                content: '<iframe src="${pageContext.request.contextPath}/frame?frameName=' + name + '" frameborder="0" border="0" style="width: 100%;height: 100%;"></iframe>',
                closable: true
            });
        }

        function removePanel() {
            var tab = $('#tt').tabs('getSelected');
            if (tab) {
                var index = $('#tt').tabs('getTabIndex', tab);
                $('#tt').tabs('close', index);
            }
        }

    </script>
</head>
<body>
<div class="easyui-layout" style="width: 100%;height: 100%;">
    <div data-options="region:'west',split:true" title="菜单栏" style="width:200px;">
        <ul class="easyui-tree">
            <li>
                <span>商品管理</span>
                <ul>
                    <li>
                        <span>
                            <a class="button" href="javascript:addPanel('product','商品列表')">商品列表</a>
                        </span>
                    </li>
                    <li>
                        <span>Program Files</span>
                    </li>
                    <li>index.html</li>
                    <li>about.html</li>
                    <li>welcome.html</li>
                </ul>
            </li>
            <li>
                <span>订单管理</span>
                <ul>
                    <li>
                        <span>Photos</span>
                    </li>
                    <li>
                        <span>Program Files</span>
                    </li>
                    <li>index.html</li>
                    <li>about.html</li>
                    <li>welcome.html</li>
                </ul>
            </li>
            <li>
                <span>用户管理</span>
                <ul>
                    <li>
                        <span>Photos</span>
                    </li>
                    <li>
                        <span>Program Files</span>
                    </li>
                    <li>index.html</li>
                    <li>about.html</li>
                    <li>welcome.html</li>
                </ul>
            </li>
            <li>
                <span>首页管理</span>
                <ul>
                    <li>
                        <span>Photos</span>
                    </li>
                    <li>
                        <span>Program Files</span>
                    </li>
                    <li>index.html</li>
                    <li>about.html</li>
                    <li>welcome.html</li>
                </ul>
            </li>
        </ul>
    </div>
    <div data-options="region:'center'">
        <div id="tt" class="easyui-tabs" style="width:100%;height: auto;">
        </div>
    </div>
</div>
</body>
</html>
