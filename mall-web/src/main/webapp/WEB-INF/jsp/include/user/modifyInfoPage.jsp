<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/16
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.rhinoceros.mall.core.enumeration.Gender" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ page import="com.rhinoceros.mall.core.constant.ConstantValue" %>

<html>
<head>
    <title>修改资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/modifyInfo.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>

    <script>
        function save() {
            var birthday = document.getElementById("dateid").value
            var nickname = document.getElementById("nicknameid").value
            var realname = document.getElementById("usernameid").value
            var email = document.getElementById("emailid").value
            var telephone = document.getElementById("telephoneid").value
            var gender = $("input[name='sex']").filter(":checked").attr("value")
            var birth = new Date(birthday)
            console.log(birth)
            console.log(realname)
            console.log(email)
            console.log(telephone)
            console.log(gender)
            if(realname == ""){
                alert("用户姓名不能为空，请填写")
                return false
            }
            if(email == ""){
                alert("邮箱不能为空，请填写")
                return false
            }
            if(telephone == ""){
                alert("电话不能为空，请填写")
                return false
            }
/*            if(new Date(birthday) == null){
                alert("请选择生日！")
            }*/
            $.ajax({
                url:'${pageContext.request.contextPath}/user/update.json',
                method:'post',
                data:{
                    id:${sessionScope[ConstantValue.CURRENT_USER].id},
                    username:realname,
                    nickname:nickname,
                    email:email,
                    telephone:telephone,
                    birthday:birth,
                    gender:gender
                },
                success:function (data) {
                    console.log(data)
                    if(data!=1){
                        alert("修改失败")
                    }
                    parent.location.href=parent.location.href
                }
            })
        }
    </script>

</head>
<body>
<div class="left">
    <div class="headImg">
        <img src="${sessionScope[ConstantValue.CURRENT_USER].avatar}" alt="头像" onclick="updateAvatar()">
        <div class="desc">
            <span>昵称：</span><input type="text" id="nicknameid" name="nickname" size="10" value=${sessionScope[ConstantValue.CURRENT_USER].nickname}>
        </div>

    </div>
</div>
<div class="right">
    <div class="info">
        修改资料
    </div>
    <form id="infoForm" method="post">
        <ul>
            <li id="l_username">
                <label>真实姓名：</label>
                <span class="username" id="username">
                <input type="text" id="usernameid" name="username" size="20" value='${sessionScope[ConstantValue.CURRENT_USER].username}'>
            </span>
            </li>
            <li id="l_gender">
                <label>性别：</label>
                <span class="gender" id="gender">
                    <input type="radio" name="sex" id="male" value="MALE" ${sessionScope[ConstantValue.CURRENT_USER].gender == Gender.MALE?'checked':''}>男
                    <input type="radio" name="sex" id="female" value="FEMALE" ${sessionScope[ConstantValue.CURRENT_USER].gender == Gender.FEMALE?'checked':''}>女
                </span>
            </li>
            <li id="l_birthday">
                <label>生日：</label>
                <span class="birthday" id="birthday">
                    <input id = "dateid" type="date" size="20" value='<fmt:formatDate value="${sessionScope[ConstantValue.CURRENT_USER].birthday}" pattern="yyyy-MM-dd"/>'>
            </span>
            </li>
            <li id="l_email">
                <label>邮箱：</label>
                <span class="email" id="email">
                    <input type="text" id="emailid" name="email" size="20" value=${sessionScope[ConstantValue.CURRENT_USER].email}>
                </span>
            </li>
            <li id="l_telephone">
                <label>电话：</label>
                <span class="telephone" id="telephone">
                    <input type="text" id="telephoneid" name="telephone" size="20" value=${sessionScope[ConstantValue.CURRENT_USER].telephone}>
                </span>
            </li>
        </ul>
    </form>
    <div>
        <br/>
        <button class="button" onclick="save()">保 存</button>
        <button class="button" id="showButton">修 改 密 码</button>
    </div>

    <div class="modal" id="updateModal" title="修改密码">
        <!-- 弹窗内容 -->
        <div class="modal-content">
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>修改密码</h2>
            </div>
            <div class="modal-body">
                <br/>
                <br/>
                <div>
                    请输入新密码：<input type="password" id="pass1"><br/>
                </div>
                <br/>
                <br/>
                <div>
                    再次输入密码：<input type="password" id="pass2"><br/>
                </div>
                <br/>
                <br/>
                <button class="button" id="confirm" onclick="confirm()">确 认</button>
                <button class="button" id="cancle" onclick="cancle()">取 消</button>
                <br/>
                <br/>
            </div>
        </div>
    </div>

    <div class="modal" id="updateAvatar" title="修改头像">
        <!-- 弹窗内容 -->
        <div class="modal-content">
            <div class="modal-header">
                <span class="close" onclick="avatar_cancle()">&times;</span>
                <h2>修改头像</h2>
            </div>
            <div class="modal-body">
                <br/>
                <br/>
                <div class="file-box">
                    <form id="avatar_form" action="${pageContext.request.contextPath}/user/uploadAvatar" enctype="multipart/form-data" method="post">
                        <input type='text' name='textfield' id='textfield' class='txt' />
                        <input type='button' class='btn' value='浏览...' />
                        <input type="file" class="file" size="28" name="file" id="fileField" onchange="document.getElementById('textfield').value = this.value">
                        <input type="submit" onclick="avatar_confirm()" class="uploadBtn uploadBtn1" value="上 传">
                    </form>
                </div>
<%--                <form id="avatar_form" action="${pageContext.request.contextPath}/user/uploadAvatar" enctype="multipart/form-data" method="post">
                    <input type='text' name='textfield' id='textfield' class='txt' />
                    <input type="file" name="file" id="fileField" onchange="document.getElementById('textfield').value = this.value">
                    <input type="submit" class="button" value="上 传">
                </form>--%>

            </div>
        </div>
    </div>



    <script>
        var modal = document.getElementById('updateModal');
        var avatarModal = document.getElementById('updateAvatar');
        // 打开弹窗的按钮对象
        var btn = document.getElementById("showButton");

        // 获取 <span> 元素，用于关闭弹窗 that closes the modal
        var span = document.getElementsByClassName("close")[0];

        btn.onclick = function () {
            modal.style.display = "block"
        }
        span.onclick = function() {
            document.getElementById("pass1").value=""
            document.getElementById("pass2").value=""
            modal.style.display = "none";
        }

        function confirm() {
            var pass1 = document.getElementById("pass1").value
            var pass2 = document.getElementById("pass2").value
            if(pass1 == ""||pass2 == ""){
                alert("密码不可为空，请输入！")
                return false
            }
            if(pass1 != pass2){
                alert("两次密码输入不一致！")
                document.getElementById("pass1").value=""
                document.getElementById("pass2").value=""
                return false
            }
/*            if(pass1 == ${sessionScope[ConstantValue.CURRENT_USER].password}){
                alert("新密码不可以和旧密码一样！")
                document.getElementById("pass1").value=""
                document.getElementById("pass2").value=""
                return false
            }*/
            $.ajax({
                url:'${pageContext.request.contextPath}/user/update.json',
                method:'post',
                data:{
                    id:${sessionScope[ConstantValue.CURRENT_USER].id},
                    password:pass1
                },
                success:function (data) {
                    document.getElementById("pass1").value=""
                    document.getElementById("pass2").value=""
                    alert("修改成功")
                    modal.style.display = "none";
                }
            })

        }

        function cancle() {
            document.getElementById("pass1").value=""
            document.getElementById("pass2").value=""
            modal.style.display = "none";
        }

        function updateAvatar() {
            avatarModal.style.display = "block"
        }

        function avatar_confirm() {
            var obj = document.getElementById("textfield").value
            console.log(obj)
            if (obj == ""){
                alert("请至少选择一个上传文件!")
            }
            else{
                alert("修改成功")
                avatarModal.style.display = "none";
                /*document.getElementById("tip").style.display = "none"*/
            }
        }

        function avatar_cancle() {
            document.getElementById("textfield").value = ""
            var obj = document.getElementById("fileField")
            obj.outerHTML=obj.outerHTML;
            avatarModal.style.display = "none";
        }

    </script>
</div>
</body>
</html>
