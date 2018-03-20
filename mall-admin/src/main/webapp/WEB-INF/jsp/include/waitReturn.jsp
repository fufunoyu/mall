<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>待退货订单管理</title>
</head>
<body>
<%--轮播图信息列表--%>
<table id = "waitReturn_grid" class="easyui-datagrid" title="待退货订单列表"
       data-options="singleSelect:false,collapsible:true,toolbar:toolbarWaitReturn">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'orderId',width:200">订单号</th>
        <th data-options="field:'productName',width:180,align:'right'">商品名称</th>
        <th data-options="field:'userId',width:180,align:'right'">用户ID</th>
        <th data-options="field:'userName',width:180,align:'right'">用户名</th>
        <th data-options="field:'status',width:180,align:'right'">订单状态</th>
    </tr>
    </thead>
</table>

<script>
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
            url:'${pageContext.request.contextPath}/order/waitReturnList.json',
            method:'get',
            success:function (data) {
//                console.log(data)
                var arr = []
                for(var i=0;i<data.length;i++){
                    arr.push({
                        productName:data[i].productVo.product.name,
                        userName:data[i].user.username,
                        userId:data[i].user.id,
                        orderId:data[i].order.identifier,
                        status:"待退货"
                    })
                }
//                console.log(arr)
                $('#waitReturn_grid').datagrid('loadData',arr)
            }
        })
    }

    //获得被选中的行id
    function getSelectionsIds(){
        var sels = $("#waitReturn_grid").datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].orderId);
        }
        return ids;
    }

    /**
     * 工具栏
     * @type {[null,null]}
     */
    var toolbarWaitReturn = [{
        text:'允许退货',
        iconCls: 'icon-ok',
        handler:function () {
            var ids = getSelectionsIds();
            if(ids.length == 0){
                $.messager.alert('提示','请选择至少一行！');
                return
            }
            $.messager.confirm('确认','确定允许订单号为'+ids+'的订单退货吗？',function (r) {
                if(r){
                    $.ajax({
                        url:'${pageContext.request.contextPath}/order/goToReturn.json',
                        method:'post',
                        data:{
                            'ids':ids
                        },
                        success:function (data) {
//                            console.log(data)
                            if(data.result==='success'){
                                $.messager.alert('提示','处理成功!',undefined,function () {
                                    var sels = $("#waitReturn_grid").datagrid("getSelections");
                                    for (var index in sels){
                                        var row_index = $("#waitReturn_grid").datagrid("getRowIndex",sels[index])
                                        $("#waitReturn_grid").datagrid('deleteRow',row_index);
                                    }
                                })
                            }
                        }
                    })
                }
            })
        }
    }]

</script>