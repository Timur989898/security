<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Signin</title>
</head>
<body>
    <%--@elvariable id="user" type="ru.kpfu.itis.model.User"--%>
    <form:form method="post" modelAttribute="user">
        <input type="hidden" name="csrf_token" value="qwe12123wqe231eqw123">
        <form:label path="username">Name</form:label>
        <form:input path="username"/>
        <form:errors path="username"/><br>
        <form:label path="password">Password</form:label>
        <form:password path="password"/>
        <form:errors path="password"/><br>
        <form:label path="confirmPassword">Confirm Password</form:label>
        <form:password path="confirmPassword"/>
        <form:errors path="confirmPassword"/><br>
        <input type="submit" value="Signin" />
    </form:form>
</body>
</html>
