
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Site</title>
</head>
<body>
    <center>
        <h1>Clients Management</h1>
        <h2>
            <a href="new">Add New Client</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Clients</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${client != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${client == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${client != null}">
                        Edit Client
                    </c:if>
                    <c:if test="${client == null}">
                        Add New Client
                    </c:if>
                </h2>
            </caption>
                <c:if test="${client != null}">
                    <input type="hidden" name="id" value="<c:out value='${client.id}' />" />
                </c:if>           
            <tr>
                <th>AgentID: </th>
                <td>
                    <input type="number" name="agentId" size="30"
                            value="<c:out value='${client.agentId}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>FirstName: </th>
                <td>
                    <input type="text" name="firstName" size="30"
                            value="<c:out value='${client.firstName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>LastName: </th>
                <td>
                    <input type="text" name="lastName" size="30"
                            value="<c:out value='${client.lastName}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>StreetNumber: </th>
                <td>
                    <input type="number" name="streetNumber" size="30"
                            value="<c:out value='${client.streetNumber}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>StreetName: </th>
                <td>
                    <input type="text" name="streetName" size="30"
                            value="<c:out value='${client.streetName}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>City: </th>
                <td>
                    <input type="text" name="city" size="30"
                            value="<c:out value='${client.city}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>Province: </th>
                <td>
                    <input type="text" name="province" size="30"
                            value="<c:out value='${client.province}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>PostalCode: </th>
                <td>
                    <input type="text" name="postalCode" size="30"
                            value="<c:out value='${client.postalCode}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>TelOffice: </th>
                <td>
                    <input type="text" name="telOffice" size="30"
                            value="<c:out value='${client.telOffice}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>TelCell: </th>
                <td>
                    <input type="text" name="telCell" size="30"
                            value="<c:out value='${client.telCell}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="30"
                            value="<c:out value='${client.email}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>Company: </th>
                <td>
                    <input type="text" name="company" size="30"
                            value="<c:out value='${client.company}' />"
                    />
                </td>
            </tr>
              <tr>
                <th>CompanyType: </th>
                <td>
                    <input type="text" name="companyType" size="30"
                            value="<c:out value='${client.companyType}' />"
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