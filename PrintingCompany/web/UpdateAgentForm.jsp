<%-- 
    Document   : UpdateAgentForm
    Created on : Apr 19, 2018, 8:10:00 PM
    Author     : jpsza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Agent</title>
    </head>
    <body>
        <center>
            <h1>Update Agent</h1>
            <form action="saveUpdate" method="post" name="updateForm">
                <table cellpadding="5" border="1">
                    <tr>
                        <th>Agent First Name</th>
                        <td>
                            <input type="text" name="fname" id="fname" value="${agent.getfName()}">
                        </td>
                    </tr>
                    <tr>
                        <th>Agent Last Name</th>
                        <td>
                            <input type="text" name="lname" id="lname" value="${agent.getlName()}">
                        </td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><input type="email" name="email" id="email" value="${agent.email}"></td>
                    </tr>
                    <tr>
                        <th>Phone</th>
                        <td><input type="phone" name="phone" id="phone" value="${agent.phone}"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="Save"></td>
                    </tr>
                </table>
            </form>
        </center>
    </body>
</html>
