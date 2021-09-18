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
                        <button type="submit" class="btn btn-secondary">${client.name}</button>
                        <input type="hidden" name="command" value="goToCabinet"/>
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
            <p class="lead text-muted"><fmt:message key="cart.page.head.message"/></p>
            <p></p>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <h2 class="ml-5"><fmt:message key="cart.page.orders.list"/></h2>

        <table class="table" style="">
            <thead>
            <tr>
                <th><fmt:message key="cart.page.receipt.id"/></th>
                <th><fmt:message key="cart.page.receipt.creation.time"/></th>
                <th><fmt:message key="cart.page.receipt.update.time"/></th>
                <th><fmt:message key="cart.page.receipt.status"/></th>
                <th><fmt:message key="cart.page.total"/></th>
                <c:if test="${receipt.statusId == 1}">
                    <th><fmt:message key="cart.page.action"/></th>
                </c:if>
                <th></th>

            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!receipt.equals(null)}">
                    <tr>
                        <th scope="row">${receipt.id}</th>
                        <td>${receipt.creationTime}</td>
                        <td>${receipt.lastUpdate}</td>
                        <td>${receipt.statusEntity.name} (${receipt.statusEntity.description})</td>
                        <td><% int total = 0;
                            Receipt receipt = (Receipt) request.getAttribute("receipt");
                            List<Product> productList = receipt.getProducts();
                            if (productList != null) {
                                for (Product p : productList) {
                                    total += p.getPrice() * p.getCount();
                                }
                            }
                            out.print(total);%>
                            <fmt:message key="currency.grn"/></td>
                        <td>
                            <c:if test="${receipt.statusId<=2}">
                                <form action="MainServlet" method="post">
                                    <button type="submit" name="receiptId" value="${receipt.id}" class="btn btn-primary"
                                            style=""><fmt:message
                                            key="cart.page.delete"/></button>
                                    <input type="hidden" name="command" value="deleteReceipt"/>
                                </form>

                            </c:if>
                        </td>
                        <td>
                            <c:if test="${receipt.statusId==1}">
                                <form action="MainServlet" method="post">
                                    <button type="submit" name="receiptId" value="${receipt.id}" class="btn btn-primary"
                                            style=""><fmt:message key="cart.page.order"/></button>
                                    <input type="hidden" name="command" value="makeOrder"/>
                                </form>

                            </c:if>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <fmt:message key="cart.no.receipt"/>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
        <div class="container">

            <div class="row">
                <c:choose>
                    <c:when test="${(receipt!=null)&&(receipt.products!=null)&&(receipt.products.size()>0)}">
                        <c:forEach var="product" items="${receipt.products}">
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
                                            <strong>${product.price * product.count} <fmt:message
                                                    key="currency.grn"/></strong>
                                            <h4 class="text-muted"><fmt:message
                                                    key="cart.page.count"/> ${product.count}</h4>
                                            <div class="btn-group">
                                                <c:if test="${receipt.statusId == 1}">
                                                    <form action="MainServlet" method="post">
                                                        <button type="submit" name="productId" value="${product.id}"
                                                                class="btn btn-sm btn-outline-secondary">
                                                            <fmt:message key="cart.page.delete"/>
                                                        </button>
                                                        <input type="hidden" name="receiptId" value="${receipt.id}"/>
                                                        <input type="hidden" name="command" value="deleteFromCart"/>
                                                    </form>
                                                </c:if>
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
