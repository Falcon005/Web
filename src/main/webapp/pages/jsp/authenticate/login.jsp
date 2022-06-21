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
    <meta charset="UTF-8"/>
    <meta name="viewport"content="width=device-width, initial-scale=.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto"/>
</head>
<body>

<%--<form action="controller.do">--%>

<%--&lt;%&ndash;    <input type="hidden" name="command" value="login"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    Login: <input type="text" name="login" value=""/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    Password: <input type="password" name="password" value=""/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="submit" name="sub" value="push"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <br/>&ndash;%&gt;--%>

<%--    --%>

<%--    ${requestScope.get("loginError")}--%>
<%--</form>--%>

<div class="global-container">
    <div class="card login-form">
        <div class="card-body">
            <h1 class="card-title text-center login-header">LOGIN</h1>

            <div class="card-text">
                <form action="${pageContext.request.contextPath}/controller.do">
                    <input type="hidden" name="command" value="login">
                    <div class="form-group">
                        <label for="exampleInputLogin1">Login</label>
                        <input type="text" name="username" class="form-control form-control-sm"
                               id="exampleInputLogin1">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <a href="#" style="float: right; font-size: 12px;">
                            Forgot Password?
                        </a>
                        <input type="password" name="password" class="form-control form-control-sm"
                               id="exampleInputPassword1" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Sign In</button>

                    <div class="signup">
                        Don't have an account? <a href="${pageContext.request.contextPath}/pages/jsp/authenticate/registration.jsp">Create One</a>
                    </div>
                    ${requestScope.get("ErrorInLoginOrPassword")}
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
