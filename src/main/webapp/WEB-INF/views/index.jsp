<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <!-- Объявляем заголовк, кодироку стили и т.д -->
        <meta contentType="text/html" charset="utf-8">
        <title>Login form</title>
        <link href="../../resources/style.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <!-- Обертка  -->
            <header>

                <jsp:include page="header.jsp" />

            </header>
            <nav>

                <a href="j_spring_security_logout" /> LogOut </a>

            </nav>
            <section id="content" class="clearfix">
                <section id="page-content">



                </section>
                <aside>

                    <jsp:include page="navBar.jsp" />

                </aside>
            </section>
            <div id="empty-div">
                --this is an "empty" div--
            </div>
        </div>
        <footer>
            --this is a footer--
        </footer>
    </body>
</html>
