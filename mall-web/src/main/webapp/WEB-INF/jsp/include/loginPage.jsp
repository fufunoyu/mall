<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {

        <c:if test="${!empty error}">
        $("span.errorMessage").html("${error}");
        $("div.loginErrorMessageDiv").show();
        </c:if>

        $("form.loginForm").submit(function () {
            if ($("#name").val().length < 6 || $("#name").val().length > 20) {
                $("span.errorMessage").html("用户名在6-20位之间");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            if ($("#password").val().length < 6 || $("#name").val().length > 20) {
                $("span.errorMessage").html("密码在6-20位之间");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            return true;
        });

        $("form.loginForm input").keyup(function () {
            $("div.loginErrorMessageDiv").hide();
        });


        var left = window.innerWidth / 2 + 162;
        $("div.loginSmallDiv").css("left", left);
    })
</script>


<div id="loginDiv" style="position: relative">

    <div class="simpleLogo">
        <a href="/"><img src="/static/img/site/logo.jpg"></a>
    </div>


    <img id="loginBackgroundImg" class="loginBackgroundImg" src="/static/img/site/loginBackground.jpg">

    <form class="loginForm" action="/loginSubmit" method="post">
        <div id="loginSmallDiv" class="loginSmallDiv">
            <div class="loginErrorMessageDiv">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>
            </div>

            <div class="login_acount_text">账户登录</div>
            <div class="loginInput ">
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
                <input id="name" name="username" value="${loginUser.username}" placeholder="用户名" type="text">
            </div>


            <div class="loginInput ">
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
                <input id="password" name="password" type="password" value="${loginUser.password}" placeholder="密码"
                       type="text">
            </div>
            <br/>
            <br/>

            <div>
                <a href="/retrievePassword">忘记登录密码</a>
                <a href="register.jsp" class="pull-right">免费注册</a>
            </div>
            <div style="margin-top:20px">
                <button class="btn btn-block redButton" type="submit">登录</button>
            </div>
        </div>
    </form>


</div>	