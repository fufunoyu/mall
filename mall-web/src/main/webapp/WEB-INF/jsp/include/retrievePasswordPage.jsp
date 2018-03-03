<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script >
    /**
     * 点击验证码刷新
     */
    function refreshVerifyCode() {
        $('#verifyCode').attr('src',"/verifyCode/getCode?a="+Math.random());
    }

    $(document).ready(function(){
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css('visibility','visible')
        </c:if>
    })

</script>
<form method="post" action="/verifyMail" class="registerForm">
    <div class="registerDiv">
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>


        <table class="registerTable" align="center">
            <tr>
                <td class="registerTableLeftTD">邮箱账号</td>
                <td class="registerTableRightTD"><input id="name" name="email" value="${retrievePassword.email}"
                                                        placeholder="请输入邮箱"></td>
            </tr>

            <tr>
                <td class="registerTableLeftTD"><img id="verifyCode" src="/verifyCode/getCode" onclick="refreshVerifyCode()"></td>
                <td class="registerTableRightTD"><input id="name1" name="code"
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
