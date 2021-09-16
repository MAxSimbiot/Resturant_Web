<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>LoginPage</title>
    <script src="jquery-3.5.1.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script>
        $(function() {

            $('#login-form-link').click(function(e) {
                $("#login-form").delay(100).fadeIn(100);
                $("#register-form").fadeOut(100);
                $('#register-form-link').removeClass('active');
                $(this).addClass('active');
                e.preventDefault();
            });
            $('#register-form-link').click(function(e) {
                $("#register-form").delay(100).fadeIn(100);
                $("#login-form").fadeOut(100);
                $('#login-form-link').removeClass('active');
                $(this).addClass('active');
                e.preventDefault();
            });

        });
    </script>
</head>
<body>



<div class="container">

    <div class="row">

        <h2><fmt:message key="login.page.please.login"/></h2>
        <div class="col-md-6 col-md-offset-3">

            <div class="panel panel-login">

                <div class="panel-heading">
                    <div class="row">

                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link"><fmt:message key="login.page.login"/></a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link"><fmt:message key="login.page.register"/></a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="MainServlet" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="login" id="username" required="required" tabindex="1" class="form-control" placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" required="required" tabindex="2" class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group text-center" style="color:red">
                                    ${errorMsg}
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <button type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login"><fmt:message key="login.page.login"/></button>
                                            <input type="hidden" name="command" value="logIn"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" action="MainServlet" method="post" role="form" style="display: none;">
                                <div class="form-group">
                                    <input type="text" name="login" id="username" required="required" tabindex="1" class="form-control" placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="name" id="email" required="required" tabindex="1" class="form-control" placeholder="Name" value="">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="phone" id="username" required="required" tabindex="2" class="form-control" placeholder="Phone">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" required="required" tabindex="2" class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="repPassword" id="confirm-password" required="required" tabindex="2" class="form-control" placeholder="Confirm Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <button type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register"><fmt:message key="login.page.register"/></button>
                                            <input type="hidden" name="command" value="register"/>
                                            <div style="color:red">${errorMsg}</div>
                                        </div>
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

</body>
</html>