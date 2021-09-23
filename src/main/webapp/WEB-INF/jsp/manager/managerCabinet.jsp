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

    <title>Manager Workspace</title>

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

                <strong><fmt:message key="resturant.name"/> </strong>
            </a>
            <strong><fmt:message key="manager.page.header"/></strong>

            <div class="btn-group" role="group" aria-label="Basic example" style="">
                <form action="MainServlet" method="post">
                    <form action="MainServlet" method="get">
                        <button type="submit" class="btn btn-secondary"><fmt:message key="header.log.out"/></button>
                        <input type="hidden" name="command" value="logOut"/>
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
                                    <jsp:include page="/WEB-INF/jsp/common/updateUserPage.jsp" flush="true"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <h2 class="ml-5"><fmt:message key="manager.page.orders"/></h2>

        <table class="table" style="">
            <thead>
            <tr>
                <th><fmt:message key="cart.page.receipt.id"/></th>
                <th><fmt:message key="cart.page.receipt.creation.time"/></th>
                <th><fmt:message key="cart.page.receipt.update.time"/></th>
                <th><fmt:message key="cart.page.receipt.status"/></th>
                <th>Products</th>
                <th><fmt:message key="cart.page.total"/></th>
                <th>Client name</th>
                <th> phone</th>
                <th>Change status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:choose>
                <c:when test="${!receiptList.equals(null)}">
                    <c:forEach var="receipt" items="${receiptList}">
                        <c:if test="${receipt.statusEntity.id > 1 && receipt.statusEntity.id < 7}">

                            <tr>
                                <c:set var="rec" value="${receipt}"/>
                                <th scope="row">${receipt.id}</th>
                                <td>${receipt.creationTime}</td>
                                <td>${receipt.lastUpdate}</td>
                                <td>${receipt.statusEntity.name} (${receipt.statusEntity.description})</td>
                                <td>
                                    <c:forEach var="product" items="${receipt.products}">
                                        ${product.name} (${product.count})
                                    </c:forEach>
                                </td>
                                <td>
                                    <% int total = 0;
                                        Receipt receipt = (Receipt) pageContext.getAttribute("rec");
                                        List<Product> productList = receipt.getProducts();
                                        if (productList != null) {
                                            for (Product p : productList) {
                                                total += p.getPrice() * p.getCount();
                                            }
                                        }
                                        request.setAttribute("total", total);%>
                                        ${total}
                                    <fmt:message key="currency.grn"/></td>

                                <th>${clients.get(receipt.clientId).name}</th>
                                <th>${clients.get(receipt.clientId).phone}</th>
                                <form action="MainServlet" method="get">
                                    <td>
                                        <select class="form-control" name="statusId">
                                            <option value="${receipt.statusEntity.id}">${receipt.statusEntity.name}</option>
                                            <option value="3">Accepted</option>
                                            <option value="4">Cooking</option>
                                            <option value="5">On Way</option>
                                            <option value="6">Paid</option>
                                            <option value="7">Done</option>
                                            <option value="8">Cancelled</option>
                                        </select>
                                    </td>
                                    <td>
                                        <button type="submit" name="receiptId" value="${receipt.id}"
                                                class="btn btn-primary"
                                                style="">Change status
                                        </button>
                                        <input type="hidden" name="command" value="changeReceiptStatus"/>
                                    </td>
                                </form>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <fmt:message key="cart.no.receipt"/>
                </c:otherwise>
            </c:choose>
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