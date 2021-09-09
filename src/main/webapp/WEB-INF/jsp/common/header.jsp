
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

            <div class="btn-group" role="group" aria-label="Basic example" style="">
                <form action="MainServlet" method="post">
                    <button type="button" class="btn btn-secondary"><fmt:message key="header.log.out"/></button>
                    <input type="hidden" name="command" value="logOut"/>
                </form>

                <c:choose>
                    <c:when test="${cleint.name.length()>0}">
                        <form action="MainServlet" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="header.account"/></button>
                            <input type="hidden" name="command" value="goToLogin"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="MainServlet" method="post">
                            <button type="submit" class="btn btn-secondary">${client.name}</button>
                            <input type="hidden" name="command" value="goToCabinet"/>
                        </form>
                    </c:otherwise>
                </c:choose>

                <form action="MainServlet" method="post">
                    <button type="submit" class="btn btn-secondary" style="background-color: rgb(243, 18, 18); color: rgb(255, 255, 255); line-height: 23px; text-align: left; font-weight: 700;">
                        <fmt:message key="header.cart"/>
                    </button>
                    <input type="hidden" name="command" value="receipt"/>
                </form>
            </div>

        </div>

    </div>
</header>