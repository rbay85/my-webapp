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


            <form action="" method="GET">
                <p>choose an option to manage</p>
                <select name="option1" required>
                    <option value="0"> </option>
                    <option value="1">2 GB internet</option>
                    <option value="2">3 GB internet</option>
                    <option value="3">4 GB internet</option>
                    <option value="4">100 sms</option>
                    <option value="5">200 sms</option>
                    <option value="6">400 sms</option>
                    <option value="7">200 minute</option>
                    <option value="8">300 minute</option>
                    <option value="9">500 minute</option>
                    <option value="10">internet service</option>
                    <option value="11">sms service</option>
                </select>

                <p>choose another option to manage</p>
                <select name="option2" required>
                    <option value="0"> </option>
                    <option value="1">2 GB internet</option>
                    <option value="2">3 GB internet</option>
                    <option value="3">4 GB internet</option>
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
                    <input type="radio" name="action" value="required">set as 1st required 2nd option<br>
                    <input type="radio" name="action" value="incompatible">set as incompatible options<br>
                </p>

                <input type="submit" value="Apply">
            </form>
            ${message} ${error}


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
