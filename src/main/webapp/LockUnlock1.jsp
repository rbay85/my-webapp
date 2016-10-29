<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>--This is a title--</title>
    <link href="style1.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Обертка  -->
    <header>
        <jsp:include page="Header.jsp" />
    </header>
    <nav>

        <jsp:include page="NavBar1.jsp" />

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">


            <form action="" method="GET">
                <p>
                    Your contract(s):<br/>
                    <c:forEach var="contract" items="${client.getContractList()}" varStatus = "contractLoopCount">
                        <input type="checkbox" name="phoneCount" value="${contractLoopCount.count}">${contract.getPhone()}
                        <br/>
                    </c:forEach>
                </p>

                <p>Choose an action:<br>
                    <input type="radio" name="condition" value="lock">lock<br>
                    <input type="radio" name="condition" value="unlock">unlock<br>
                </p>
                <input type="submit" value="Apply"/>

            </form>
            ${message} ${error}
            <br/>


        </section>
        <aside>

            <p><h2>Dear ${client.getFirstName()} ${client.getLastName()}, <br> welcome!!</h2></p>

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