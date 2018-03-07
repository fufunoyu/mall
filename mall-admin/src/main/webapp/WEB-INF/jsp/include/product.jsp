<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<%--图片大小--%>
<style>
    .photo{
        width: 70px;
        height: 70px;
        margin: 10px;
    }
</style>
<script>
    /**
     * 新增分类
     */
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

    /**
     * 删除分类
     */
    function removeit() {
        var t = $('#category_list');
        var node = t.tree('getSelected');
        t.tree('removeit', {
            parent: null,
            data: [{
                id: -1,
                parentId: null
            }]
        });
    }

    /**
     * 修改分类
     */
    function edit() {
        var t = $('#category_list');
        var node = t.tree('getSelected');
        t.tree('beginEdit', node.target);
    }

    /**
     * 点击跳转至图片详情
     * @param val
     * @param row
     * @returns {string}
     */
    function formatImage(val, row) {
        return '<a href=' + val + ' target="_blank">点击查看图片</a>'
    }

    /**
     * 点击跳转至图片描述详情
     * @param val
     * @param row
     * @returns {string}
     */
    function formatDescriptionImage(val, row) {
        return '<a href=' + val + ' target="_blank">点击查看图片描述</a>'
    }

    /**
     * 点击跳转至商品描述窗口
     * @param val
     * @param row
     * @returns {string}
     */
    function formatParams(val, row) {
        return '<a href="javascript:void(0)" onclick="paramsClick(this)" data-params=\'' + val + '\'>点击查看商品详细参数</a>'
    }

    /**
     * 双击进入商品详情修改界面
     * @param index
     * @param row
     */
    function onTableClickRow(index, row) {
        $("#productName").textbox('setText', row.name)
        $("#productPrice").textbox('setText', row.price)
        $("#productDiscount").textbox('setText', row.discount)
        $("#productStatus").textbox('setText', row.status)
        $("#productCategory").textbox('setText', row.category)
        var images = row.imageUrls.split(";")
        $("#image").empty()
        for(var i=0;i<images.length;i++){
            var url = images[i]
            var photo = $('<img class="photo" src="'+url+'"/>')
            photo.bind('contextmenu',function(e){
                e.preventDefault();
                $('#product_image_menu').menu('show',{
                    left: e.pageX,
                    top: e.pageY
                });
            })
            $("#image").append(photo)
        }
        // var upload = $('<input class="easyui-filebox" label="File1:" labelPosition="top" data-options="prompt:\'Choose a file...\'" style="width:100%">')
        // $("#image").append(upload)
        $("#product_win").window("open")
    }

</script>
<%--参数窗口--%>
<div id="product_win" class="easyui-window" title="详细参数" data-options="iconCls:'icon-save',closed:true,modal:true"
     style="padding:10px;width: 500px;">
    <form id="ff" action="product.jsp" method="post">
        <div style="margin-bottom:20px">
            <input id="productName" class="easyui-textbox" name="name" style="width:100%"
                   data-options="label:'商品名称:',required:true">
        </div>
        <div id="image" style="margin-bottom:20px">

        </div>
        <div style="margin-bottom:20px">
            <input id="productPrice" class="easyui-textbox" name="price" style="width:100%"
                   data-options="label:'商品价格:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input id="productDiscount" class="easyui-textbox" name="discount" style="width:100%"
                   data-options="label:'优惠价:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input id="productStatus" class="easyui-textbox" name="status" style="width:100%"
                   data-options="label:'商品状态:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input id="productCategory" class="easyui-textbox" name="category" style="width:100%"
                   data-options="label:'商品分类:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="message" style="width:100%;height:60px"
                   data-options="label:'Message:',multiline:true">
        </div>
    </form>
</div>
<%--用户工具栏--%>
<div id="category_tool">
    <a href="javascript:void(0)" class="icon-add" onclick="append()"></a>
    <a href="javascript:void(0)" class="icon-edit" onclick="edit()" style="margin-right: 10px"></a>
</div>

<div id="product_image_menu" class="easyui-menu" style="width:120px;">
    <div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
</div>
<%--工具栏--%>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
    <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
</div>
<%--商品分类菜单栏--%>
<div class="easyui-layout" style="width: 100%;height: 100%;">
    <div data-options="region:'west',split:true" style="width:250px;height: 100%;">
        <div class="easyui-panel" title="商品分类" data-options="tools:'#category_tool'" style="width:250px;"
             border="false">
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
                    var table = $('#product_table')
                    if(node.id>0){
                        $.ajax({
                            url:'${pageContext.request.contextPath}/product/list?categoryId='+node.id,
                            method:'get',
                            success:function(data){
                                table.datagrid('loadData',data)
                            }
                         })
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
    <%--商品表单--%>
    <div data-options="region:'center'">
        <table class="easyui-datagrid" id="product_table" style="width:100%;height:auto;border:1px solid #ccc;"
               data-options="{
                                    singleSelect:true,
                                    collapsible:true,
                                    onDblClickRow:onTableClickRow
                                }">
            <thead>
            <%--表格显示项目--%>
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
                <th data-options="field:'imageUrls',width:150,formatter:formatImage">商品图片</th>
                <th data-options="field:'params',formatter:formatParams">商品参数</th>
                <th data-options="field:'descriptionImageUrls',width:150,formatter:formatDescriptionImage">商品描述图片
                </th>
                <th data-options="field:'commentNum'">总评论数</th>
            </tr>
            </thead>
        </table>
        <div>

        </div>
    </div>
</div>


</body>
</html>
