<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">
    <title>ResturantWeb</title>
    <!-- Bootstrap core CSS -->
    <link href="../../css/editor.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="album.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>

<body>

<header>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
                    <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path>
                    <circle cx="12" cy="13" r="4"></circle>
                </svg>
                <strong>
                    <div class="btn-group" role="group" aria-label="Basic example" style="">
                        <button type="button" class="btn btn-secondary"><fmt:message key="en"/></button>
                        <button type="button" class="btn btn-secondary"><fmt:message key="ru"/></button>
                    </div>
                    <fmt:message key="resturant.name"/></strong>
            </a>

        </div>
        <div class="btn-group" role="group" aria-label="Basic example" style="">
            <button type="button" class="btn btn-secondary"><fmt:message key="header.log.out"/></button>
            <button type="button" class="btn btn-secondary"><fmt:message key="header.account"/></button>
            <button type="button" class="btn btn-secondary"
                    style="background-color: rgb(243, 18, 18); color: rgb(255, 255, 255); line-height: 23px; text-align: left; font-weight: 700;">
                <fmt:message key="header.cart"/>
            </button>
        </div>
    </div>
</header>

<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading"><fmt:message key="resturant.slogan"/> </h1>
            <p class="lead text-muted">Как-то раз, великий OG Снуп Ди О Дабл Джи возвращался с колледжа, вместе с Др.Дре Вест-Сайд LBC
                                        У ребят прикрутили животы, а деньжат на пиццу от гренни не хватало. С того момента и открыли они, вдруг,
                                           свою харчевню на Вест-Сайд.</p>
            <p>
                <a href="#" class="btn btn-primary my-2"><fmt:message key="main.page.register"/></a>
            </p>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row">
                <c:choose>
                    <c:when test="${(products!=null)&&(products.size()>0)}">
                        <c:forEach var="product" items="${products}">
                            <div class="col-md-4">
                                <div class="card mb-4 box-shadow">
                                    <img class="card-img-top"
                                         alt="https://i.ibb.co/rvXf6p6/Overhead-question-mark-on-White-plate-with-knife-and-fork-on-wooden-table-top.jpg"
                                         style="height: 225px; width: 100%; display: block;"
                                         src="${product.image_url}">
                                    <div class="card-body">
                                        <p><Strong>${product.name_ru}</Strong></p>
                                        <p class="text-muted">${product.description_ru}</p>

                                        <div class="d-flex justify-content-between align-items-center">
                                            <strong>${product.price} grn</strong>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-sm btn-outline-secondary">BUY
                                                </button>

                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>


                    </c:when>
                    <c:otherwise>
                        Nothing
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
    <nav aria-label="Page navigation example" style="float: right;">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>
    </div>

</main>

<footer class="text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>Онлайн харчевня для мудреца!</p>
        <p>Еще не пробовали нашу еду? <a href="../../">получить бонус</a> или подписаться на канал <a href="../../getting-started/">Дневник Мудреца</a>.</p>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="../../js/vendor/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/holderjs@2.9.4/holder.js"></script>


<svg xmlns="http://www.w3.org/2000/svg" width="348" height="226" viewBox="0 0 348 226" preserveAspectRatio="none"
     style="display: none; visibility: hidden; position: absolute; top: -100%; left: -100%;">
    <defs>
        <style type="text/css"></style>
    </defs>
    <text x="0" y="17" style="font-weight:bold;font-size:17pt;font-family:Arial, Helvetica, Open Sans, sans-serif">
        Thumbnail
    </text>
</svg>
</body>
</html>
