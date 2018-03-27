<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<script>


    /**
     * 对注册页面各项操作的提示
     */
    $(function () {

        //回显错误操作提示信息
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("display", "block");
        </c:if>

        //未输入用户名时显示提示语句
        $(".registerForm").submit(function () {
            if (0 == $("#name").val().length) {
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }

            //提示用户名长度不合法
            if ($("#name").val().length < 6 || $("#name").val().length > 20) {
                $("span.errorMessage").html("用户名长度在6-20位之间");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }


            //密码未输入时显示提示信息
            if (0 == $("#password").val().length) {
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }

            //提示密码长度不合法
            if ($("#password").val().length < 6 || $("#password").val().length > 20) {
                $("span.errorMessage").html("密码长度在6-20位之间");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }

            //提示输入确认密码
            if (0 == $("#rePassword").val().length) {
                $("span.errorMessage").html("请输入重复密码");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }
            //提示两次密码输入不一致
            if ($("#password").val() != $("#rePassword").val()) {
                $("span.errorMessage").html("重复密码不一致");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }

            //提示输入邮箱
            if (0 == $("#email").val().length) {
                $("span.errorMessage").html("请输入邮箱");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }

            //检测邮箱格式是否合法

            //判断邮箱格式是否正确
            var email = $("#email").val(); //获取邮箱地址
            if (!/^([a-zA-Z0-9_])+@([a-zA-Z0-9_])+((\.[a-zA-Z0-9_]{2,3}){1,2})$/.test(email)) {
                $("span.errorMessage").html("请输入正确的邮箱格式");
                $("div.registerErrorMessageDiv").css("display", "block");
                return false;
            }
            return true;
        });
    })
</script>
<div class="simpleLogo">
    <a href="${pageContext.request.contextPath}/"><img
            src="${pageContext.request.contextPath}/static/img/site/logo.jpg"></a>
</div>
<img class="register_bg" src="${pageContext.request.contextPath}/static/img/site/loginBackground.png" alt="">
<form method="post" action="${pageContext.request.contextPath}/registerSubmit" class="registerForm">
    <h2 class="register_title">用户注册</h2>
    <div class="registerErrorMessageDiv">
        <div class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
            <span class="errorMessage"></span>
        </div>
    </div>

    <div class="form-group">
        <label for="name">用户名</label>
        <input id="name" name="username" value="${registerUser.username}" placeholder="请输入用户名" class="form-control">
    </div>
    <div class="form-group">
        <label for="password">密码</label>
        <input id="password" type="password" name="password" placeholder="请输入登陆密码" class="form-control">
    </div>
    <div class="form-group">
        <label for="rePassword">确认密码</label>
        <input id="rePassword" type="password" name="rePassword" placeholder="请再次输入你的密码" class="form-control">
    </div>
    <div class="form-group">
        <label for="email">邮箱</label>
        <input id="email" name="email" value="${registerUser.email}" placeholder="请输入你的邮箱" class="form-control">
    </div>
    <button type="submit" style="width: 100%" class="btn btn-primary">提交</button>
</form>