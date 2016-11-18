<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>--This is a title--</title>
    <link href="resources/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Обертка  -->
    <header>
        <jsp:include page="../header.jsp" />
    </header>
    <nav>


    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">

            <!-- сообщение или ощибка -->
            ${message} ${error}
            <br>

            <!-- Форма добавления клиента -->
            <form action="/addClient" method="GET">

                <div class="form-element">
                    <label for="firstName">First name:</label>
                    <input type="text" name="firstName">
                </div>
                <div class="form-element">
                    <label for="lastName">Last name:</label>
                    <input type="text" name="lastName">
                </div>
                <div class="form-element">
                    <label for="birthDay">Date of birth:</label>
                    <input type="date" name="birthDay" value="" max="1999-12-31" min="1900-01-01">
                </div>
                <div class="form-element">
                    <label for="passNo">Passport:</label>
                    <input type="text" name="passNo">
                </div>
                <div class="form-element">
                    <label for="address">Address:</label>
                    <input type="text" name="address">
                </div>

                <!-- кнопка -->
                <div class="form-element">
                    <input type="submit" value="Add a new client" />
                </div>
                <br>

            </form>


        </section>
        <aside>

            <jsp:include page="../navBar.jsp" />

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