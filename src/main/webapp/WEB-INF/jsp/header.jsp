<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="current" value="${param.ddlLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="locale" scope="session"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/pageHead.css">
    <link href="https://unpkg.com/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
</head>
<body>
<div class="page-header">
    <form  action="#" method="post">
        <select name="ddlLanguage">
            <option value="en_EN">English</option>
            <option value="ru_RU">Russian</option>
            <option value="by_BY">Belarusian</option>
        </select>
        <button class="btn btn-dark btn-sm" type="submit"> <fmt:message key="applyLocale"/></button>
    </form>
    <button class="btn btn-light btn-sm toHome" type="button" onclick="window.location.href='home?command=mainPage'"><fmt:message key="mainPage"/></button>
    <button class="btn btn-light btn-sm profile" type="button" onclick="window.location.href='user?command=toUserProfile'"><fmt:message key="profile"/></button>
    <button class="btn btn-light btn-sm log-in" type="button" onclick="window.location.href='home?command=toSignIn'"><fmt:message key="logIn"/></button>
    <button class="btn btn-light btn-sm sign-up" type="button" onclick="window.location.href='home?command=toSignUp'"><fmt:message key="signUp"/></button>
    <c:set var = "teacher" scope = "session" value = "${role}"/>
    <c:if test = "${role != null}">
        <button class="btn btn-light btn-sm log-out" type="button" onclick="window.location.href='user?command=logOut'"><fmt:message key="logOut"/></button>
    </c:if>
    <c:set var = "teacher" scope = "session" value = "${role}"/>
    <c:if test = "${role == 'ADMIN'}">
        <button class="btn btn-light btn-sm adminPanel" type="button" onclick="window.location.href='admin?command=adminPanel'"><fmt:message key="admin"/></button>
    </c:if>
</div>
</body>
</html>
