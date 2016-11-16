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

        <a href="j_spring_security_logout"/> LogOut </a>

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">

            <!-- сообщение или ощибка -->
            ${message} ${error}
            <br>

            <!-- Форма добавления контракта -->
            <form action="addContract" method="GET">

                <!-- номер телефона -->
                <div class="form-element">
                    <label for="phone">Phone number:</label>
                    <input type="text" name="phone" id="phone">
                    <br>
                    <small>format: (xxx)xxx-xxxx</small>
                </div>

                <!-- клиент -->
                <div class="form-element">
                    <label for="client">Client:</label>
                    <select name="clientId" required id="client">
                        <option value="0"> </option>
                        <c:forEach var="client" items="${clientList}">
                            <option value="${client.id}"><pre>${client.firstName} ${client.lastName}</pre></option>
                        </c:forEach>
                    </select>
                </div>

                <!-- тариф -->
                <div class="form-element">
                    <label for="tariff">Tariff:</label>
                    <select name="tariffId" required id="tariff">
                        <option value="0"> </option>
                        <c:forEach var="tariff" items="${tariffList}">
                            <option value="${tariff.id}">${tariff.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- кнопка-->
                <div class="form-element">
                    <input type="submit" value="Add a new contract" />
                </div>
                <br>

            </form>

            <!-- форма добавления опции в контракт ( тут же удаление опции ) -->
            <form action=" " method="GET">

                <!-- таблица с тарифами -->
                <table>
                    <tr>
                        <td><b>Phone number</b></td>
                        <td><b>Client</b></td>
                        <td><b>Tariff</b></td>
                        <td><b>Options</b></td>
                        <td><b>Lock</b></td>
                    </tr>
                    <c:forEach var="contract" items="${contractList}">
                        <tr>
                            <td>
                                <input type="radio" name="contractId" value="${contract.id}">
                                ${contract.phone}
                                <%--<a href="deleteContract?id=${contract.getId()}" style="color:red;">X</a>--%>
                                <br>
                                <a href="adminLockContract?id=${contract.id}&condition=lock">lock</a>
                                <a href="adminLockContract?id=${contract.id}&condition=unlock">unlock</a>
                            </td>
                            <td>
                                ${contract.client.firstName}
                                <br/>
                                ${contract.client.lastName}
                            </td>
                            <td>
                                ${contract.tariff.name}
                                <br/>
                                <!-- ссылка добавления опции -->
                                    <c:forEach var="option" items="${contract.tariff.optionList}">
                                        <a href="addOptionInContract?optionId=${option.id}&contractId=${contract.id}""><small>${option.name}</small></a>
                                        <br/>
                                    </c:forEach>
                            </td>
                            <!-- удаление опции -->
                            <td>
                                <c:forEach var="option" items="${contract.optionList}">
                                    ${option.name}
                                    <a href="deleteOptionFromContract?contractId=${contract.id}&optionId=${option.id}" style="color:red;">x</a>
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>
                                ${contract.isLocked}
                            </td>
                        </tr>
                    </c:forEach>
                </table>


                <!-- кнопка -->
                <input type="submit" value="Add" />
                <br><br>

            </form>

        </section>
        <aside>

            <jsp:include page="navBar.jsp" />

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