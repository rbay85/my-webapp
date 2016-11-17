<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>ERROR 403</title>
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

            <h2>
                HTTP Status 403:
                <br>
                <br>
                Sorry, access forbidden!
            </h2>

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
