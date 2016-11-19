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

            <!-- сообщение или ощибка -->
            ${message} ${error}
            <br>

            <!-- форма смены тарифа в контракте -->
            <form action="userChangesTariffInContract" method="GET">

                <!-- таблица с тарифами -->
                <table>
                    <tr>
                        <td><b>Contract</b></td>
                        <td><b>Tariff</b></td>
                        <td><b>Options</b></td>
                        <td><b>Lock</b></td>
                    </tr>
                    <c:forEach var="contract" items="${client.contractList}">
                        <tr>
                            <td>
                                <input type="radio" name="contractId" value="${contract.id}">
                                    ${contract.phone}
                                <br>
                                <!-- блокировка/разблокировка -->
                                <a href="userLockContract?id=${contract.id}&condition=lock">lock</a>
                                <a href="userLockContract?id=${contract.id}&condition=unlock">unlock</a>
                            </td>
                            <td>
                                    ${contract.tariff.name}
                                <br/>
                                <!-- ссылка добавления опции -->
                                <c:forEach var="option" items="${contract.tariff.optionList}">
                                    <a href="userAddsOptionInContract?optionId=${option.id}&contractId=${contract.id}"><small>${option.name}</small></a>
                                    <br/>
                                </c:forEach>
                            </td>
                            <!-- удаление опции -->
                            <td>
                                <c:forEach var="option" items="${contract.optionList}">
                                    ${option.name}
                                    <a href="userDeleteOptionFromContract?contractId=${contract.id}&optionId=${option.id}" style="color:red;">x</a>
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>
                                    ${contract.isLocked}
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <!-- смена тарифа -->
                Change tariff in the contract chosen above to
                <select name="tariffId" required>
                    <option value="0"> </option>
                    <c:forEach var="tariff" items="${tariffList}">
                        <option value="${tariff.id}">${tariff.name}</option>
                    </c:forEach>
                </select>

                <!-- кнопка -->
                <input type="submit" value="Change" />
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