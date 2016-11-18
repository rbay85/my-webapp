<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>--This is a title--</title>
    <link href="../../resources/style1.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Обертка  -->
    <header>

        <jsp:include page="header.jsp" />

    </header>
    <nav>

        <a href="j_spring_security_logout" class="navBar" > LogOut </a>

    </nav>
    <section id="content" class="clearfix">
        <aside>

            <h2>
                &nbsp;&nbsp;
                Dear ${client.firstName} ${client.lastName}, welcome :)
            </h2>

        </aside>
        <section id="page-content">

            <form action="" method="GET">
                <p>
                    Your contract(s):<br/>
                    <c:forEach var="contract" items="${client.contractList}">
                        <input type="radio" name="contractId" value="${contract.id}">${contract.phone}
                        <br/>
                    </c:forEach>
                </p>

                <p>Choose an action:<br>
                    <input type="radio" name="condition" value="lock">lock<br>
                    <input type="radio" name="condition" value="unlock">unlock<br>
                </p>
                <input type="submit" value="Apply"/>
                <br>
                <br>
                ${message}${error}
                <br>
            </form>

        </section>
    </section>
    <div id="empty-div">

    </div>
</div>
<footer>

</footer>
</body>
</html>