<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources"/>
<html >
<head>
    <title>LoginPage</title>
    <meta charset="utf-8">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>


<div class="container">


    <h2><fmt:message key="login.page.please.login.or.register"/></h2>


    <div class="panel panel-login">

        <div class="panel-heading">
            <div class="row">

                <div class="col-xs-6">
                    <fmt:message key="login.page.login"/>
                </div>
                <div class="col-xs-6">
                    <fmt:message key="login.page.register"/>
                </div>
            </div>
            <hr>
        </div>


        <div class="row">
            <div class="col-lg-5">
                <form action="MainServlet" method="post">
                    <div class="form-group">
                        <input type="text" name="login" id="username" required="required"
                               tabindex="1"
                               class="form-control" placeholder="Username" value="">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" required="required"
                               tabindex="2" class="form-control" placeholder="Password">
                    </div>
                    <c:if test="${loginErrorMsg}">
                        <div class="form-group text-center" style="color:red">
                            <fmt:message key="login.page.login.fail"/>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <button type="submit" name="login-submit" id="login-submit"
                                        tabindex="4"
                                        class="form-control btn btn-login"><fmt:message
                                        key="login.page.login"/></button>
                                <input type="hidden" name="command" value="logIn"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-5">
                <form action="MainServlet" method="get">
                    <div class="form-group">
                        <c:if test="${badLogin}">
                            <h5 style="color: red"><fmt:message key="login.page.bad.login"/></h5>
                        </c:if>
                        <input type="text" name="login"  required="required"
                               tabindex="1"
                               class="form-control" placeholder="Username" value=""/>
                    </div>
                    <div class="form-group">
                        <c:if test="${badName}">
                            <h5 style="color: red"><fmt:message key="login.page.bad.name"/></h5>
                        </c:if>
                        <input type="text" name="name"  required="required"
                               class="form-control" placeholder="Name" />
                    </div>
                    <div class="form-group">
                        <c:if test="${badPhone}">
                            <h5 style="color: red"><fmt:message key="login.page.bad.phone"/></h5>
                        </c:if>
                        <input type="text" name="phone"  required="required"
                               tabindex="2"
                               class="form-control" placeholder="Phone"/>
                    </div>
                    <div class="form-group">
                        <c:if test="${badPass}">
                            <h5 style="color: red"><fmt:message key="login.page.bad.pass"/></h5>
                        </c:if>
                        <input type="password" name="password"  required="required"
                               tabindex="2" class="form-control" placeholder="Password"/>
                    </div>
                    <div class="form-group">
                        <c:if test="${diffPass}">
                            <h5 style="color: red"><fmt:message key="login.page.bad.pass.match"/></h5>
                        </c:if>
                        <input type="password" name="repPassword"
                               required="required" tabindex="2" class="form-control"
                               placeholder="Confirm Password"/>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <button type="submit" name="register-submit" id="register-submit"
                                        tabindex="4" class="form-control btn btn-register">
                                    <fmt:message
                                            key="login.page.register"/></button>
                                <input type="hidden" name="command" value="register"/>
                                <div style="color:#029f5b">
                                    <c:if test="${registerSuccessMsg}">
                                        <fmt:message key="login.page.register.success"/>
                                    </c:if>
                                </div>
                                <div style="color:red">
                                    <c:if test="${registerErrorMsg}">
                                        <fmt:message key="login.page.register.fail"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>


    </div>
</div>

</body>
</html>