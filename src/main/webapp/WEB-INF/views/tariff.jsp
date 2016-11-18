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

            <!-- Форма добавления тарифа -->
            <br>
            <form action="addTariff" method="GET">

                <div class="form-element">
                    <label for="name">Tariff name:</label>
                    <input type="text" name="name">
                </div>
                <div class="form-element">
                    <label for="price">Price:</label>
                    <input type="text" name="price">
                </div>

                <!-- кнопки -->
                <div class="form-element">
                    <input type="reset" value="Clear" />
                    <input type="submit" value="Add a new tariff" />
                </div>
                <br>

            </form>

        </aside>
        <section id="page-content">

            <!-- сообщение или ощибка -->
            ${message} ${error}
            <br>

            <!-- форма добавления опции в тариф ( тут же удаление тарифа ) -->
            <form action="addOptionInTariff" method="GET">

                <!-- таблица с тарифами -->
                <table>
                    <tr>
                        <td><b>Tariff name</b></td>
                        <td><b>Price</b></td>
                        <td><b>Options</b></td>
                    </tr>
                    <c:forEach var="tariff" items="${tariffList}">
                        <tr>
                            <td>
                                <input type="radio" name="tariffId" value="${tariff.id}">
                                ${tariff.name}
                                <a href="deleteTariff?id=${tariff.id}" style="color:red;">X</a>
                            </td>
                            <td>${tariff.price}</td>
                            <td>
                                <c:forEach var="option" items="${tariff.optionList}">
                                    ${option.name}
                                    <a href="deleteOptionFromTariff?tariffId=${tariff.id}&optionId=${option.id}" style="color:red;">x</a>
                                    <br/>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                Add option
                <!-- выпадающий список -->
                <select name="optionId" required>
                    <option value="0"> </option>
                    <c:forEach var="option" items="${optionList}">
                        <option value="${option.id}">${option.name}</option>
                    </c:forEach>
                </select>
                in the tariff chosen above

                <!-- кнопка -->
                <input type="submit" value="Add" />
                <br><br>

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