<%-- 
    Document   : login
    Created on : Jul 10, 2021, 1:13:24 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Login</div>
        <h1>Login Page</h1>
        <form action="loginPage" method="POST">
            <c:set var="errors" value="${requestScope.LOGIN_ACCOUNT}"/>
            Username <input type="text" name="txtUserName" value="${param.txtUserName}" /> </br>
            <c:if test="${not empty errors.usernameNoMatched}">
                <font color ="red">
                ${errors.usernameNoMatched}
                </font><br>
            </c:if>
            <c:if test="${not empty errors.usernameNotExisted}">
                <font color ="red">
                ${errors.usernameNotExisted}
                </font><br>
            </c:if>
            <c:if test="${not empty errors.usernameHasBeenRemoved}">
                <font color ="red">
                ${errors.usernameHasBeenRemoved}
                </font><br>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /> </br>
            <c:if test="${not empty errors.passwordNoMatched}">
                <font color ="red">
                ${errors.passwordNoMatched}
                </font><br>
            </c:if>
            <c:if test="${not empty errors.passwordWrong}">
                <font color ="red">
                ${errors.passwordWrong}
                </font><br>
            </c:if>
            <input type="submit" value="Login" name="btnAction" />
            <input type="reset" value="Reset" />
            <input type="submit" value="Shopping Now" name="btnAction" />
            <a href="signUp.html">sign up</a>
        </form>
    </body>
</html>
