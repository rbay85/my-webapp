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

            <!-- Форма добавления клиента -->
            <form action="/addClient" method="GET">

                <div class="form-element">
                    <label for="firstName">First name:</label>
                    <input type="text" name="firstName">
                </div>
                <div class="form-element">
                    <label for="lastName">Last name:</label>
                    <input type="text" name="lastName">
                </div>
                <div class="form-element">
                    <label for="birthDay">Date of birth:</label>
                    <input type="date" name="birthDay" value="" max="1999-12-31" min="1900-01-01">
                </div>
                <div class="form-element">
                    <label for="passNo">Passport:</label>
                    <input type="text" name="passNo">
                </div>
                <div class="form-element">
                    <label for="address">Address:</label>
                    <input type="text" name="address">
                </div>

                <!-- кнопка -->
                <div class="form-element">
                    <input type="submit" value="Add a new client" />
                </div>
                <br>

            </form>

        </aside>
        <section id="page-content">

            <!-- сообщение или ощибка -->
            ${message} ${error}
            <br>

            <!-- форма добавления юзера в клинета -->
            <form action="addUserInClient" method="GET">

                <!-- таблица с клиентами -->
                <table>
                    <tr>
                        <td><b>Passport</b></td>
                        <td><b>Client</b></td>
                        <td><b>Username</b></td>
                        <td><b>Address</b></td>
                        <td><b>Date of birth</b></td>
                        <td><b>Contract</b></td>
                        <td><b>Tariff</b></td>
                        <td><b>Lock</b></td>
                    </tr>
                    <c:forEach var="client" items="${clientList}">
                        <tr>
                            <td>
                                <input type="radio" name="clientId" value="${client.id}">
                                ${client.passNo}
                            </td>
                            <td>
                                    ${client.firstName}
                                <br>
                                    ${client.lastName}
                            </td>
                            <td>${client.user.email}</td>
                            <td>${client.address}</td>
                            <td>${client.birthDay}</td>
                            <td>
                                <c:forEach var="contract" items="${client.contractList}">
                                    ${contract.phone}
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="contract" items="${client.contractList}">
                                    ${contract.tariff.name}
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="contract" items="${client.contractList}">
                                    ${contract.isLocked}
                                    <br/>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>

                <div class="form-element">
                    <label for="email">Username:</label>
                    <input type="text" name="email">
                </div>
                <div class="form-element">
                    <input type="checkbox" name="role" value="admin">mark as admin<br>
                </div>

                <!-- кнопка -->
                <div class="form-element">
                    <input type="submit" value="Add user for client" />
                </div>
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
