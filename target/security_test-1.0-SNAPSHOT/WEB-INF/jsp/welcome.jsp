<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title>Welcome</title>
</head>
<body>
    <c:if test="${pageContext.request.userPrincipal.name} != null">

        <h2>${pageContext.request.userPrincipal.name}</h2>

    </c:if>

    ${pageContext.request.userPrincipal.name}
    <a href="${spring:mvcUrl("AC#logout").build()}">Выход</a>
</body>
</html>
