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
        <jsp:include page="Header.jsp" />
    </header>
    <nav>

        <jsp:include page="NavBar.jsp" />

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">


            <form action="" method="GET">
                <p>Input phone number:<br/>
                    <input type="text" name="phone"><br>
                    <small>(xxx)xxx-xxxx for example</small>
                </p>
                <p><b>action:</b><br>
                    <input type="radio" name="condition" value="lock">lock<br>
                    <input type="radio" name="condition" value="unlock">unlock<br>
                </p>
                <input type="submit" value="Apply"/>
                <br>
                <br>
                ${message} ${error}
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