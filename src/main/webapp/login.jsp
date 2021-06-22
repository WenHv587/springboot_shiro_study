<%--
  Created by IntelliJ IDEA.
  User: wddv587
  Date: 2021/6/19
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
    <script type="text/javascript">
        function _change() {
            var imgEle = document.getElementById("img");
            imgEle.src = "${pageContext.request.contextPath}/user/image"
        }

    </script>
</head>
<body>
    <h1>登录界面</h1>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名：<input type="text" name="username"/><br/>
        密 码：<input type="password" name="password"><br/>
        验证码：<input type="text" name="verifyCode"/><img id="img" src="${pageContext.request.contextPath}/user/image" alt="验证码">
        <a href="">看不清，换一张</a><br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
