<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="com.rhinoceros.mall.core.constant.ConstantValue" %>
<script>
    /**
     * 点击验证码刷新
     */
    function refreshVerifyCode() {
        $('#verifyCode').attr('src', "/verifyCode/getCode?a=" + Math.random());
    }

    //显示错误提示信息
    $(document).ready(function () {
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css('visibility', 'visible')
        </c:if>
    })

</script>
<form method="post" action="${pageContext.request.contextPath}/resetPasswordSubmit" class="registerForm">
    <div class="registerDiv">
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>

        <input name="secret" value="${requestScope[ConstantValue.RESET_PASSWORD_PARAM]}" type="hidden">

        <table class="registerTable" align="center">
            <tr>
                <td class="registerTableLeftTD">重置密码</td>
                <td class="registerTableRightTD"><input id="password" name="password" value="${registerUser.username}"
                                                        placeholder="请输入密码"></td>
            </tr>

            <tr>
                <td class="registerTableLeftTD">确认密码</td>
                <td class="registerTableRightTD"><input id="rePassword" name="rePassword"
                                                        value="${registerUser.username}"
                                                        placeholder="请再次输入密码"></td>
            </tr>

            <tr>
                <td class="registerTableLeftTD"><img id="verifyCode" src="${pageContext.request.contextPath}/verifyCode/getCode"
                                                     onclick="refreshVerifyCode()"></td>
                <td class="registerTableRightTD"><input id="name1" name="code" value="${registerUser.username}"
                                                        placeholder="请输入验证码"></td>

            </tr>


            <tr>
                <td colspan="2" class="registerButtonTD">
                    <input type="submit" value="确定">
                </td>
            </tr>
        </table>
    </div>
</form>
