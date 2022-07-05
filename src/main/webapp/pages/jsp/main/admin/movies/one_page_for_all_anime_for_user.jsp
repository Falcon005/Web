<%--
  Created by IntelliJ IDEA.
  User: ashurmatovlochinbek
  Date: 03/07/22
  Time: 5:21 PM
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
  <title>Id Page</title>
  <jsp:useBean id="temporary_anime" scope="request" type="by.ashurmatov.anime.entity.Anime"/>
  <jsp:useBean id="temporary_rating" scope="request" type="by.ashurmatov.anime.entity.Rating"/>
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
            <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
              <input type="hidden" name="command" value="home_all_anime_for_user"/>
              <input type="submit" value="Home"/>
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
        <form action="${pageContext.request.contextPath}/controller.do" class="logout text-decoration-underline text-light p-2 ms-1">
          <input type="hidden" name="command" value="logout">
          <input type="submit" value="logout">
        </form>
      </div>
    </div>
  </nav>
</section>

<section class="anime-wrapper py-5">
  <div class="container">

    <div class="row anime-title">
      <h4 title="<jsp:getProperty name="temporary_anime" property="name"/>"><jsp:getProperty name="temporary_anime" property="name"/></h4>
    </div>
    <div class="row anime-data">
      <div class="col-3">
        <div class="row">
          <%--                    <img src="${pageContext.request.contextPath}/img/<jsp:getProperty name="temporary_anime" property="image_path"/>" class="card-img-top rounded anime-image" alt="anime-image">--%>
          <img src="<jsp:getProperty name="temporary_anime" property="image_path"/>" class="card-img-top rounded anime-image" alt="anime-image">
          <div class="anime-rate-comment">
            <form action="${pageContext.request.contextPath}/controller.do" class="rate-wrapper">
              <input type="hidden" name="command" value="rating_for_user"/>
              <div class="rate">
                <input type="radio" id="star10" name="rate" value="10" />
                <label for="star10" title="text"></label>
                <input type="radio" id="star9" name="rate" value="9" />
                <label for="star9" title="text"></label>
                <input type="radio" id="star8" name="rate" value="8" />
                <label for="star8" title="text"></label>
                <input type="radio" id="star7" name="rate" value="7" />
                <label for="star7" title="text"></label>
                <input type="radio" id="star6" name="rate" value="6" />
                <label for="star6" title="text"></label>
                <input type="radio" id="star5" name="rate" value="5" />
                <label for="star5" title="text"></label>
                <input type="radio" id="star4" name="rate" value="4" />
                <label for="star4" title="text"></label>
                <input type="radio" id="star3" name="rate" value="3" />
                <label for="star3" title="text"></label>
                <input type="radio" id="star2" name="rate" value="2" />
                <label for="star2" title="text"></label>
                <input type="radio" id="star1" name="rate" value="1" />
                <label for="star1" title="text"></label>
              </div>
              <button type="submit" name="id" value="${temporary_anime.id}" class="btn btn-primary">Rate</button>
            </form>
          </div>
        </div>
      </div>
      <div class="col-5 anime-details">
        <div class="row flex-column">
          <div class="col d-flex">
            <div class="anime-detail-type">Country:</div>
            <div class="anime-detail-value"><jsp:getProperty name="temporary_anime" property="country"/></div>
          </div>
          <div class="col d-flex">
            <div class="anime-detail-type">Created year:</div>
            <div class="anime-detail-value"><jsp:getProperty name="temporary_anime" property="createdYear"/></div>
          </div>
          <div class="col d-flex">
            <div class="anime-detail-type">Genre:</div>
            <div class="anime-detail-value"><jsp:getProperty name="temporary_anime" property="genre"/></div>
          </div>
          <div class="col d-flex">
            <div class="anime-detail-type">Age rating:</div>
            <div class="anime-detail-value"><jsp:getProperty name="temporary_anime" property="ageLimit"/></div>
          </div>
          <div class="col d-flex">
            <div class="anime-detail-type">Rate:</div>
            <div class="anime-detail-value"><jsp:getProperty name="temporary_rating" property="value"/></div>
          </div>

        </div>
      </div>
    </div>
    <div class="row anime-description">
      <strong>Description:</strong>
      <p>
        <jsp:getProperty name="temporary_anime" property="description"/>
      </p>
    </div>
    <div class="row anime-comments"></div>
  </div>

  <form action="${pageContext.request.contextPath}/controller.do">
    <div class="col-12 mb-2 mx-4 px-5">
      <input type="hidden" name="command" value="comment_for_user"/>
      <label for="inputComment" class="form-label">Comment</label>
      <textarea rows="3" cols="10" id="inputComment" class="form-control" name="comment"></textarea>
      <button type="submit" name="id" value="${temporary_anime.id}" class="btn btn-primary mt-3">Post comment</button>
    </div>
  </form>

  <div class="comments-wrapper px-5 mx-4 mt-3">
    <jsp:useBean id="comment_list" scope="request" type="java.util.List"/>
    <c:forEach var="temp_comment" items="${comment_list}">
      <div class="comment">
        <div class="comment-avatar">
        <span class="user-profile text-dark bg-light text-decoration-none p-2 rounded-circle">
          <i class="fa-regular fa-user"></i>
        </span>
        </div>
        <div class="comment-text">
            ${temp_comment.comment_text}
        </div>
      </div>
    </c:forEach>
  </div>

  <%--<div class="container">
    <table class="table table-bordered border-primary table-hover">
      <thead class="bg-primary text-light">
      <tr>
        <th scope="col">Comment</th>
      </tr>
      </thead>
      <tbody>
      <jsp:useBean id="comment_list" scope="request" type="java.util.List"/>
      <c:forEach var="temp_comment" items="${comment_list}">
        <tr>
          <td>${temp_comment.comment_text}</td>
        </tr>
      </c:forEach>
      </tbody>
      <!-- <tfoot>

      </tfoot> -->
    </table>
  </div>--%>
</section>



<section class="footer-wrapper bg-dark p-3">
  <footer class="d-flex justify-content-center text-light">
    Made by <a class="text-warning ms-1 text-decoration-none" href="https://github.com/Falcon005" target="_blank">Ashurmatov Lochinbek</a>
  </footer>
</section>

</body>
</html>
