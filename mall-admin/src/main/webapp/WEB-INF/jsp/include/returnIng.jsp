<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>退货中订单管理</title>
</head>
<body>
<%--轮播图信息列表--%>
<table id="returnIng_grid" class="easyui-datagrid" title="退货中订单列表"
       data-options="singleSelect:false,collapsible:true,toolbar:toolbarReturnIng">
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
<div id="returnIng_pagination" class="easyui-pagination">
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
        waitReturnGrid()
    })()

    /**
     * 加载数据网格
     */
    function waitReturnGrid() {
        $.ajax({
            url: '${pageContext.request.contextPath}/order/returnIngList.json',
            method: 'get',
            success: function (data) {
//                console.log(data)
                refreshPage(data, 1)
            }
        })
    }


    //获得被选中的行id
    function getWaitReturnIds() {
        var sels = $("#returnIng_grid").datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].orderId);
        }
        return ids;
    }

    /**
     * 工具栏
     * @type {[null,null]}
     */
    var toolbarReturnIng = [{
        text: '确认已退货',
        iconCls: 'icon-ok',
        handler: function () {
            var ids = getWaitReturnIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '请选择至少一行！');
                return
            }
            $.messager.confirm('确认', '确定订单号为' + ids + '的订单已退货吗？\n 注意：确认后将会把款项退还给用户', function (r) {
                if (r) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/order/confirmReturn.json',
                        method: 'post',
                        data: {
                            'ids': ids
                        },
                        success: function (data) {
//                            console.log(data)
                            if (data.result === 'success') {
                                $.messager.alert('提示', '确认成功!', undefined, function () {
                                    var pageSize = $('#returnIng_pagination').pagination('options').pageSize
                                    var pageNumber = $('#returnIng_pagination').pagination('options').pageNumber
                                     if ($('#returnIng_grid').datagrid('getRows').length == ids.length && pageNumber != 1) {
                                        pageNumber--
                                    }
                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/order/returnIngList.json?page=' + pageNumber + "&size=" + pageSize,
                                        method: 'get',
                                        success: function (data) {
                                            refreshPage(data, pageNumber)
                                        }
                                    })
                                    //删除处理后的行
                                    /*var sels = $("#returnIng_grid").datagrid("getSelections");
                                    for (var index in sels) {
                                        var row_index = $("#returnIng_grid").datagrid("getRowIndex", sels[index])
                                        $("#returnIng_grid").datagrid('deleteRow', row_index);
                                    }*/
                                })
                            }
                        }
                    })
                }
            })
        }
    }]


    <%--分页功能实现--%>
    $('#returnIng_pagination').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            $(this).pagination('loading');
            // alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
            $.ajax({
                url: '${pageContext.request.contextPath}/order/returnIngList.json?page=' + pageNumber + "&size=" + pageSize,
                method: 'get',
                success: function (data) {
                    refreshPage(data, pageNumber)
                }
            })
            $(this).pagination('loaded');
        }
    });

    /**
     * 刷新列表
     */
    function refreshPage(data, pageNumber) {
        var arr = []
        for (var i = 0; i < data.adminOrderVoList.length; i++) {
            arr.push({
                productId: data.adminOrderVoList[i].productVo.product.id,
                productName: data.adminOrderVoList[i].productVo.product.name,
                userName: data.adminOrderVoList[i].user.username,
                userId: data.adminOrderVoList[i].user.id,
                orderId: data.adminOrderVoList[i].order.identifier,
                productNum: data.adminOrderVoList[i].order.productNum,
                status: "退货中"
            })
        }
//                console.log(arr)
        $('#returnIng_grid').datagrid('loadData', arr)
        changePage(pageNumber, data.count)
    }

    /**
     * 分页设置每页显示条数和当前目录及其子目录共有多少件商品
     * */
    function changePage(page, total) {
        $('#returnIng_pagination').pagination({
            total: total,
            pageNumber: page
        });
    }

</script>