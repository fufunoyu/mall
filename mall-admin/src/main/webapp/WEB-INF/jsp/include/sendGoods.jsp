<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2018/3/20
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>待发货</title>
</head>
<body>
<table id = "sendGoods_grid" class="easyui-datagrid" title="发货处理"
       data-options="singleSelect:false,collapsible:true,toolbar:toolbar"
       iconCls="icon-save"
       rownumbers="true" pagination="true">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'identifier'">订单号</th>
        <th data-options="field:'createAt'">下单时间</th>
        <th data-options="field:'name'">商品名称</th>
        <th data-options="field:'nickname'">用户</th>
        <th data-options="field:'telephone'">用户联系方式</th>
        <th data-options="field:'deliveryName'">收货人</th>
        <th data-options="field:'deliveryAddress'">收货地址</th>
        <th data-options="field:'postalCode'">邮编</th>
        <th data-options="field:'phone'">收货人联系方式</th>
    </tr>
    </thead>
</table>
<script>
    /**
     * 加载数据网格
     */
    function loadslideshowGrid() {
        $.ajax({
            url:'${pageContext.request.contextPath}/order/send',
            method:'get',
            success:function (data) {
                console.log(data)
                var arr = []
                for(var i=0;i<data.length;i++){
                    arr.push({
                        identifier:data[i].order.identifier,
                        createAt:data[i].order.createAt,
                        name:data[i].product.name,
                        nickname:data[i].user.nickname,
                        telephone:data[i].user.telephone,
                        deliveryName:data[i].address.deliveryName,
                        deliveryAddress:data[i].address.deliveryAddress+data[i].address.detailAddress,
                        postalCode:data[i].address.postalCode,
                        phone:data[i].address.phone,
                    })
                }
                $('#sendGoods_grid').datagrid('loadData',arr)
            }
        })
    }

    /**
     * 初始化
     */
    (function () {
        loadslideshowGrid()
    })()
</script>
</body>
</html>
