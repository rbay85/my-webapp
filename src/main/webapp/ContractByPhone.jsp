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
                <input type="submit" value="Submit" />
            </form>
            ${contractByPhone_phone} ${error}
            <br/><br/>
            ${contractByPhone_tariff}
            <br/><br/>
            ${contractByPhone_clientFN}
            <br/>
            ${contractByPhone_clientLN}
            <br/>
            ${contractByPhone_clientBD}
            <br/><br/>
            <c:forEach var="option" items="${contractByPhone_optionList}">
                ${option.getName()}
                <br/>
            </c:forEach>
            <br/>lock condition:
            ${contractByPhone_isLocked}
            <br/><br/><br/>


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

