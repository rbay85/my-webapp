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
                            <option value="${client.getId()}"><pre>${client.getFirstName()} ${client.getLastName()}</pre></option>
                        </c:forEach>
                    </select>
                </div>

                <!-- тариф -->
                <div class="form-element">
                    <label for="tariff">Tariff:</label>
                    <select name="tariffId" required id="tariff">
                        <option value="0"> </option>
                        <c:forEach var="tariff" items="${tariffList}">
                            <option value="${tariff.getId()}">${tariff.getName()}</option>
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
            <form action="addOptionInContract" method="GET">

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
                                <input type="radio" name="contractId" value="${contract.getId()}">
                                ${contract.getPhone()}
                                <%--<a href="deleteContract?id=${contract.getId()}" style="color:red;">X</a>--%>
                                <br>
                                <a href="adminLockContract?id=${contract.getId()}&condition=lock">lock</a>
                                <a href="adminLockContract?id=${contract.getId()}&condition=unlock">unlock</a>
                            </td>
                            <td>
                                ${contract.getClient().getFirstName()}
                                <br/>
                                ${contract.getClient().getLastName()}
                            </td>
                            <td>
                                ${contract.getTariff().getName()}
                                <br/>
                                <!-- выпадающий список -->
                                <select name="optionId" onchange="this.form.submit()" required>
                                    <option value="0&contractId=${contract.getId()}"> </option>
                                    <c:forEach var="option" items="${contract.tariff.optionList}">
                                        <option value="${option.getId()}&contractId=${contract.getId()}">${option.getName()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <c:forEach var="option" items="${contract.getOptionList()}">
                                    ${option.getName()}
                                    <%--<a href="deleteOptionFromTariff?tariffId=${tariff.getId()}&optionId=${option.getId()}" style="color:red;">x</a>--%>
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>${contract.getIsLocked()}</td>
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