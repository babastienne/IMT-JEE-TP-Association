<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qeude
  Date: 30/10/2017
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link href="assets/css/style.css" rel="stylesheet">
    <title>Vegassociation</title>

</head>
<body>

<%@include file="jsp/partials/navbar.jspf"%>


<!-- name : orderLine.getItem().getName() ; id: orderLine.getItem().getId(); price : orderLine.getItem().getPrice()

orderLine.getQuantityItem() -->


<div class="container">
    <c:if test="${not empty orderLines}">
        <form method="post" action="/command">
            <input type="submit" class="btn btn-outline-danger ml-sm-2" value="Annuler la commande"/>
        </form>
    </c:if>
    <table class="table">
        <thead>
            <th>Code Article</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Quantité</th>
        </thead>
    <c:forEach items="${orderLines}" var="orderLine">
        <tr>
            <td><c:out value ="${orderLine.getItem().getId()}"/></td>
            <td><c:out value = "${orderLine.getItem().getName()}"/></td>
            <td><c:out value = "${orderLine.getItem().getPrice()}"/></td>
            <td><c:out value = "${orderLine.getQuantityItem()}"/></td>
        </tr>
    </c:forEach>
    </table>
    <c:if test="${not empty orderLines}">
        <form method="post" action="/command">
            <input type="submit" class="btn btn-outline-danger ml-sm-2" value="Annuler la commande"/>
        </form>
    </c:if>
</div>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>
</html>
