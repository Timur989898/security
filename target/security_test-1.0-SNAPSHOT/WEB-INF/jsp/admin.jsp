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
    <title>Admin</title>
</head>
<body>
    <c:if test="${pageContext.request.userPrincipal.name} != null">

        <h2>${pageContext.requset.userPrincipal.name}</h2>

    </c:if>
    Админ
</body>
</html>
