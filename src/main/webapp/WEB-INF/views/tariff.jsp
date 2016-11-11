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

        <a href="<c:url value="/logout" />"> LogOut </a>

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">

            <!-- сообщение или ощибка -->
            ${message} ${error}
            <br>

            <!-- Форма добавления тарифа -->
            <form action="addTariff" method="GET">

                <td>Tariff name: *</td><br>
                <td><input type="text" name="name"></td><br>

                <td>Price: *</td><br>
                <td><input type="text" name="price"></td><br>

                <!-- кнопка -->
                <br>
                <input type="submit" value="Add a new tariff" />

            </form>

            <!-- форма добавления опции в тариф -->

            <form action="optionInTariff" method="GET">

                <!-- таблица с тарифами -->
                <table>
                    <tr>
                        <td><b> </b></td>
                        <td><b>Name</b></td>
                        <td><b>Price</b></td>
                        <td><b>Options</b></td>
                        <td><b>Action</b></td>
                    </tr>
                    <c:forEach var="tariff" items="${tariffList}">
                        <tr>
                            <%--<td>${tariff.getId()}</td>--%>
                            <td><input type="radio" name="tariffId" value="${tariff.getId()}"> </td>
                            <td>${tariff.getName()}</td>
                            <td>${tariff.getPrice()}</td>
                            <td>
                                <c:forEach var="option" items="${tariff.getOptionList()}">
                                    ${option.getName()}
                                    <br/>
                                </c:forEach>
                            </td>
                            <td><a href="deleteTariff?id=${tariff.getId()}">delete</a></td>
                        </tr>
                    </c:forEach>
                </table>

                <br>
                Add option
                <select name="optionId" required>
                    <option value="0"> </option>
                    <option value="1">2 GB internet</option>
                    <option value="2">4 GB internet</option>
                    <option value="3">10 GB internet</option>
                    <option value="4">100 sms</option>
                    <option value="5">200 sms</option>
                    <option value="6">400 sms</option>
                    <option value="7">200 minute</option>
                    <option value="8">300 minute</option>
                    <option value="9">500 minute</option>
                    <option value="10">internet service</option>
                    <option value="11">sms service</option>
                </select>
                in the tariff chosen above

                <!-- кнопки -->
                <input type="submit" name="action" value="Add" />
                <input type="submit" name="action" value="Delete" />
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