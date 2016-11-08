<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>

<body>

<h1>Login page</h1>

<c:if test="${not empty param.login_error}">
    <span style="color: red; ">
        Sorry, your login attempt was not successful, please try again.<br/><br/>
        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
    </span>
</c:if>

<form name="form" action="" method="GET"> <!--  -->

    Username: <br>
    <input type="text" name="username"> <br>
    <br>
    Password: <br>
    <input type="password" name="password"> <br>
    <br>
    <input name="reset" type="reset" value="Clear">
    <input name="submit" type="submit" value="Log in">

    <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"
           value="<c:out value="${_csrf.token}"/>"/>
</form>

</body>

</html>