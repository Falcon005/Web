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
    ti

    <title>Login Page</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto"/>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js" integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>--%>
</head>
<body>

<%--<section class="navbar-wrapper">--%>
<%--    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-5 py-2">--%>
<%--        <div class="container-fluid">--%>
<%--            <div class="collapse navbar-collapse" id="navbarScroll">--%>
<%--                <ul class="navbar-nav navbar-nav-scroll w-50 justify-content-evenly">--%>
<%--                    <li class="nav-item dropdown">--%>
<%--                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">--%>
<%--                            Language--%>
<%--                        </a>--%>
<%--                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                            <li><a class="dropdown-item" href="#">EN</a></li>--%>
<%--                            <li><a class="dropdown-item" href="#">RU</a></li>--%>
<%--                            <li><a class="dropdown-item" href="#">UZ</a></li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </nav>--%>
<%--</section>--%>

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
                               id="exampleInputPassword1" >
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Sign In</button>
<%--                    <input type="submit" class="btn btn-primary btn-block">Sign In</input>--%>
                    <div class="signup">
                        Don't have an account? <a href="${pageContext.request.contextPath}/pages/jsp/authenticate/registration.jsp">Create One</a>
                    </div>
                    ${requestScope.get("error_message")}
                    ${requestScope.get("error_in_blocking")}
                </form>
            </div>
        </div>
    </div>
</div>

<%--<section class="footer-wrapper bg-dark p-3">--%>
<%--    <footer class="d-flex justify-content-center text-light">--%>
<%--        Made by <a class="text-warning ms-1 text-decoration-none" href="https://github.com/Falcon005" target="_blank">Ashurmatov Lochinbek</a>--%>
<%--    </footer>--%>
<%--</section>--%>
</body>
</html>
