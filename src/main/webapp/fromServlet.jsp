<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>--This is a title--</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Обертка  -->
    <header>
        --this is a header--
    </header>
    <nav>
        --this is a navigation bar--
    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">
            <form action="" method="GET">
                <p>Input client ID: <input type="number" name="id"></p>
                <input type="submit" value="Submit" />
            </form>
            <br/>
            ${client}
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

