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
        <section id="page-content">


            <form action="" method="GET">
                <p>Input phone number:<br/>
                    <input type="text" name="phone"><br>
                    <small>(xxx)xxx-xxxx for example</small>
                </p>
                <input type="submit" value="Submit" />
                <br/><br/>
                ${contract.getPhone()} ${error}
                <br/><br/>
                ${contract.getTariff().getName()}
                <br/><br/>
                ${contract.getClient().getFirstName()}
                <br/>
                ${contract.getClient().getLastName()}
                <br/>
                ${contract.getClient().getBirthDay()}
                <br/><br/>
                <c:forEach var="option" items="${contract.getOptionList()}">
                    ${option.getName()}
                    <br/>
                </c:forEach>
                <br/>
                ${contract.getIs_locked()}
                <br/><br/><br/>
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

