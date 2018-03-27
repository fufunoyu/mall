<%--
  Created by IntelliJ IDEA.
  User: 胖嘟嘟糟蹋圆滚滚
  Date: 2018/3/16
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        div{
            width:100%;
        }
    </style>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/material/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery-easyui-texteditor/texteditor.css">

    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery-easyui-texteditor/jquery.texteditor.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<h2>富文本</h2>
<form id="form" method="post" target="_blank">
    <script type="text/plain" id="myEditor" name="myEditor"></script>
</form>
<script type="text/javascript">
    var editor_a = UE.getEditor('myEditor');
</script>
</body>
</html>
