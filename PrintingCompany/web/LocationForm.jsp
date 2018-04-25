
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
            <a href="list">List All Locations</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${location != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${location == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${location != null}">
                        Edit Location
                    </c:if>
                    <c:if test="${location == null}">
                        Add New Location
                    </c:if>
                </h2>
            </caption>
                <c:if test="${location != null}">
                    <input type="hidden" name="id" value="<c:out value='${location.id}' />" />
                </c:if>           
            <tr>
                <th>Location Name: </th>
                <td>
                    <input type="text" name="locationName" size="45"
                            value="<c:out value='${location.locationName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Distribution Capacity: </th>
                <td>
                    <input type="text" name="distributionCapacity" size="45"
                            value="<c:out value='${location.distributionCapacity}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>