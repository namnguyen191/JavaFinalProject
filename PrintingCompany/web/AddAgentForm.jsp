<%-- 
    Document   : addAgentForm
    Created on : Apr 13, 2018, 3:48:05 PM
    Author     : jpsza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Agent</title>
    </head>
    <body>
        <center>
            <h1>Add Agent</h1>
            <form action="saveAdd" method="post" name="insertForm">
                <table cellpadding="5" border="1">
                    <tr>
                        <th>Agent First Name</th>
                        <td>
                            <input type="text" name="fname" id="fname">
                        </td>
                    </tr>
                    <tr>
                        <th>Agent Last Name</th>
                        <td>
                            <input type="text" name="lname" id="lname">
                        </td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><input type="email" name="email" id="email"></td>
                    </tr>
                    <tr>
                        <th>Phone</th>
                        <td><input type="phone" name="phone" id="phone"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="Save"></td>
                    </tr>
                </table>
            </form>
        </center>
    </body>
</html>
