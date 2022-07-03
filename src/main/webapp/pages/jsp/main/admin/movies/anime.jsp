<%--
  Created by IntelliJ IDEA.
  User: ashurmatovlochinbek
  Date: 30/06/22
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Anime table page</title>
</head>
<body>

<section class="navbar-wrapper">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-5 py-2">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Anime Hub</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav navbar-nav-scroll w-50 justify-content-evenly">
                    <li class="nav-item">
<%--                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/pages/jsp/main/admin/admin_page.jsp">Home</a>--%>
                        <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                            <input type="hidden" name="command" value="home_all_anime"/>
                            <input type="submit" value="Home"/>
                        </form>
                    </li>
                    <li class="nav-item">
<%--                        <a class="nav-link" href="../users/users.html">Users</a>--%>
                        <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                            <input type="hidden" name="command" value="admin_users">
                            <input type="submit" value="Users">
                        </form>
                    </li>
                    <li class="nav-item">
<%--                        <a class="nav-link" href="#">Movies Control</a>--%>
                        <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                            <input type="hidden" name="command" value="admin_all_anime">
                            <input type="submit" value="Movies">
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
<%--                <a class="login text-decoration-underline text-light p-2 me-1" href="../../login/login.html">Login</a>or--%>
<%--                <a class="sign-up text-decoration-none bg-warning p-2 rounded text-secondary ms-1" href="../../registration/registration.html">Sign Up</a>--%>
<%--                <a class="logout text-decoration-underline text-light p-2 ms-1" href="">Logout</a>--%>
                <form action="${pageContext.request.contextPath}/controller.do" class="logout text-decoration-underline text-light p-2 ms-1">
                    <input type="hidden" name="command" value="logout">
                    <input type="submit" value="logout">
                </form>
            </div>
        </div>
    </nav>
</section>

<section class="main-wrapper py-4">

    <div class="container mb-3">
        <div class="row justify-content-between">
            <div class="col-6 d-flex align-items-center">
                <input type="text" class="form-control" id="inputSearch" placeholder="Search">
                <button class="btn btn-primary ms-2 d-flex align-items-center">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <span class="ms-2">Search</span>
                </button>
            </div>
            <div class="col-3">
                <form action="${pageContext.request.contextPath}/pages/jsp/main/admin/movies/add_anime/add.jsp">
                    <button type="submit" class="btn btn-success ms-2 d-flex align-items-center float-end">
                        <i class="fa-solid fa-plus"></i>
                        <span class="ms-2">Add Movie</span>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div class="container">
        <table class="table table-bordered border-primary table-hover">
            <thead class="bg-primary text-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">Name</th>
                <th scope="col">Country</th>
                <th scope="col">Created Year</th>
                <th scope="col">Genre</th>
                <th scope="col">Age Limit</th>
                <th scope="col">Description</th>
                <th scope="col" class="table-actions">Actions</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="anime_list" scope="request" type="java.util.List"/>
            <c:forEach var="tempAnime" items="${anime_list}">
                <tr>
                    <td>${tempAnime.id}</td>
                    <td>${tempAnime.name}</td>
                    <td>${tempAnime.country}</td>
                    <td>${tempAnime.createdYear}</td>
                    <td>${tempAnime.genre}</td>
                    <td>${tempAnime.ageLimit}</td>
                    <td>${tempAnime.description}</td>
                    <td class="table-actions">
                        <div>
                            <form action="${pageContext.request.contextPath}/controller.do">
                                <input type="hidden" name="command" value="find_anime_to_update"/>
                                <button type="submit" value="${tempAnime.id}" name="id" class="btn btn-outline-success">
                                    <i class="fa-regular fa-pen-to-square"></i>
                                    Edit
                                </button>
                            </form>

                            <form action="${pageContext.request.contextPath}/controller.do">
                                <input type="hidden" name="command" value="anime_delete"/>
                                <button type="submit" value="${tempAnime.id}" name="id" class="btn btn-outline-danger">
                                    <i class="fa-regular fa-trash-can"></i>
                                    Delete
                                </button>
                            </form>
                        </div>

                    </td>
                </tr>
            </c:forEach>
<%--            <tr>--%>
<%--                <td>1</td>--%>
<%--                <td>Боруто. Наруто: Следующее поколение</td>--%>
<%--                <td>Japan</td>--%>
<%--                <td>2017</td>--%>
<%--                <td>Adventure, fantasy</td>--%>
<%--                <td>18</td>--%>
<%--                <td>--%>
<%--                    Встречайте новое поколение ниндзя! История Наруто подошла к концу,--%>
<%--                    он исполнил свою мечту и стал Хокаге. Теперь в центре событий его сын по имени Боруто,--%>
<%--                    который избрал для себя совсем иной путь. Однако для начала ему следует завести друзей и--%>
<%--                    таки выпуститься из Академии, чтобы стать настоящим ниндзя. Новая история – наследие «Наруто»!--%>
<%--                </td>--%>
<%--                <td class="table-actions">--%>
<%--                    <button class="btn btn-outline-success">--%>
<%--                        <i class="fa-regular fa-pen-to-square"></i>--%>
<%--                        Edit--%>
<%--                    </button>--%>
<%--                    <button class="btn btn-outline-danger">--%>
<%--                        <i class="fa-regular fa-trash-can"></i>--%>
<%--                        Delete--%>
<%--                    </button>--%>
<%--                </td>--%>
<%--            </tr>--%>
            </tbody>
            <!-- <tfoot>

            </tfoot> -->
        </table>
    </div>
</section>

<section class="footer-wrapper bg-dark p-3">
    <footer class="d-flex justify-content-center text-light">
        Made by <a class="text-warning ms-1 text-decoration-none" href="https://github.com/Falcon005" target="_blank">Ashurmatov Lochinbek</a>
    </footer>
</section>

</body>
</html>
