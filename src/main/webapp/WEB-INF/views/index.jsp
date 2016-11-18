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

                <jsp:include page="navBar.jsp" />

            </nav>
            <section id="content" class="clearfix">
                <section id="page-content">

                    <h3>
                        &nbsp;&nbsp;
                        Dear Admin, welcome :)
                        <br>
                    </h3>

                </section>
                <aside>



                </aside>
            </section>
            <div id="empty-div">

            </div>
        </div>
        <footer>

        </footer>
    </body>
</html>
