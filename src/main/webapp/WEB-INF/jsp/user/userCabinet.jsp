<%@ page import="entity.Product" %>
<%@ page import="entity.Receipt" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Cart</title>

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
            <a href="MainServlet?command=mainPage" class="navbar-brand d-flex align-items-center">

                <strong><fmt:message key="resturant.name"/> </strong>
            </a>

            <div class="btn-group" role="group" aria-label="Basic example" style="">
                <form action="MainServlet" method="post">
                    <form action="MainServlet" method="get">
                        <button type="submit" class="btn btn-secondary"><fmt:message key="header.log.out"/></button>
                        <input type="hidden" name="command" value="logOut"/>
                    </form>
                    <form action="MainServlet" method="get">
                        <button type="submit" class="btn btn-secondary"
                                style="background-color: rgb(243, 18, 18); color: rgb(255, 255, 255); line-height: 23px; text-align: left; font-weight: 700;">
                            <fmt:message key="header.cart"/></button>
                        <input type="hidden" name="command" value="showCart"/>
                    </form>
                </form>
            </div>
        </div>
    </div>
</header>

<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <p></p>
            <p></p>
            <p class="lead text-muted"><fmt:message key="client.page.message"/></p>
            <p></p>
        </div>
    </section>

    <div class="album py-5 bg-light">

        <div class="container mb-5">
            <div class="row">
                <div class="col-md-16">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <h4><fmt:message key="client.page.your.profile"/></h4>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <form>
                                        <div class="form-group row my-2">
                                            <label for="username" class="col-4 col-form-label"><fmt:message key="client.page.login"/></label>
                                            <div class="col-8">
                                                <input id="username" name="username" placeholder="${client.login}"
                                                       class="form-control here" required="required" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group row my-2">
                                            <label for="text" class="col-4 col-form-label"><fmt:message key="client.page.name"/></label>
                                            <div class="col-8">
                                                <input id="text" name="text" placeholder="${client.name}"
                                                       class="form-control here" required="required" type="text">
                                            </div>
                                        </div>

                                        <div class="form-group row my-2">
                                            <label for="email" class="col-4 col-form-label"><fmt:message key="client.page.password"/></label>
                                            <div class="col-8">
                                                <input id="email" name="email" placeholder="${client.password}" class="form-control here"
                                                       required="required" type="text">
                                            </div>
                                        </div>

                                        <div class="form-group row my-2">
                                            <label for="newpass" class="col-4 col-form-label"><fmt:message key="client.page.phone"/></label>
                                            <div class="col-8">
                                                <input id="newpass" name="newpass" placeholder="${client.phone}"
                                                       class="form-control here" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="offset-4 col-8 my-2">
                                                <button name="submit" type="submit" class="btn btn-primary">
                                                    <fmt:message key="client.page.update.account"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <h2 class="ml-5"><fmt:message key="client.page.order.history"/></h2>

        <table class="table" style="">
            <thead>
            <tr>
                <th><fmt:message key="cart.page.receipt.id"/></th>
                <th><fmt:message key="cart.page.receipt.creation.time"/></th>
                <th><fmt:message key="cart.page.receipt.update.time"/></th>
                <th><fmt:message key="cart.page.receipt.status"/></th>
                <th><fmt:message key="cart.page.total"/></th>
                <th><fmt:message key="cart.page.action"/></th>
            </tr>
            </thead>
            <tbody>


            </tbody>
        </table>
        <div class="container">

            <div class="row">

            </div>
        </div>
    </div>

</main>
<footer class="text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#"><fmt:message key="to.top"/></a>
        </p>
        <p><fmt:message key="resturant.slogan"/></p>
        <p><fmt:message key="footer.havent.tried"/> <a href="../../"><fmt:message key="footer.bonus"/> </a> <fmt:message
                key="footer.subscribe"/> <a
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