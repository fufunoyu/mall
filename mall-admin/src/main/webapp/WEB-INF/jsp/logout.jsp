<%--
  Created by IntelliJ IDEA.
  User: qf
  Date: 2018/3/6
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>退出登录</title>
</head>
<body>

    管理员：${loginuser.username}<a href="${pageContext.request.contextPath}/logout">退出</a>

</body>
</html>
