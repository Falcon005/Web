<%--
  Created by IntelliJ IDEA.
  User: ashurmatovlochinbek
  Date: 13/06/22
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="uz" scope="session"/>
<fmt:setBundle basename="prop.language"/>
<html>
<head>
    <title>Main</title>
</head>
<body>

<h1><fmt:message key="label.hello"/> ${requestScope.get("user")}</h1>
<h2><fmt:message key="label.welcome"/></h2>
</body>
</html>
