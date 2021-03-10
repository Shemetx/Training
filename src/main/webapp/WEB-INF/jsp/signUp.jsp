<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <form  action="#" method="post">
        <select name="ddlLanguage">
            <option value="en_EN">English</option>
            <option value="ru_RU">Russian</option>
            <option value="by_BY">Belarusian</option>
        </select>
        <button class="btn btn-dark btn-sm" type="submit"> <fmt:message key="applyLocale"/></button>
    </form>
${requestScope.toSignUp}
    <div class="main">
        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title"><fmt:message key="signUp"/></h2>
                        <form action="home?command=signUp" method="POST" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="firstName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="firstName" name="firstName" id="firstName" placeholder="<fmt:message key="yourFirstName"/>"
                                       required pattern="^(([A-Z]([A-z]){1,16}))$" title="<fmt:message key="firstNameValid"/>"
                                       oninvalid="this.setCustomValidity('<fmt:message key="firstNameValid"/>')"
                                       oninput="this.setCustomValidity('')"/>
                            </div>
                            <div class="form-group">
                                <label for="secondName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="secondName" name="secondName" id="secondName" placeholder="<fmt:message key="yourSecondName"/>"
                                       required pattern="^(([A-z]([A-z]){1,16}([\u0020-][A-z]([A-z]){1,16})?))$" title="<fmt:message key="secondNameValid"/>"
                                       oninvalid="this.setCustomValidity('<fmt:message key="secondNameValid"/>')"
                                       oninput="this.setCustomValidity('')"/>
                            </div>
                            <div class="form-group">
                                <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="login" id="login" placeholder="<fmt:message key="yourLogin"/>"
                                required pattern="^[a-zA-Z0-9._-]{3,15}$" title="<fmt:message key="loginValid"/>"
                                oninvalid="this.setCustomValidity('<fmt:message key="loginValid"/>')"
                                oninput="this.setCustomValidity('')"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="<fmt:message key="yourEmail"/>"
                                       required pattern="^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,6}$" title="<fmt:message key="emailValid"/>"
                                       oninvalid="this.setCustomValidity('<fmt:message key="emailValid"/>')"
                                       oninput="this.setCustomValidity('')"/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="pass" placeholder="<fmt:message key="password"/>"
                                       required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=\S+$).{8,}" title="<fmt:message key="passwordValid"/>"
                                       oninvalid="this.setCustomValidity('<fmt:message key="passwordValid"/>')"
                                       oninput="this.setCustomValidity('')"/>
                            </div>
                            <div class="form-group">
                                <label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="<fmt:message key="repeatPass"/>"
                                       required title="<fmt:message key="repPasswordValid"/>"
                                       oninvalid="this.setCustomValidity('<fmt:message key="repPasswordValid"/>')"
                                       oninput="this.setCustomValidity('')"/>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value=<fmt:message key="signUp"/> />
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="<c:url value='/images/signup-image.jpg'/>" alt="sing up image"></figure>
                        <a href="home?command=toSignIn" class="signup-image-link"><fmt:message key="alreadyMember"/></a>
                        <a href="home?command=mainPage" class="signup-image-link"><fmt:message key="mainPage"/></a>
                    </div>
                </div>
            </div>
        </section>

    </div>
</body>
</html>
<script type="text/javascript">
    var password = document.getElementById("pass");
    var confirmPassword = document.getElementById("re_pass");

    function validatePassword() {

        if (password.value != confirmPassword.value) {
            confirmPassword.setCustomValidity("Passwords dont't match!");
        } else {
            confirmPassword.setCustomValidity("");
        }
    }
    password.onchange = validatePassword;
    confirmPassword.onkeyup = validatePassword;
</script>