<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Объявляем заголовк, кодироку стили и т.д -->
    <meta contentType="text/html" charset="utf-8">
    <title>--This is a title--</title>
    <link href="resources/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Обертка  -->
    <header>

        <jsp:include page="Header.jsp" />

    </header>
    <nav>

        <jsp:include page="NavBar.jsp" />

    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">


            <form action="" method="GET">
                choose an option to manage<br>
                <select name="optionId1" required>
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

                <br><br>choose another option to manage<br>
                <select name="optionId2" required>
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

                <p><b>action:</b><br>
                    <input type="radio" name="action" value="required">set as 1st requires 2nd option<br>
                    <input type="radio" name="action" value="incompatible">set as incompatible options<br>
                    <input type="radio" name="action" value="remove">remove any relations of selected options<br>
                </p>

                <input type="submit" value="Apply">
                <br><br>
                ${message} ${error}
                <br><br>
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
