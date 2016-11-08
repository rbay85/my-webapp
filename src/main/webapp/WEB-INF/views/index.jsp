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


            </nav>
            <section id="content" class="clearfix">
                <section id="page-content">

                    <form action="action.html">
                        <h3>Login please</h3>
                        <div class="form-element">
                            <label for="username">Username:</label>
                            <input type="text" name="username"/>
                        </div>
                        <div class="form-element">
                            <label for="password">Password:</label>
                            <input type="password" name="password"/>
                        </div>
                        <div class="form-element">
                            <input type="submit" value="Log in"/>
                        </div>
                    </form>

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
