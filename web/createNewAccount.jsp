<%-- 
    Document   : createNewAccount
    Created on : Jun 29, 2021, 10:49:13 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="create">
            <c:set var="errors" value="${requestScope.CREATE_ACCOUNT}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 20 characters) </br>
            <c:if test="${not empty errors.usernameLengthError}" >
                <font color="red"> 
                ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}" >
                <font color="red"> 
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 30 characters) </br>
            <c:if test="${not empty errors.passwordLengthError}" >
                <font color="red"> 
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
                Confirm* <input type="password" name="txtConfirm" value="" /> </br>
            <c:if test="${not empty errors.confirmNoMatched}" >
                <font color="red"> 
                ${errors.confirmNoMatched}
                </font><br/>
            </c:if>
            Full Name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (2 - 50 characters) </br>
            <c:if test="${not empty errors.fullNameLengthError}" >
                <font color="red"> 
                ${errors.fullNameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create new Account" name="btnAction" />
            <input type="reset" value="reset" name="reset" />
            <a href="login.html">Back</a>
        </form>
    </body>
</html>
