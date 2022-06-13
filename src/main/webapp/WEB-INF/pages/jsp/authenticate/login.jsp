<%--
  Created by IntelliJ IDEA.
  User: ashurmatovlochinbek
  Date: 13/06/22
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<form action="controller.do">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" name="sub" value="push"/>
    <br/>
    ${requestScope.get("loginError")}
</form>
</body>
</html>
