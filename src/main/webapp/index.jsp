<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index.jsp</title>
</head>
<body>
    <%--受限资源--%>
    <h1>系统主页</h1>
    <shiro:principal/>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
    <ul>
        <%--shiro对jsp的支持，通过页面资源进行授权--%>
        <shiro:hasAnyRoles name="user, admin"> <%--普通用户和管理员都有权限--%>
            <li><a href="#">用户管理</a></li>
            <ul>
                <shiro:hasPermission name="user:add:*">
                    <li><a href="">添加</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li><a href="">删除</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li><a href="">修改</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li><a href="">删除</a></li>
                </shiro:hasPermission>
            </ul>
        </shiro:hasAnyRoles>
        <shiro:hasRole name="admin"> <%--需要管理员权限--%>
            <li><a href="#">商品管理</a></li>
            <ul>
                <li><a href="">添加</a></li>
                <li><a href="">删除</a></li>
                <li><a href="">修改</a></li>
                <li><a href="">删除</a></li>
            </ul>
            <li><a href="#">订单管理</a></li>
            <li><a href="#">物流管理</a></li>
        </shiro:hasRole>

    </ul>
    <a href="${pageContext.request.contextPath}/order/saveOrder">权限测试</a>
    <%--第二次提交--%>
</body>
</html>