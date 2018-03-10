<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/7
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页商品分类列表</title>
</head>
<body>
<script>
    /**
     * 点击分类栏目数据表格显示该分类下的数据
     * @param index
     * @param row
     */
    function onCategoryClick(index, row) {
        var products = row.products
        $("#home_product_list").datagrid('loadData', products)

    }

    function onCategoryDblClick(node) {
        if (node.children && node.children.length > 0) {
            return
        }
        $.ajax({
            url: node.id ? '${pageContext.request.contextPath}/category/list.json?id=' + node.id : '${pageContext.request.contextPath}/category/list.json',
            method: 'get',
            success: function (data) {
                $('#home_category_tree').tree('append', {
                    parent: node.target,
                    data: data
                });
            }
        })
    }

    /**
     * 获取已经添加到首页展示的所有商品ID
     * @returns {Array}
     */
    function getProductId() {
        var pro = []
        var data = $('#home_category_list').datalist('getRows')
        for (var i = 0; i < data.length; i++) {
            var j = 0
            for (;j < data[i].products.length;j++) {
                pro.push( data[i].products[j].id)
            }
        }
        return pro
    }

    /**
     * 加载左侧展示在首页的分类列表
     */
    function loadIndexCategoryList() {
        $.ajax({
            url: '${pageContext.request.contextPath}/home/list.json',
            method: 'get',
            success: function (data) {
                var arr = []
                for (var i = 0; i < data.length; i++) {
                    arr.push({
                        text: data[i].category.name,
                        id: data[i].category.id,
                        products: data[i].products
                    })
                }
                var row = $("#home_category_list").datalist("getSelected")
                $('#home_category_list').datalist('loadData', arr)
                if(row){
                    var rows = $("#home_category_list").datalist("getRows")
                    for(var i=0;i<rows.length;i++){
                        if(rows[i].id == row.id){
                            var index = $("#home_category_list").datalist("getRowIndex",rows[i])
                            $("#home_category_list").datalist("selectRow",index)
                            break
                        }
                    }
                }
            }
        })
    }

    /**
     * 初始化
     */
    (function () {
        loadIndexCategoryList()
    })()

    /**
     * 取出菜单栏
     * @param data
     * @returns {Array}
     */
    function loadFilter(data) {
        var arr=[]
        for(var i=0;i<data.length;i++){
            arr.push({
                id:data[i].id,
                text:data[i].name,
                parentId:data[i].parentId
            })
        }
        return arr
    }

    /**
     * 再添加商品的页面获得被选中的商品行的id
     * @returns {Array}
     */
    function getSelectionsSubIds(){
        var itemList = $("#home_choose_list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].id);
        }
        return ids;
    }

    /**
     * 点击添加调用的函数，添加商品
     */
    function addProduct() {
        var ids = getSelectionsSubIds();
        if(ids.length == 0){
            $.messager.alert('提示','未选中商品！');
            return ;
        }
        $.messager.confirm('确认','确定添加ID为'+ids+'的商品吗？',function (r) {
            if(r){
                $.ajax({
                    url:'${pageContext.request.contextPath}/home/addproduct.json',
                    method:'post',
                    data:{
                        'ids':ids
                    },
                    success:function (data) {
                        loadIndexCategoryList()
                        $("#home_category_tree").tree('getSelected').target.click()
                        if(data.length == ids.length){
                            $.messager.alert('提示','添加商品成功!',undefined,function() {
//                                            $("#home_product_list").datagrid('loadData', [])
                            })
                        }
                        var itemList = $("#home_choose_list");
                        itemList.datagrid("clearSelections");
                    }
                })
            }
        })
    }

    /**
     * 点击取消按钮调用
     */
    function cancelChoose() {
        alert("取消选项！")
        var itemList = $("#home_choose_list");
        itemList.datagrid("clearSelections");
        $("#home_add_product").window('close');
    }
</script>
<%--所有id以home开头--%>
<div class="easyui-layout" style="width: 100%;height: 100%;">
    <div data-options="region:'west',split:true" style="width:250px;height: 100%;">
       <%--左侧分类栏--%>
        <ul id="home_category_list" class="easyui-datalist" title="商品分类" lines="true" data-options="{
            onSelect:onCategoryClick,
        }">
        </ul>
    </div>
    <div data-options="region:'center'" style="height: 100%">
        <%--右侧商品数据列表--%>
        <table id="home_product_list" class="easyui-datagrid" title="商品列表" style="width:100%;height:100%"
               data-options="singleSelect:false,collapsible:true,toolbar:toolbar">
            <thead>
            <tr>
                <th data-options="field:'id',width:80">商品ID</th>
                <th data-options="field:'name',width:100">商品名</th>
                <th data-options="field:'price',width:80,align:'right'">商品价格</th>
                <th data-options="field:'discount',width:80,align:'right'">优惠价</th>
                <%--<th data-options="field:'status',width:60,align:'center'">商品状态</th>--%>
                <%--<th data-options="field:'categoryId',width:60,align:'center'">分类ID</th>--%>
                <th data-options="field:'storeNum',width:60,align:'center'">库存总量</th>
                <th data-options="field:'saleNum',width:60,align:'center'">销售总量</th>
                <th data-options="field:'commentNum',width:60,align:'center'">评论总数</th>
                <th data-options="field:'saleDate',width:60,align:'center'">上架时间</th>
            </tr>
            </thead>
        </table>
        <script>
//            在商品列表中获取被选中的商品行index
            function getSelectionsIds(){
                var itemList = $("#home_product_list");
                var sels = itemList.datagrid("getSelections");
                var ids = [];
                for(var i in sels){
                    ids.push(sels[i].id);
                }
                return ids;
            }

            var toolbar = [{
                text: '添加商品',
                iconCls: 'icon-add',
                handler:function(){
                    var node = $("#home_category_list").tree("getSelected")
                    if(node){
                        node.target.click()
                    }
                    $("#home_add_product").window('open');
                }
            },{
                text:'删除商品',
                iconCls: 'icon-remove',
                handler:function(){
                    var ids = getSelectionsIds();
                    if(ids.length == 0){
                        $.messager.alert('提示','未选中商品！');
                        return ;
                    }
                    $.messager.confirm('确认','确定删除ID为'+ids+'的商品吗？',function (r) {
                        if(r){
//                            var params = {"ids":ids};
                            $.ajax({
                                url:'${pageContext.request.contextPath}/home/deleteproduct.json',
                                method:'post',
                                data:{
                                    'ids':ids
                                },
                                success:function (data) {
                                    loadIndexCategoryList()
                                    if(data.result==='success'){
                                        $.messager.alert('提示','删除商品成功!',undefined,function() {
                                            var sels = $("#home_product_list").datagrid("getSelections");
                                            for (var index in sels){
                                                var row_index = $("#home_product_list").datagrid("getRowIndex",sels[index])
                                                $("#home_product_list").datagrid('deleteRow',row_index);
                                            }
                                        })
                                    }
                                }
                            })
                        }
                    })
                }
            }];
        </script>
    </div>
</div>
<!-- 弹出窗口：添加商品 -->
<div id="home_add_product" class="easyui-window" title="选择添加商品" data-options="iconCls:'icon-save',closed:true" style="width:1000px;height:300px;padding:10px;">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-add" onclick="addProduct()" style="width:90px">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelChoose()" style="width:90px">取消</a>
    <div class="easyui-layout" style="width: 100%;height: 100%;">
        <div data-options="region:'west',split:true" style="width:250px;height: 100%;">
            <%--添加商品窗口下的左侧分类栏--%>
            <ul id="home_category_tree" class="easyui-tree" data-options="{
                 url:'${pageContext.request.contextPath}/category/list.json',
                 method:'get',
                 onContextMenu: function(e,node){
                    e.preventDefault();
                    $(this).tree('select',node.target);
                    $('#mm').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                 },
                 loadFilter:loadFilter,
                 onDblClick:function (node) {
                    if(node.children&&node.children.length>0){
                        return
                    }
                    $.ajax({
                        url:'${pageContext.request.contextPath}/product/list?id='+node.id,
                        method:'get',
                        success:function(data){
                            $('#home_category_tree').tree('append',data)
                        }
                    })
                },
                onClick:function(node){

                    var table = $('#home_choose_list')
                    if(node.id>0){
                        $.ajax({
                            url:'${pageContext.request.contextPath}/product/list?categoryId='+node.id,
                            method:'get',
                            success:function(data){
                                var ids = getProductId()
                                var arr = []
                                for(var i=0;i<data.length;i++){
                                    var j =0;
                                    for(;j<ids.length;j++){
                                        if(data[i].id == ids[j]){
                                            break;
                                        }
                                    }
                                    if(j>=ids.length){
                                        arr.push(data[i])
                                    }
                                }

                                table.datagrid('loadData',arr)
                            }
                         })
                    }
                }

            }">
            </ul>
        </div>
        <div data-options="region:'center'" style="height: 100%">
            <%--添加商品窗口右侧的可选择商品列表--%>
            <table id="home_choose_list"class="easyui-datagrid" title="商品列表" style="width:960px;height:250px;padding:10px;"
                   toolbar="#toolbar" idField="id" collapsible="true"
                   rownumbers="true" fitColumns="true" singleSelect="false">
                <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <th data-options="field:'id',width:150">商品ID</th>
                    <th data-options="field:'name',width:150">商品名称</th>
                    <th data-options="field:'price',width:150,align:'right'">价格</th>
                    <th data-options="field:'discount',width:150,align:'right'">折后价</th>
                    <th data-options="field:'status',width:150,align:'center'">商品状态</th>
                    <th data-options="field:'categoryId',width:150,align:'center'">分类ID</th>
                    <th data-options="field:'saleNum',width:150,align:'center'">销售总量</th>
                    <th data-options="field:'commentNum',width:150,align:'center'">评论总数</th>
                </tr>
                </thead>
            </table>
        </div>

    </div>
</div>
</body>
</html>
