<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/10
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>轮播图管理</title>
</head>
<body>
<%--轮播图信息列表--%>
<table id = "slideshow_grid" class="easyui-datagrid" title="详细信息列表"
       data-options="singleSelect:false,collapsible:true,toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:80">ID</th>
        <th data-options="field:'title',width:200">标题</th>
        <th data-options="field:'imageUrl',width:180,align:'right'">图片地址</th>
        <th data-options="field:'jumpUrl',width:180,align:'right'">跳转地址</th>
    </tr>
    </thead>
</table>
<script>
    /**
     * 加载数据网格
     */
    function loadslideshowGrid() {
        $.ajax({
            url:'${pageContext.request.contextPath}/slideshow/list.json',
            method:'get',
            success:function (data) {
                var arr = []
                for(var i=0;i<data.length;i++){
                    arr.push({
                        id:data[i].id,
                        title:data[i].title,
                        imageUrl:data[i].imageUrl,
                        jumpUrl:data[i].jumpUrl
                    })
                }
                $('#slideshow_grid').datagrid('loadData',arr)
            }
        })
    }

    /**
     * 初始化
     */
    (function () {
        loadslideshowGrid()
    })()

//获得被选中的行id
    function getSlidesIds(){
        var sels = $("#slideshow_grid").datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].id);
        }
        return ids;
    }

    /**
     * 工具栏
     * @type {[null,null]}
     */
    var toolbar = [{
        text:'添加',
        iconCls: 'icon-add',
        handler:function () {
            $('#slideshow_add').window('open')
        }
    },{
        text:'删除',
        iconCls: 'icon-remove',
        handler:function () {
            var ids = getSlidesIds();
            if(ids.length == 0){
                $.messager.alert('提示','请选择至少一行！');
                return
            }
            $.messager.confirm('确认','确定删除ID为'+ids+'的轮播图信息吗？',function (r) {
                if(r){
                    $.ajax({
                        url:'${pageContext.request.contextPath}/slideshow/deleteslideshow.json',
                        method:'post',
                        data:{
                            'ids':ids
                        },
                        success:function (data) {
//                            console.log(data)
                            if(data.result==='success'){
                                $.messager.alert('提示','删除成功!',undefined,function () {
                                    var sels = $("#slideshow_grid").datagrid("getSelections");
                                    for (var index in sels){
                                        var row_index = $("#slideshow_grid").datagrid("getRowIndex",sels[index])
                                        $("#slideshow_grid").datagrid('deleteRow',row_index);
                                    }
                                })
                            }
                        }
                    })
                }
            })
        }
    }]

    /**
     * 提交信息
     */
    function submitForm(){
        if(!$('#slideshow_add_form').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        var title = $('#slideshow_title').textbox('getText')
        var imageUrl = $('#slideshow_image_url').textbox('getText')
        var jumpUrl = $('#slideshow_jump_url').textbox('getText')

        $.ajax({
            url:'${pageContext.request.contextPath}/slideshow/addslideshow.json',
            method: 'post',
            data:{
                title:title,
                imageUrl:imageUrl,
                jumpUrl:jumpUrl
            },
            success:function (data) {
                console.log(data)
                loadslideshowGrid()
                if(data.title == title){
                    $.messager.alert('提示','提交成功!',undefined,function() {
                        $('#slideshow_add_form').form('clear');
                        $('#slideshow_add').window('close');
                    })
                }
            }
        })
    }

    /**
     * 清空编辑信息
     */
    function clearForm(){
        $('#slideshow_add_form').form('clear');
    }
</script>
<%--弹出窗口：添加轮播图编辑窗口--%>
<div id="slideshow_add" class="easyui-window" title="编辑添加信息" style="width:100%;max-width:400px;padding:30px 60px;"
     data-options="closed:true">
    <form id="slideshow_add_form" method="post">
        <div style="margin-bottom:20px">
            <input id="slideshow_title" class="easyui-textbox" name="title" style="width:100%" data-options="label:'标题:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input id="slideshow_image_url" class="easyui-textbox" name="imageUrl" style="width:100%" data-options="label:'图片地址:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input id="slideshow_jump_url" class="easyui-textbox" name="jumpUrl" style="width:100%" data-options="label:'URL:',required:true">
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
    </div>
</div>
</body>
</html>
