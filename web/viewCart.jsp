<%@page import="antdt.product.ProductDAO"%>
<%@page import="antdt.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="antdt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Market</title>
    </head>
    <body>
        <h1>Your cart include: </h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="items" value="${cart.items}"/>
        <c:if test="${not empty items}">


            <%--<%
                //1. Customer goes his/her cart place
                if (session != null) {
                    //2. Customer takes his/her cart 
            %>
            <h1>has session</h1>
            <%
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer takes items
            %>
            <h1>has cart</h1>
            <%
                Map<String, ProductDTO> items = cart.getItems();
                if (items != null) {
                    //4. display items 
            %>--%>

            <form action="CartRemoveOut">

                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--<%
                            int count = 0;
                            for (String key : items.keySet()) {
                        %>--%>
                        <c:forEach var="item" items="${items}" varStatus="counter">

                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    <%--<% out.print(items.get(key).getName()); %>--%>
                                    ${item.value.name}
                                    <input type="hidden" name="name" value="${item.value.name}" />
                                </td>
                                <td>
                                    <%--<% out.print(items.get(key).getQuantity()); %>--%>
                                    ${item.value.quantity}
                                    <input type="hidden" name="quantity" value="${item.value.quantity}" />
                                </td>
                                <td>
                                    <%--<% 
                                        ProductDAO dao = new ProductDAO();
                                        ProductDTO dto = dao.loadProductDataByid(key);
                                        double price = dto.getPrice()*items.get(key).getQuantity();
                                        out.print(price);
                                    %>--%>
                                    ${item.value.price * item.value.quantity}
                                    <input type="hidden" name="price" value="${item.value.price * item.value.quantity}" />
                                </td>   
                                <td>
                                    <input type="checkbox" name="chkItem" value="${item.key}" />
                                </td>
                            </tr>

                            <%--<%
                                }
                            %>--%>
                            </c:forEach>
                            <tr>
                                <td colspan = "3">
                                    <a href="onlineShopping.jsp">Add More Items to your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btnAction" />
                                </td>
                                <td>
                                    <input type="submit" value="Out" name="btnAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <%--<%
                        return;
                    } else {
                    %>
                    <h1>has no items</h1>
                    <%
                    }
                } else {
                %>
                <h1>has no cart</h1>
                <%} // end cart existed 
                    }// end sesstion 
                %>--%>
        </c:if>

    </body>
</html>
