<%-- 
    Document   : AgentIndex
    Created on : Apr 13, 2018, 3:38:14 PM
    Author     : jpsza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agent List</title>
    </head>
    <body>
        <center>
            <h2>
            <a href="add">Add New Agent</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Agents</a>
            </h2>
            <h1>Agent List</h1>
                <table cellpadding="5" border="1">
                    <thead>
                        <th>id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                    </thead>
                    <tbody>
                        <c:forEach var="agentObj" items="${agentList}">
                            <tr>
                                <td><c:out value="${agentObj.id}"/></td>
                                <td><c:out value="${agentObj.getfName()}"/></td>
                                <td><c:out value="${agentObj.getlName()}"/></td>
                                <td><c:out value="${agentObj.getPhone()}"/></td>
                                <td><c:out value="${agentObj.getEmail()}"/></td>
                                <td>
                                    <a href="edit?id=<c:out value='${agentObj.getId()}'/>">Edit</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="delete?id=<c:out value='${agentObj.getId()}'/>">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
        </center>
    </body>
</html>
