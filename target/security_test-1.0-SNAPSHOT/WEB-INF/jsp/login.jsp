<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ytka
  Date: 23.04.2019
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <%--@elvariable id="loginForm" type="ru.kpfu.itis.model.forms.LoginForm"--%>
    <form:form method="post" modelAttribute="loginForm">
        <form:label path="username">Name</form:label>
        <form:input path="username"/>
        <form:errors path="username"/><br>
        <form:label path="password">Password</form:label>
        <form:password path="password"/>
        <form:errors path="password"/><br>
        <form:label path="remember">Remember me?</form:label>
        <form:checkbox path="remember"/>
        <form:errors path="remember"/><br>
        <input type="submit" value="Login" />
    </form:form>
</body>
</html>
