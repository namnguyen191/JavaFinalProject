
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Orders Management</title>
        <link rel="stylesheet" href="Style.css">
    </head>
    <body>
        <center>
        <h1>Orders</h1>
        <h2><a href="OrderControllerServlet?action=new">Add New Order</a></h2>
        </center>
    <div align="center">
        <table>
            <caption><h2>List of Orders</h2></caption>
            <tr>
                <th>Id</th>
                <th>Agent Id</th>
                <th>Client Id</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="order" items="${listOrders}">
                <tr>
                    <td><c:out value="${order.id}" /></td>
                    <td><c:out value="${order.agentId}" /></td>
                    <td><c:out value="${order.clientid}" /></td>
                    <td>
                        <a href="OrderControllerServlet?action=view&id=<c:out value='${order.id}' />">View and Edit Details</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="OrderControllerServlet?action=delete&id=<c:out value='${order.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</html>
