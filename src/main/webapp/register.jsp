<%--
  Created by IntelliJ IDEA.
  User: wddv587
  Date: 2021/6/19
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register.jsp</title>
</head>
<body>
    <h1>注册界面</h1>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        用户名：<input type="text" name="username"/><br/>
        密 码：<input type="password" name="password"><br/>
        <input type="submit" value="注册"/>
    </form>
</body>
</html>
