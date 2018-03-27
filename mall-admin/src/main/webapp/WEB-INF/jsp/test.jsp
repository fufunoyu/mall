
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/material/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/themes/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery-easyui-texteditor/texteditor.css">

    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/easyui-1.5.4.2/jquery-easyui-texteditor/jquery.texteditor.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.all.js"></script>


</head>
<body>
<script type="text/javascript">
    UE.getEditor('myEditor',{
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
        toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold','test']],
        //focus时自动清空初始化时的内容
        autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //默认的编辑区域高度
        initialFrameHeight:300,
        //更多其他参数，请参考ueditor.config.js中的配置项
        serverUrl: '/server/ueditor/controller.php'
    })
</script>
<select id="cc2" class="easyui-combotree" style="width:200px;"
        data-options="url:'${pageContext.request.contextPath}/category/list.json',loadFilter: categoryFilter,

        onClick:function (node) {
             alert(node.id +' '+node.parentId)
        }
        ,required:true">
</select>

</body>
</html>
