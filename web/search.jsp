<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="antdt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>

        <font color="red">
        Welcome, ${sessionScope.DTO.lastname}
        </font>
        <%--<%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
        %> 
        <font color = "red">
        Welcome, <%=  cookies[cookies.length - 1].getName()%>
        </font>
        <%
            }
        %>
        <h1>Search Result</h1>
        --%>
        <form action="searchPage">
            Search Value <input type ="text" name ="txtSearchValue" 
                                value ="${param.txtSearchValue}" /><br/>
            <input type="submit" value="search" name="btnAction" />
            <input type="submit" value="Log out" name="btnAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:set var="errors" value="${sessionScope.UPDATE_ACCOUNT}"/>
        <c:set var="deleteErrors" value="${sessionScope.DELETE_ACCOUNT}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>

            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if> />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="delete">
                                        <%--<c:param name="btnAction" value="delete"/>--%>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="keyword" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a> 
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btnAction" />
                                    <input type="hidden" name="keyword" value="${searchValue}" />
                                </td>
                            </tr>

                        </form>

                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
    <c:if test="${not empty errors.passwordLengthError}">
        <font color = "red">
        ${errors.passwordLengthError}
        </font>
    </c:if>
    <c:if test="${not empty deleteErrors.deleteMyselfErrors}">
        <font color="red">
        ${deleteErrors.deleteMyselfErrors}
        </font></br>
    </c:if>
    <c:if test="${not empty deleteErrors.deleteAdminRoleErrors}">
        <font color="red">
        ${deleteErrors.deleteAdminRoleErrors}
        </font></br>
    </c:if>
    <%--
    <%
        Cookie[] cookies = request.getCookies();
        RegistrationDTO welcomeName = (RegistrationDTO) session.getAttribute("WelcomeName");
        if (cookies != null) {
    %>
    <font color="red"> 
    Welcome, 
    </font>
    <%
        }//end cookie are existed    Welcome, <%= welcomeName.getLastname() %>
    %>
    <h1>Search Result</h1>
    <form action="DispatchServlet">
        Search Value <input type="text" name="txtSearchValue"
                            value="<%= request.getParameter("txtSearchValue")%>" />
        <input type="submit" value="search" name="btnAction" />
    </form>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btnAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&keyword=" + searchValue;
                %>
            <form action="DispatchServlet">
                <tr>
                    <td>
                        <%= ++count%> 
                        .</th>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUserName"
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtUserName"
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"

                               <%
                                   }
                               %>
                    </td>
                    <td>
                        <a href=" <%= urlRewriting%>">Delete</a> 
                    </td>
                    <td>                                           
                        <input type="submit" value="Update" name="btnAction" />
                        <input type="hidden" name="keyword" value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>    


            <%
                }//traverse each dto in result
            %>
        </tbody>
    </table>

    <%
    } else { //search is no record
    %>
    <h2>
        No record is matched...!
    </h2>
    <%
            }
        }//searchValue is exitsted bacause accessing direct this page
%>
    --%>
</body>
</html>
