<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>LoginPage</title>
</head>
<body>
<form action="MainServlet" method="post">
    <input type="text" name="login"/>
    <input type="text" name="password"/>
    <button type="submit">LoGin</button>
    <input type="hidden" name="command" value="logIn"/>
</form>
</body>
</html>