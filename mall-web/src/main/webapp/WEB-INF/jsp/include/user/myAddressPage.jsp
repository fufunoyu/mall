<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/16
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>收货地址管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/myAddress.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
<div class="left">
    <div class="headImg">
        <img src="${user.avatar}" alt="头像">
        <div class="desc">
            <span>昵称：</span>${user.nickname}
        </div>
    </div>
</div>
<div class="right">
    <div class="info">
        收货地址
    </div>
    <ul>
        <c:forEach items="${addressList}" var="address" varStatus="vs">
            <li oiid = "${addressList[vs.index].id}" class="address">
                <label>地址 ${vs.index + 1}：</label>
                <span class="addr" oiid = "${addressList[vs.index].id}">
                    邮编 ${address.postalCode} ${address.deliveryAddress} ${address.deliveryName} ${address.phone}
                </span>
                <span>
                    <button class="addrBtn" id="edit" onclick="edit()">编 辑</button>
                    <button class="addrBtn" id="remove" onclick="remove()">删 除</button>
                </span>
            </li>
        </c:forEach>
     <%--   <li id="addr1">
            <label>地址 1：</label>
            <span class="addr1" id="address1">邮编 545400 江苏省苏州市吴中区林泉街399号东南大学三江院 张三 18234568899</span>
            <span>
                <button>编 辑</button>
                <button>删 除</button>
            </span>
        </li>--%>
    </ul>
    <div>
        <br/>
        <br/>
        <br/>
        <button class="button" id="add" onclick="add()">添 加</button>
    </div>
    <%--编辑地址弹窗--%>
    <div class="modal" id="updateWin" title="编辑地址">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close" onclick="updateclose()">&times;</span>
                <h2>编辑地址</h2>
            </div>
            <div class="modal-body">
                <br/>
                <br/>
                <div>
                    邮编：<input style="width: 400px" type="text" id="u_post"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    收货地址：<input style="width: 400px" type="text" id="u_addr"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    收货人：<input style="width: 400px" type="text" id="u_name"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    电话：<input style="width: 400px" type="text" id="u_phone"><br/>
                </div>
                <br/>
                <br/>
                <button class="button" id="updateConfirm" onclick="updateConfirm()">确 认</button>
                <button class="button" id="updateCancle" onclick="updateCancle()">取 消</button>
                <br/>
                <br/>
            </div>
        </div>
    </div>

    <%--添加地址弹窗--%>
    <div class="modal" id="addWin" title="添加地址">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close" onclick="addclose()">&times;</span>
                <h2>添加地址</h2>
            </div>
            <div class="modal-body">
                <br/>
                <br/>
                <div>
                    邮编：<input style="width: 400px" type="text" id="a_post"><br/>
                </div>
                <br/>
                <br/>
                <div>收货地址：<input style="width: 400px" type="text" id="a_addr"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    收货人：<input style="width: 400px" type="text" id="a_name"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    电话：<input style="width: 400px" type="text" id="a_phone"><br/>
                </div>
                <br/>
                <br/>
                <button class="button" id="addConfirm" onclick="addConfirm()">确 认</button>
                <button class="button" id="addCancle" onclick="addCancle()">取 消</button>
                <br/>
                <br/>
            </div>
        </div>
    </div>

    <script>
        var updateModal = document.getElementById('updateWin')
        var addModal = document.getElementById('addWin')

        function add() {
            addModal.style.display = "block"
        }

        function edit() {
            updateModal.style.display = "block"
        }
        
        function addclose() {
            document.getElementById("a_post").value=""
            document.getElementById("a_addr").value=""
            document.getElementById("a_name").value=""
            document.getElementById("a_phone").value=""
            updateModal.style.display = "none"
        }

        function updateclose() {
            document.getElementById("u_post").value=""
            document.getElementById("u_addr").value=""
            document.getElementById("u_name").value=""
            document.getElementById("u_phone").value=""
            addModal.style.display = "none"
        }
        function addCancle() {
            document.getElementById("a_post").value=""
            document.getElementById("a_addr").value=""
            document.getElementById("a_name").value=""
            document.getElementById("a_phone").value=""
            updateModal.style.display = "none"
        }

        function updateCancle() {
            document.getElementById("u_post").value=""
            document.getElementById("u_addr").value=""
            document.getElementById("u_name").value=""
            document.getElementById("u_phone").value=""
            addModal.style.display = "none"
        }

        function remove(){
            console.log('${address.id}')
            var aid = '${address.id}'
            $.ajax({
                url:'${pageContext.request.contextPath}/user/deleteAddress',
                method:'post',
                data:{
                    id:aid
                },
                success:function(data) {
                    alert("success")
                    location.reload()
                }
            })
        }

        function updateConfirm() {
            var post = document.getElementById("u_post").value
            var addr = document.getElementById("u_addr").value
            var name = document.getElementById("u_name").value
            var phone = document.getElementById("u_phone").value
            var aid = '${address.id}'
            $.ajax({
                url:'${pageContext.request.contextPath}/user/updateAddress.json',
                method:'post',
                data:{
                    id:aid,
                    postalCode:post,
                    deliveryAddress:addr,
                    deliveryName:name,
                    phone:phone
                },
                success:function () {
                    document.getElementById("u_post").value=""
                    document.getElementById("u_addr").value=""
                    document.getElementById("u_name").value=""
                    document.getElementById("u_phone").value=""
                    alert("修改成功")
                    updateModal.style.display = "none"
                }
            })
        }

        function addConfirm() {
            var post = document.getElementById("a_post").value
            var addr = document.getElementById("a_addr").value
            var name = document.getElementById("a_name").value
            var phone = document.getElementById("a_phone").value
            $.ajax({
                url:'${pageContext.request.contextPath}/user/addAddress.json',
                method:'post',
                data:{
                    postalCode:post,
                    deliveryAddress:addr,
                    deliveryName:name,
                    phone:phone
                },
                success:function () {
                    document.getElementById("a_post").value=""
                    document.getElementById("a_addr").value=""
                    document.getElementById("a_name").value=""
                    document.getElementById("a_phone").value=""
                    alert("添加成功")
                    addModal.style.display = "none"
                }
            })
        }

    </script>

</div>
</body>
</html>
