<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="current" value="${param.ddlLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="locale" scope="session"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <link rel="stylesheet" href="css/fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <form action="#" method="post">
        <select name="ddlLanguage">
            <option value="en_EN">English</option>
            <option value="ru_RU">Russian</option>
            <option value="by_BY">Belarusian</option>
        </select>
        <button class="btn btn-dark btn-sm" type="submit"><fmt:message key="applyLocale"/></button>
    </form>
${requestScope.toSignIn}
<div class="main">
    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="images/signin-image.jpg" alt="sing up image"></figure>
                    <a href="home?command=toSignUp" class="signup-image-link"><fmt:message key="createAcc"/></a>
                    <a href="home?command=mainPage" class="signup-image-link"><fmt:message key="mainPage"/></a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title"><fmt:message key="signIn"/></h2>
                    <form action="home?command=signIn" method="POST" class="register-form" id="login-form">
                        <div class="form-group">
                            <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="login" id="login" placeholder="<fmt:message key="yourLogin"/>"
                                   required pattern="^[a-zA-Z0-9._-]{3,15}$" title="<fmt:message key="loginValid"/>"
                                   oninvalid="this.setCustomValidity('<fmt:message key="loginValid"/>')"
                                   oninput="this.setCustomValidity('')"/>
                        </div>
                        <div class="form-group">
                            <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="your_pass" placeholder="<fmt:message key="password"/>"
                                   required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=\S+$).{8,}"
                                   title="<fmt:message key="passwordValid"/>"
                                   oninvalid="this.setCustomValidity('<fmt:message key="passwordValid"/>')"
                                   oninput="this.setCustomValidity('')"/>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="<fmt:message key="signIn"/>"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>