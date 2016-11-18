<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>--This is a title--</title>
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
        <aside>



        </aside>
        <section id="page-content">

            <form action="" method="GET">
                <p>Input phone number:<br/>
                    <input type="text" name="phone"><br>
                    <small>(xxx)xxx-xxxx for example</small>
                </p>
                <input type="submit" value="Submit" />
                <br/><br/>
                ${contract.phone} ${error}
                <br/><br/>
                ${contract.tariff.name()}
                <br/><br/>
                ${contract.client.firstName}
                <br/>
                ${contract.client.lastName}
                <br/>
                ${contract.client.birthDay}
                <br/><br/>
                <c:forEach var="option" items="${contract.optionList}">
                    ${option.name}
                    <br/>
                </c:forEach>
                <br/>
                ${contract.isLocked}
                <br/><br/><br/>
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

