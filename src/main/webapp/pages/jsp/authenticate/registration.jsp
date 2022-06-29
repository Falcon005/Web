<%--
  Created by IntelliJ IDEA.
  User: ashurmatovlochinbek
  Date: 17/06/22
  Time: 8:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"content="width=device-width, initial-scale=.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto"/>
    <title>Registration</title>
</head>
<body>
<div class="global-container">
    <div class="card register-form">
        <div class="card-body">
            <h1 class="card-title text-center login-header">REGISTRATION</h1>
            <div class="card-text">
                <form action="${pageContext.request.contextPath}/controller.do">
                    <input type="hidden" name="command" value="register">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email</label>
                        <input type="email" name="email" class="form-control form-control-sm"
                               id="exampleInputEmail1"/>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputFirstName1">First Name</label>

                        <input type="text" name="firstname" class="form-control form-control-sm"
                               id="exampleInputFirstName1">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputLastName1">Last Name</label>

                        <input type="text" name="lastname" class="form-control form-control-sm"
                               id="exampleInputLastName1">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputUserName1">UserName or Login</label>

                        <input type="text" name="username" class="form-control form-control-sm"
                               id="exampleInputUserName1" >
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>

                        <input type="password" name="password" class="form-control form-control-sm"
                               id="exampleInputPassword1">
                    </div>

                    <button type="submit" class="btn btn-primary btn-block">Register</button>

                    <div class="login">
                        Do you have an account? <a href="${pageContext.request.contextPath}/pages/jsp/authenticate/login.jsp">Login</a>
                    </div>
                    ${requestScope.get("user")}
                    ${requestScope.get("unavailable_login")}
                    ${requestScope.get("unavailable_email_address")}
                </form>
            </div>
        </div>
    </div>
</div>
${requestScope.get("user")}
${requestScope.get("unavailable_login")}
${requestScope.get("unavailable_email_address")}
</body>
</html>
