
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Printing Company</title>
        <link rel="stylesheet" href="Style.css">
</head>
<body>
    <center>
        <h1>Distribution Location and Capacity</h1>
        <h2>
            <a href="new">Add New Location</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Location</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Locations and Capacity</h2></caption>
            <tr>
                <th>ID</th>
                <th>Location</th>
                <th>Capacity</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="location" items="${listLocation}">
                <tr>
                    <td><c:out value="${location.id}" /></td>
                    <td><c:out value="${location.locationName}" /></td>
                    <td><c:out value="${location.distributionCapacity}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${location.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${location.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>
