<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已完成订单管理</title>
</head>
<body>
<%--轮播图信息列表--%>
<table id="completed_grid" class="easyui-datagrid" title="退货中订单列表"
       data-options="singleSelect:false,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'orderId',width:200">订单号</th>
        <th data-options="field:'productName',width:250,align:'right',formatter:  productFormatter
			">商品名称</th>
        <th data-options="field:'productNum',width:100,align:'right'">商品数量</th>
        <th data-options="field:'userId',width:130,align:'right'">用户ID</th>
        <th data-options="field:'userName',width:180,align:'right'">用户名</th>
        <th data-options="field:'status',width:120,align:'right'">订单状态</th>
    </tr>
    </thead>
</table>
<!-- 分页栏 -->
<div id="completed_pagination" class="easyui-pagination">
</div>
<script>

    /**
     * 商品名链接
     */
    function productFormatter(value,row,index) {
        return "<a href='http://localhost:8080/mall/product?pid=" + row.productId + "' target='_blank'>"+value+"</a>"
    }


    /**
     * 初始化
     */
    (function () {
        completedGrid()
    })()

    /**
     * 加载数据网格
     */
    function completedGrid() {
        $.ajax({
            url: '${pageContext.request.contextPath}/order/completedList.json',
            method: 'get',
            success: function (data) {
//                console.log(data)
                completedRefreshPage(data, 1)
            }
        })
    }


    //获得被选中的行id
    function getShipIngIds() {
        var sels = $("#completed_grid").datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].orderId);
        }
        return ids;
    }



    <%--分页功能实现--%>
    $('#completed_pagination').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            $(this).pagination('loading');
            // alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
            $.ajax({
                url: '${pageContext.request.contextPath}/order/completedList.json?page=' + pageNumber + "&size=" + pageSize,
                method: 'get',
                success: function (data) {
                    completedRefreshPage(data, pageNumber)
                }
            })
            $(this).pagination('loaded');
        }
    });

    /**
     * 刷新列表
     */
    function completedRefreshPage(data, pageNumber) {
        var arr = []
        for (var i = 0; i < data.adminOrderVoList.length; i++) {
            arr.push({
                productId: data.adminOrderVoList[i].productVo.product.id,
                productName: data.adminOrderVoList[i].productVo.product.name,
                userName: data.adminOrderVoList[i].user.username,
                userId: data.adminOrderVoList[i].user.id,
                orderId: data.adminOrderVoList[i].order.identifier,
                productNum: data.adminOrderVoList[i].order.productNum,
                status: "已完成"
            })
        }
//                console.log(arr)
        $('#completed_grid').datagrid('loadData', arr)
        completedChangePage(pageNumber, data.count)
    }

    /**
     * 分页设置每页显示条数和当前目录及其子目录共有多少件商品
     * */
    function completedChangePage(page, total) {
        $('#completed_pagination').pagination({
            total: total,
            pageNumber: page
        });
    }

</script>