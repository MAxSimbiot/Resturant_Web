<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
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
                <strong>
                    <div class="btn-group" role="group" aria-label="Basic example" style="">
                        <form action="MainServlet" method="post">
                            <button type="submit" name="locale" value="us" class="btn btn-secondary"><fmt:message
                                    key="us"/></button>
                            <button type="submit" name="locale" value="ru" class="btn btn-secondary"><fmt:message
                                    key="ru"/></button>
                            <input type="hidden" name="command" value="changeLanguage"/>
                        </form>
                    </div>
                    <fmt:message key="resturant.name"/></strong>
            </a>

            <div class="btn-group" role="group" aria-label="Basic example" style="">


                <c:choose>
                    <c:when test="${logged}">
                        <form action="MainServlet" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="header.log.out"/></button>
                            <input type="hidden" name="command" value="logOut"/>
                        </form>
                        <form action="MainServlet" method="post">
                            <button type="submit" class="btn btn-secondary">${client.name}</button>
                            <input type="hidden" name="command" value="goToCabinet"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="MainServlet" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="header.account"/></button>
                            <input type="hidden" name="command" value="goToLogin"/>
                        </form>
                    </c:otherwise>
                </c:choose>

                <form action="MainServlet" method="post">
                    <button type="submit" class="btn btn-secondary"
                            style="background-color: rgb(243, 18, 18); color: rgb(255, 255, 255); line-height: 23px; text-align: left; font-weight: 700;">
                        <fmt:message key="header.cart"/> ${receipt.size}
                    </button>
                    <input type="hidden" name="command" value="showCart"/>
                </form>
            </div>

        </div>

    </div>
</header>
<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading"><fmt:message key="resturant.slogan"/></h1>
            <p class="lead text-muted"><fmt:message key="main.page.story"/></p>
            <p>
                <c:if test="${!logged}">
            <form action="MainServlet" method="post">
                <button type="submit" class="btn btn-primary my-2"><fmt:message key="main.page.register"/></button>
                <input type="hidden" name="command" value="goToLogin"/>
            </form>
            </c:if>
            </p>
        </div>
    </section>


    <div class="album py-5 bg-light">
        <div class="container">

            <form action="MainServlet" class="" method="post" style="align-content: space-between">

                <div class="d-flex flex-row justify-content-center gap-3 mb-4">
                    <div class="form-group p2">
                        <select class="form-control" name="sortPrice" onchange="this.form.submit()">
                            <option><fmt:message key="main.page.price.sort"/></option>
                            <option name="sortPrice" value="expensive"><fmt:message
                                    key="main.page.sort.expensive"/></option>
                            <option name="sortPrice" value="cheap"><fmt:message key="main.page.sort.cheap"/></option>
                        </select></div>
                    <div class="p2">
                        <button type="submit" name="sort" value="1" class="btn btn-outline-primary"><fmt:message
                                key="main.page.category.food"/></button>
                    </div>
                    <div class="p2">
                        <button type="submit" name="sort" value="4" class="btn btn-outline-primary"><fmt:message
                                key="main.page.category.meat"/></button>
                    </div>
                    <div class="p2">
                        <button type="submit" name="sort" value="3" class="btn btn-outline-primary"><fmt:message
                                key="main.page.category.soups"/></button>
                    </div>
                    <div class="p2">
                        <button type="submit" name="sort" value="2" class="btn btn-outline-primary"><fmt:message
                                key="main.page.category.drinks"/></button>
                    </div>
                    <div class="p2">
                        <button type="submit" name="sort" value="5" class="btn btn-outline-primary"><fmt:message
                                key="main.page.category.alcohol"/></button>
                    </div>
                    <div class="p2">
                        <button type="submit" name="sort" value="6" class="btn btn-outline-primary"><fmt:message
                                key="main.page.category.juice"/></button>
                    </div>
                    <div class="p2">
                        <input class="form-control" name="searchQuery" type="text"
                               placeholder="<fmt:message key="main.page.enter.product.name"/>" aria-label="Search">
                    </div>
                    <div class="p2">
                        <button class="btn btn-outline-primary" name="search" value="true" type="submit"><fmt:message
                                key="main.page.search"/></button>
                    </div>
                    <input type="hidden" name="command" value="mainPage"/>
                </div>
            </form>
            <div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        --------
                        <c:if test="${added}">
                            <fmt:message key="main.page.added"/>
                        </c:if>
                        <c:if test="${!added}">
                            Error
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="jumbotron text-center">
            <c:if test="${added}">
               <h2 style="color: darkseagreen"> <fmt:message key="main.page.added"/></h2>
            </c:if>
            </div>
            <div class="row">
                <c:choose>
                    <c:when test="${(products!=null)&&(products.size()>0)}">
                        <c:forEach var="product" items="${products}">
                            <div class="col-md-4 ">
                                <div class="card mb-4 box-shadow ">
                                    <img class="card-img-top"
                                         alt="https://i.ibb.co/rvXf6p6/Overhead-question-mark-on-White-plate-with-knife-and-fork-on-wooden-table-top.jpg"
                                         style="height: 225px; width: 100%; display: block;"
                                         src="${product.image_url}">
                                    <div class="card-body">
                                                <p><Strong>${product.name}</Strong></p>
                                                <p class="text-muted">${product.description}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <strong>${product.price} <fmt:message key="currency.grn"/></strong>
                                            <div class="btn-group">
                                                <form action="MainServlet" method="post">
                                                    <button type="submit" name="productId" value="${product.id}"

                                                            class="btn btn-sm btn-outline-secondary"
                                                            >
                                                        <fmt:message key="main.page.add.to.cart"/>
                                                    </button>
                                                    <input type="hidden" data-toggle="modal" data-target=".bd-example-modal-sm">
                                                    <input type="hidden" name="command" value="addToCart"/>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="main.page.no.products"/>
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
            <a href="#"><fmt:message key="to.top"/></a>
        </p>
        <p><fmt:message key="resturant.slogan"/> </p>
        <p><fmt:message key="footer.havent.tried"/> <a href="../../"><fmt:message key="footer.bonus"/> </a> <fmt:message key="footer.subscribe"/> <a
                href="../../getting-started/"><fmt:message key="footer.channel"/> </a>.</p>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="../../js/vendor/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/holderjs@2.9.4/holder.js"></script>


</body>
</html>
