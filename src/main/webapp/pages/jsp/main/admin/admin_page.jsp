<%--
  Created by IntelliJ IDEA.
  User: ashurmatovlochinbek
  Date: 29/06/22
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Admin Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--    Hello ${sessionScope.get("user")}--%>
<%--    <h1>Welcome to Admin Page</h1>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js" integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <title>Admin Page</title>
</head>
<body>

<section class="navbar-wrapper">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-5 py-2">
        <div class="container-fluid">
            <a class="navbar-brand" href="">Anime Hub</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav navbar-nav-scroll w-50 justify-content-evenly">
                    <li class="nav-item">
<%--                        <a class="nav-link active" aria-current="page" href="">Home</a>--%>
                        <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                            <input type="hidden" name="command" value="home_all_anime"/>
                            <input type="submit" value="Home"/>
                        </form>
                    </li>
                    <li class="nav-item">
<%--                        <a class="nav-link" href="${pageContext.request.contextPath}/pages/jsp/main/admin/users/users.jsp">Users</a>--%>
                        <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                            <input type="hidden" name="command" value="admin_users"/>
                            <input type="submit" value="Users"/>
                        </form>
                    </li>
                    <li class="nav-item">
<%--                        <a class="nav-link" href="${pageContext.request.contextPath}/pages/jsp/main/admin/movies/anime.jsp">Movies Control</a>--%>
                        <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                            <input type="hidden" name="command" value="admin_all_anime"/>
                            <input type="submit" value="Movies"/>
                        </form>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
                            Language
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">EN</a></li>
                            <li><a class="dropdown-item" href="#">RU</a></li>
                            <li><a class="dropdown-item" href="#">UZ</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="user-actions text-light d-flex align-items-center">
                <a class="user-profile text-dark bg-light text-decoration-none p-2 rounded-circle">
                    <i class="fa-regular fa-user"></i>
                </a>
<%--                <a class="login text-decoration-underline text-light p-2 me-1" href="./login/login.html">Login</a>or--%>
<%--                <a class="sign-up text-decoration-none bg-warning p-2 rounded text-secondary ms-1" href="./registration/registration.html">Sign Up</a>--%>
                <form action="controller.do" class="logout text-decoration-underline text-light p-2 ms-1">
                    <input type="hidden" name="command" value="logout">
                    <input type="submit" value="logout">
                </form>
            </div>
        </div>
    </nav>
</section>

<center><h1 class="admin">This page is for Admin</h1></center>

</body>
</html>
