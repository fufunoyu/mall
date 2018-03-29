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
<html style="background-color:#f4f4f4;">
<head>
    <title>收货地址管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap/3.3.6/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/myAddress.css">
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/mall/city.js"></script>

    <script>
        $(document).ready(function(){
            var pHtmlStr = '';
            for(var name in pc){
                pHtmlStr = pHtmlStr + '<option>'+name+'</option>';
            }
            $("#u_province").html(pHtmlStr);
            $("#u_province").change(function(){
                var pname = $("#u_province option:selected").text();
                var pHtmlStr = '';
                var cityList = pc[pname];
                for(var index in cityList){
                    pHtmlStr = pHtmlStr + '<option>'+cityList[index]+'</option>';
                }
                $("#u_city").html(pHtmlStr);
            });
            $("#u_province").change();

            $("#province").html(pHtmlStr);
            $("#province").change(function(){
                var pname = $("#province option:selected").text();
                var pHtmlStr = '';
                var cityList = pc[pname];
                for(var index in cityList){
                    pHtmlStr = pHtmlStr + '<option>'+cityList[index]+'</option>';
                }
                $("#city").html(pHtmlStr);
            });
            $("#province").change();
        });
    </script>
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
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>收货人</th>
                    <th>电话</th>
                    <th>邮编</th>
                    <th>收货地址</th>
                    <th>详细地址</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${addressList}" var="address" varStatus="vs">
                <tr>
                    <td>${address.deliveryName}</td>
                    <td>${address.phone}</td>
                    <td>${address.postalCode}</td>
                    <td>${address.deliveryAddress}</td>
                    <td>${address.detailAddress}</td>
                    <td>
                        <img oiid="${address.id}" class="imgBtn" src="${pageContext.request.contextPath}/static/img/personalCenter/trash.jpg"
                             id="remove" onclick="remove(this)">
                        <img nn="${address.deliveryName}" pp="${address.phone}" pc="${address.postalCode}" del="${address.deliveryAddress}" det="${address.detailAddress}" oiid="${address.id}"
                             class="imgBtn" src="${pageContext.request.contextPath}/static/img/personalCenter/pencil.jpg"
                             id="edit" onclick="edit(this)">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    <div>
        <br/>
        <br/>
        <br/>
        <button class="button0" id="add" onclick="add()">添 加</button>
    </div>
    <%--编辑地址弹窗--%>
    <div class="modal" id="updateWin" title="编辑地址">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close" onclick="updateclose()">&times;</span>
                <h3>编辑地址</h3>
            </div>
            <div class="modal-body">
                <br/>
                <br/>
                <input type="hidden" name="id" id="addressId">
                <div>
                    <label>邮编：</label>
                    <input style="width: 400px" type="text" id="u_post"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    <label>收货地址：</label>
                    <div class="ddd">
                        原地址-<input style="width: 140px" type="text" id="u_addr"><br/>
                    </div>
                    <div class="ddd">
                        请选择-<select style="width: 120px" id="u_province"></select>
                    </div>
                    <div class="ddd">
                        <select style="width: 100px" id="u_city"></select>
                    </div>
                </div>
                <br/>
                <br/>
                <div>
                    <label>详细地址：</label>
                    <input style="width: 400px" type="text" id="u_detailaddr"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    <label>收货人：</label>
                    <input style="width: 400px" type="text" id="u_name"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    <label>电话：</label>
                    <input style="width: 400px" type="text" id="u_phone"><br/>
                </div>
                <br/>
                <br/>
                <button class="button" id="updateConfirm" onclick="updateConfirm()">确 认</button>
                <button class="button" id="updateCancle" onclick="updateclose()">取 消</button>
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
                <h3>添加地址</h3>
            </div>
            <div class="modal-body">
                <br/>
                <br/>
                <div>
                    <label>邮编：</label>
                    <input style="width: 400px" type="text" id="a_post"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    <label>收货地址：</label>
                    <select style="width: 120px" id="province"></select>
                    <select style="width: 100px" id="city"></select>
                </div>
                <br/>
                <br/>
                <div>
                    <label>详细地址：</label>
                    <input style="width: 400px" type="text" id="a_detailaddr"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    <label>收货人：</label>
                    <input style="width: 400px" type="text" id="a_name"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    <label>电话：</label>
                    <input style="width: 400px" type="text" id="a_phone"><br/>
                </div>
                <br/>
                <br/>
                <button class="button" id="addConfirm" onclick="addConfirm()">确 认</button>
                <button class="button" id="addCancle" onclick="addclose()">取 消</button>
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

            function edit(e) {
                var id = $(e).attr("oiid")
                var post = $(e).attr("pc")
                var addr = $(e).attr("del")
                var deaddr = $(e).attr("det")
                var name = $(e).attr("nn")
                var phone =$(e).attr("pp")
                $('#addressId').val(id)
                $('#u_post').val(post)
                $('#u_addr').val(addr)
                $('#u_detailaddr').val(deaddr)
                $('#u_name').val(name)
                $('#u_phone').val(phone)
                $("#u_province").find("option[text='-省-']").attr("selected",true);
                $("#u_city").find("option[text='-市-']").attr("selected",true);
                console.log(document.getElementById("addressId").value)
                updateModal.style.display = "block"
            }

            function addclose() {
                console.log("添加窗口关闭")
                document.getElementById("a_post").value = ""
                $("#province").find("option[text='-省-']").attr("selected",true);
                $("#city").find("option[text='-市-']").attr("selected",true);
                document.getElementById("a_detailaddr").value = ""
                document.getElementById("a_name").value = ""
                document.getElementById("a_phone").value = ""
                addModal.style.display = "none"
            }

            function updateclose() {
                console.log("编辑窗口关闭")
                updateModal.style.display = "none"
            }

            function remove(e) {
                var id = $(e).attr("oiid")
                $('#addressId').val(id)
                /*$('#addressId').val(id)*/
                var r = confirm('确定删除该地址吗？')
                if (r) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/user/deleteAddress',
                        method: 'post',
                        data: {
                            id: id
                        },
                        success: function (data) {
                            alert("删除成功")
                            location.reload()
                        }
                    })
                }
            }

            function updateConfirm() {
                var province = $("#u_province").find("option:selected").text()
                var city = $("#u_city").find("option:selected").text()
                var post = document.getElementById("u_post").value
                var addr = province + city
                if(addr == "-省--市-"){
                    addr=document.getElementById("u_addr").value
                }
                var deaddr = document.getElementById("u_detailaddr").value
                var name = document.getElementById("u_name").value
                var phone = document.getElementById("u_phone").value
                var aid = document.getElementById("addressId").value
                console.log(aid)
                console.log(post)
                console.log(addr)
                console.log(deaddr)
                console.log(name)
                console.log(phone)
                $.ajax({
                    url: '${pageContext.request.contextPath}/user/updateAddress.json',
                    method: 'post',
                    data: {
                        id: aid,
                        postalCode: post,
                        deliveryAddress: addr,
                        detailAddress: deaddr,
                        deliveryName: name,
                        phone: phone
                    },
                    success: function () {
                        alert("修改成功")
                        updateModal.style.display = "none"
                        location.reload()
                    }
                })
            }

            function addConfirm() {
                var province = $("#province").find("option:selected").text()
                var city = $("#city").find("option:selected").text()
                var addr = province + city
                var post = document.getElementById("a_post").value
                var deaddr = document.getElementById("a_detailaddr").value
                var name = document.getElementById("a_name").value
                var phone = document.getElementById("a_phone").value
                var userId = ${user.id}
                console.log(post)
                console.log(addr)
                console.log(deaddr)
                console.log(name)
                console.log(phone)
                console.log(userId)
                if (post == "" || deaddr == "" || name == "" || phone == "") {
                    alert("请填写完整地址！")
                    return false
                }
                if(addr == "-省--市-" ){
                    alert("请选择省份和城市！")
                    return false
                }
                $.ajax({
                    url: '${pageContext.request.contextPath}/user/addAddress.json',
                    method: 'post',
                    data: {
                        postalCode: post,
                        deliveryAddress: addr,
                        detailAddress: deaddr,
                        deliveryName: name,
                        phone: phone,
                        userId: userId
                    },
                    success: function () {
                        document.getElementById("a_post").value = ""
                        $("#province").find("option[text='-省-']").attr("selected",true);
                        $("#city").find("option[text='-市-']").attr("selected",true);
                        document.getElementById("a_detailaddr").value = ""
                        document.getElementById("a_name").value = ""
                        document.getElementById("a_phone").value = ""
                        alert("添加成功")
                        addModal.style.display = "none"
                        location.reload()
                    }
                })
            }

    </script>

</div>
</body>
</html>
