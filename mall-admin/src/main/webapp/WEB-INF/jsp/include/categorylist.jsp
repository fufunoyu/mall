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
    function onCategoryClick(index, row) {
        var products = row.products
        console.log(products)

    }

    (function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/home/list',
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
                $('#category_list').datalist('loadData', arr)
            }
        })
    })()
</script>
<div class="easyui-layout" style="width: 100%;height: 100%;">
    <div data-options="region:'west',split:true" style="width:250px;height: 100%;">
        <ul id="category_list" class="easyui-datalist" title="商品分类" lines="true" data-options="{
            onClickRow:onCategoryClick
        }">
        </ul>
    </div>
    <div data-options="region:'center'">
        <%--     <div id="product_tt" class="easyui-tabs" style="width:100%;height: auto;">
             </div>--%>
            <table class="easyui-datagrid" title="商品列表" style="width:700px;height:250px"
                   data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
                <thead>
                <tr>
                    <th data-options="field:'itemid',width:80">商品ID</th>
                    <th data-options="field:'productid',width:100">Product</th>
                    <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                    <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                    <th data-options="field:'attr1',width:250">Attribute</th>
                    <th data-options="field:'status',width:60,align:'center'">Status</th>
                </tr>
                </thead>
            </table>
    </div>
</div>
</body>
</html>
