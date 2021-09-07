
<header>
    <a style="margin-right: 4%">RESTURANT_WEB</a>
    <form action="MainServlet" method="post" style="align-self: end">
        <button type="submit" name="language" value="en"><fmt:message key="en"/></button>
        <button type="submit" name="language" value="ru"><fmt:message key="ru"/></button>
        <input type="hidden" id = "command" name="command" value="changeLanguage"/>
    </form>
</header>
