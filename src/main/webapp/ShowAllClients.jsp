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

        <jsp:include page="NavBar.jsp" />

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">


            <table>
                <tr>
                        <td><b>ID</b></td>
                        <td><b>First Name</b></td>
                        <td><b>Last Name</b></td>
                        <td><b>Phones</b></td>
                        <td><b>Date of birth</b></td>
                </tr>
                <c:forEach var="client" items="${clientList}">
                        <tr>
                            <td>${client.getId()}</td>
                            <td>${client.getFirstName()}</td>
                            <td>${client.getLastName()}</td>
                            <td>
                                <c:forEach var="contract" items="${client.getContractList()}">
                                    ${contract.getPhone()}
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>${client.getBirthDay()}</td>
                        </tr>
                </c:forEach>
            </table>


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
