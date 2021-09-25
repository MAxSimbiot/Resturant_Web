<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources"/>
<form action="MainServlet" method="get">
    <div class="form-group row my-2">
        <label for="login" class="col-4 col-form-label"><fmt:message
                key="client.page.login"/></label>
        <div class="col-8">
            <c:if test="${badLogin}">
                <h6 style="color: red"><fmt:message key="login.page.bad.login"/></h6>
            </c:if>
            <input id="login" name="login" value="${client.login}"
                   class="form-control here" required="required" type="text">
        </div>
    </div>
    <div class="form-group row my-2">
        <label for="name" class="col-4 col-form-label"><fmt:message
                key="client.page.name"/></label>
        <div class="col-8">
            <c:if test="${badName}">
                <h6 style="color: red"><fmt:message key="login.page.bad.name"/></h6>
            </c:if>
            <input id="name" name="name" value="${client.name}"
                   class="form-control here" required="required" type="text">
        </div>
    </div>

    <div class="form-group row my-2">
        <label for="pass" class="col-4 col-form-label"><fmt:message
                key="client.page.password"/></label>
        <div class="col-8">
            <c:if test="${badPass}">
                <h6 style="color: red"><fmt:message key="login.page.bad.pass"/></h6>
            </c:if>
            <input id="pass" name="password"
                   placeholder="<fmt:message key="client.page.password"/>"
                   class="form-control here"
                   required="required" type="password">
        </div>
    </div>
    <div class="form-group row my-2">
        <label for="reppass" class="col-4 col-form-label"><fmt:message
                key="client.page.password.repeat"/></label>
        <div class="col-8">
            <c:if test="${diffPass}">
                <h6 style="color: red"><fmt:message key="login.page.bad.pass.match"/></h6>
            </c:if>
            <input id="reppass" name="repeatPassword" placeholder="<fmt:message key="client.page.password.repeat"/>"
                   class="form-control here"
                   required="required" type="password">
        </div>
    </div>

    <div class="form-group row my-2">
        <label for="phone" class="col-4 col-form-label"><fmt:message
                key="client.page.phone"/></label>
        <div class="col-8">
            <c:if test="${badPhone}">
                <h6 style="color: red"><fmt:message key="login.page.bad.phone"/></h6>
            </c:if>
            <input id="phone" name="phone"
                   class="form-control here" type="text" value="${client.phone}">
        </div>
    </div>
    <div class="form-group row">
        <div class="offset-4 col-8 my-2">
            <button name="submit" type="submit" class="btn btn-primary">
                <fmt:message key="client.page.update.account"/>
            </button>
            <div style="color:#029f5b">
                <c:if test="${updateSuccessMsg}">
                    <fmt:message key="client.page.update.success"/>
                </c:if>
            </div>
            <div style="color:red">
                <c:if test="${updateErrorMsg}">
                    <fmt:message key="client.page.update.fail"/>
                </c:if>
            </div>
        </div>
    </div>
    <input type="hidden" name="id" value="${client.id}"/>
    <input type="hidden" name="command" value="updateClient"/>
</form>