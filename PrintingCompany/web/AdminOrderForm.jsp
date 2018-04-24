
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Management Application</title>
        <script type="text/javascript">

function val(){
var chks = document.getElementsByName('locations');
var hasChecked = false;
for(var i=0; i<chks.length; i++){
if(chks[i].checked){
hasChecked = true;
break;
}
}
if(hasChecked == false){
alert("Please select at least one location");
return false;
}
return true;
}

</script>
</head>
<body>
    <center>
        <h1>Orders Management</h1>
        <h2>
            <a href="OrderControllerServlet?action=list">List All Orders</a>             
        </h2>
    </center>
    <div align="center">
        <c:if test="${order != null}">
            <form action="OrderControllerServlet?action=update&id=<c:out value='${order.id}' />" method="post" enctype="multipart/form-data">
        </c:if>
        <c:if test="${order == null}">
            <form action="OrderControllerServlet?action=insert" method="post" enctype="multipart/form-data">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${order != null}">
                        Order Details
                    </c:if>
                    <c:if test="${order == null}">
                        Add New Order
                    </c:if>
                </h2>
            </caption>    
            <tr>
                <th>Agent Id </th>
                <td>
                    <select name="agentId">
                        <c:forEach var = "agentId" items="${listAllAgentIds}">
                            <option name="agentId" value="<c:out value="${agentId}" />" <c:if test="${order.agentId==agentId}">selected</c:if>><c:out value="${agentId}" /></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Client Id </th>
                <td>
                    <select name="clientId">
                        <c:forEach var = "clientId" items="${listAllClientIds}">
                            <option name="clientId" value="<c:out value="${clientId}" />" <c:if test="${order.clientid==clientId}">selected</c:if>><c:out value="${clientId}" /></option>
                        </c:forEach>
                    </select>
<!--                    <input type="number" name="clientId" size="45"
                            value="<c:out value='${order.clientid}' />"
                    />-->
                </td>
            </tr>
            <tr>
                <th>Flyer Quantity </th>
                <td>
                    <input type="number" name="flyerQty" size="45"
                            value="<c:out value='${order.flyerQty}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Personal Copy </th>
                <td>
                    <input type="number" name="personalCopy" size="45"
                            value="<c:out value='${order.personalCopy}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Flyer Art</th>
                <td>
                    <img src="${pageContext.request.contextPath}/images/<c:out value='${order.id}' />" style='max-height: 400px; max-width: 400px'>
                    <input type="file" accept="image/*" name="uploadFile" value="<c:out value='${order.flyerImg}'/>">
                </td>
            </tr>
            <tr>
                <th>Flyer Art Approved </th>
                <td>
                    <select name='isFlyerArtApproved'>
                        <option name="isFlyerArtApproved" value="1"  <c:if test="${order.isFlyerArtApproved=='1'}">selected</c:if>>Yes</option>
                         <option name="isFlyerArtApproved" value="0" <c:if test="${order.isFlyerArtApproved=='0'}">selected</c:if>>No</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Payment Received </th>
                <td>
                    <select name='isPaymentReceived'>
                        <option name="isPaymentReceived" value="1"  <c:if test="${order.isPaymentReceived=='1'}">selected</c:if>>Yes</option>
                         <option name="isPaymentReceived" value="0" <c:if test="${order.isPaymentReceived=='0'}">selected</c:if>>No</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Flyer Layout </th>
                <td>
                    <select name='flyerLayout'>
                        <option name="flyerLayout" value="Portrait"  <c:if test="${order.flyerLayout=='Portrait'}">selected</c:if>>Portrait</option>
                        <option name="flyerLayout" value="Landscape"  <c:if test="${order.flyerLayout=='Landscape'}">selected</c:if>>Landscape</option>
                        <option name="flyerLayout" value="Both"  <c:if test="${order.flyerLayout=='Both'}">selected</c:if>>Both</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Payment Info </th>
                <td>
                    <input type="text" name="paymentInfo" size="45"
                            value="<c:out value='${order.paymentInfo}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Comments </th>
                <td>
                    <input type="text" name="comments" size="45"
                            value="<c:out value='${order.comments}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Invoice No </th>
                <td>
                    <input type="text" name="invoiceNo" size="45"
                            value="<c:out value='${order.invoiceNo}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Distributed Locations</th>
                <td>
                    <c:forEach var = "location" items="${listAllLocations}">
                        
                        <input type='checkbox' name='locations' value="<c:out value="${location}" />"
                               <c:forEach var = "orderLocation" items="${order.locations}">
                                   <c:if test="${orderLocation==location}">checked</c:if>
                                </c:forEach>
                               >
                        <c:out value="${location}" /><br>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save Details"  onclick="return val();"/>
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>