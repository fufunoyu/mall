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
           iconCls="icon-save">
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
    <!-- 分页栏 -->
    <div id="dom_var_pagination" class="easyui-pagination">
    </div>
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

    //获得被选中的订单号
    function getSelectionsIds(){
        var sels = $("#sendGoods_grid").datagrid("getSelections");
        var identifiers = [];
        for(var i in sels){
            identifiers.push(sels[i].identifier);
        }
        return identifiers;
    }
    /**
     * 工具栏
     * @type {[null,null]}
     */
    var toolbar = [{
        text:'发货',
        iconCls: 'icon-remove',
        handler:function () {
            var identifiers = getSelectionsIds();
            if(identifiers.length == 0){
                $.messager.alert('提示','请选择至少一行！');
                return
            }
            $.messager.confirm('确认','确定发货的订单号为'+identifiers+'吗？',function (r) {
                if(r){
                    $.ajax({
                        url:'${pageContext.request.contextPath}/order/delete.json',
                        method:'post',
                        data:{
                            'identifiers':identifiers
                        },
                        success:function (data) {
//                            console.log(data)
                            if(data.result==='success'){
                                $.messager.alert('提示','发货成功!',undefined,function () {
                                    var sels = $("#sendGoods_grid").datagrid("getSelections");
                                    for (var index in sels){
                                        var row_index = $("#sendGoods_grid").datagrid("getRowIndex",sels[index])
                                        $("#sendGoods_grid").datagrid('deleteRow',row_index);
                                    }
                                })
                            }
                        }
                    })
                }
            })
        }
    }]
    <%--分页功能实现--%>
    $('#dom_var_pagination').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            $(this).pagination('loading');
            // alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
            $.ajax({
                url: '${pageContext.request.contextPath}/order/send=' + "&page=" + pageNumber + "&size=" + pageSize,
                method: 'get',
                success: function (data) {
                    $('#sendGoods_grid').datagrid('loadData', data)
                    changePage(pageNumber, data.count)
                }
            })
            $(this).pagination('loaded');
        }
    });
    /**
     * 分页设置每页显示条数和当前目录及其子目录共有多少个订单
     * */
    function changePage(page, total) {
        $('#dom_var_pagination').pagination({
            total: total,
            pageNumber: page
        });
    }
</script>
</body>
</html>
