<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<script>
    function append() {
        var t = $('#category_list');
        var node = t.tree('getSelected');
        t.tree('append', {
            parent: (node ? node.target : null),
            data: [{
                id: -1,
                text: '新的商品分类',
                parentId: node ? node.id : null
            }]
        });
    }

    function edit() {
        var t = $('#category_list');
        var node = t.tree('getSelected');
        t.tree('beginEdit', node.target);
    }
</script>
<div id="category_tool">
    <a href="javascript:void(0)" class="icon-add" onclick="append()"></a>
    <a href="javascript:void(0)" class="icon-edit" onclick="edit()" style="margin-right: 10px"></a>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
    <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
</div>
<div class="easyui-layout" style="width: 100%;height: 100%;">
    <div data-options="region:'west',split:true" style="width:250px;height: 100%;">
        <div class="easyui-panel" title="商品分类" data-options="tools:'#category_tool'" style="width:250px;" border="false">
            <ul id="category_list" class="easyui-tree" data-options="{
                 url:'${pageContext.request.contextPath}/category/list',
                 method:'get',
                 onContextMenu: function(e,node){
                    e.preventDefault();
                    $(this).tree('select',node.target);
                    $('#mm').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                 },
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
                },
                onDblClick: function(node){
                    $(this).tree('beginEdit',node.target);
                },
                onAfterEdit: function(node){
                    if(node.id && node.id>0){
                        $.ajax({
                            url:'${pageContext.request.contextPath}/category/update',
                            method:'post',
                            data:{
                                id:node.id,
                                name:node.text,
                                parentId: node.parentId
                            },
                            success:function(category){
                                node.text = category.name
                            }
                        })
                        return
                    }
                    $.ajax({
                        url:'${pageContext.request.contextPath}/category/add',
                        method:'post',
                        data:{
                            name:node.text,
                            parentId: node.parentId
                        },
                        success:function(category){
                            node.id = category.id
                            node.text = category.name
                            node.parentId = category.parentId
                        }
                    })
                }
            }">
            </ul>
        </div>
    </div>
    <div data-options="region:'center'">
        <div id="product_tt" class="easyui-tabs" style="width:100%;height: auto;">
        </div>
    </div>
</div>
<%--<table class="easyui-datagrid" id="dg" style="width:auto;height:auto;border:1px solid #ccc;" data-options="singleSelect:true,collapsible:true,url:'${pageContext.request.contextPath}/productList',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'id'">商品ID</th>
        <th data-options="field:'name'">商品名</th>
        <th data-options="field:'price',align:'right'">商品价格</th>
        <th data-options="field:'discount',align:'right'">优惠价</th>
        <th data-options="field:'status'">商品状态</th>
        <th data-options="field:'categoryId'">商品分类ID</th>
        <th data-options="field:'rootCategoryId'">商品父分类ID</th>
        <th data-options="field:'storeNum'">商品库存总量</th>
        <th data-options="field:'saleNum'">商品销售总量</th>
        <th data-options="field:'imageUrls'">商品图片</th>
        <th data-options="field:'params'">商品参数</th>
        <th data-options="field:'descriptionImageUrls'">商品描述图片</th>
        <th data-options="field:'commentNum'">总评论数</th>
    </tr>
    </thead>
</table>--%>
</body>
</html>
