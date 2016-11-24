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

            <!-- Форма добавления опции -->
            <br>
            <form action="addOption" method="GET">

                <div class="form-element">
                    <label for="name">Option name:</label>
                    <input type="text" name="name">
                </div>
                <div class="form-element">
                    <label for="price">Price:</label>
                    <input type="text" name="price">
                </div>
                <div class="form-element">
                    <label for="cost">Cost:</label>
                    <input type="text" name="cost">
                </div>

                <!-- кнопки -->
                <div class="form-element">
                    <input type="reset" value="Clear" />
                    <input type="submit" value="Add a new option" />
                </div>
                <br>


                <!-- сообщение или ощибка -->
                <p>
                    <font color="red">
                        <b>${param.message} ${param.error}</b>
                    </font>
                </p>
                <br>

            </form>

        </aside>
        <section id="page-content">

            <!-- таблица с опциями -->
            <table>

                <tr>
                    <td><b>Option</b></td>
                    <td><b>Price</b></td>
                    <td><b>Cost</b></td>
                    <td><b>Requires</b></td>
                    <td><b>Incompatible</b></td>
                </tr>
                <c:forEach var="option" items="${optionList}">
                    <tr>
                        <td>
                            ${option.name}
                            <!-- удаление опции -->
                            <a href="deleteOption?id=${option.id}" style="color:red;">X</a>
                        </td>
                        <td>${option.price}</td>
                        <td>${option.onCost}</td>
                        <!-- необходимые опции -->
                        <td>
                            <c:forEach var="reqOption" items="${option.necessaryOptionList}">
                                ${reqOption.name}
                                <!-- удаление -->
                                <a href="deleteReqOption?optionId=${option.id}&reqOptionId=${reqOption.id}" style="color:red;">x</a>
                                <br/>
                            </c:forEach>
                        </td>
                        <!-- несовместимые опции -->
                        <td>
                            <c:forEach var="incOption" items="${option.incompatibleOptionList}">
                                ${incOption.name}
                                <!-- удаление -->
                                <a href="deleteIncOption?optionId1=${option.id}&optionId2=${incOption.id}" style="color:red;">x</a>
                                <br/>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <!-- управление отношениями опций -->
            <form action="manageOptionRelations" method="GET">

                <!-- выпадающие списки -->
                choose an option <br>
                <select name="optionId1" required>
                    <option value="0"> </option>
                    <c:forEach var="option" items="${optionList}">
                        <option value="${option.id}">${option.name}</option>
                    </c:forEach>
                </select>
                <br>
                <br>
                choose another option <br>
                <select name="optionId2" required>
                    <option value="0"> </option>
                    <c:forEach var="option" items="${optionList}">
                        <option value="${option.id}">${option.name}</option>
                    </c:forEach>
                </select>

                <!-- радиокнопки -->
                <p><b>action:</b><br>
                    <input type="radio" name="action" value="required">set as 1st requires 2nd option<br>
                    <input type="radio" name="action" value="incompatible">set as incompatible options<br>
                </p>

                <!-- кнопка -->
                <input type="submit" value="Apply">
                <br>
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
