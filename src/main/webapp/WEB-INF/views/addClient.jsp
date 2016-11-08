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
        <jsp:include page="header.jsp" />
    </header>
    <nav>

        <jsp:include page="navBar.jsp" />

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">


            <form action="" method="GET">
                <p><b>Input data of a new client:</b></p>
                <td>first name: *</td><br>
                <td><input type="text" name="firstName"></td><br>

                <td>last name: *</td><br>
                <td><input type="text" name="lastName"></td><br>

                <td>date of birth: *</td><br>
                <td><input type="date" name="birthDay" value="" max="1999-12-31" min="1900-01-01"></td><br>

                <td>passport number: *</td><br>
                <td><input type="text" name="passport"></td><br>

                <td>address:</td><br>
                <td><input type="text" name="address"></td><br>

                <td>e-mail:</td><br>
                <td><input type="text" name="email"></td><br>

                <td>password: *</td><br>
                <td><input type="text" name="password"></td><br>

                <br>
                <input type="submit" value="Add a new client" />

                <br><br>
                ${message} ${error}
                <br><br>

            </form>



        </section>
        <aside>
            --this is an aside block--
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