<%-- 
    Document   : onlineShopping
    Created on : Jun 26, 2021, 4:20:29 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Online Market</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Online Shopping</div>
        <c:set var="result" value="${sessionScope.PRODUCT_DATA}"/>
        <form action="cart">
            Choose items <select name="cboItem">
                <c:forEach var="dto" items="${result}">
                    <option value="${dto.id}">${dto.name}</option>
                </c:forEach>
            </select></br>
            <input type="submit" value="Add Item to Your Cart" name="btnAction" />
            <input type="submit" value="View Your Cart" name="btnAction" />
        </form>
    </body>
</html>
