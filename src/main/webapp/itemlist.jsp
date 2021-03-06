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


<div class="container">
    <table class="table">
        <thead>
            <th>Code Article</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Stock</th>
        </thead>
    <c:forEach items="${listItems}" var="item">
        <tr>
            <form action="/itemlist" method="post">

                <td><c:out value ="${item.getId()}"/></td>
                <td><c:out value = "${item.getName()}"/></td>
                <td><c:out value = "${item.getPrice()}"/></td>
                <td>
                    <div class="row">
                        <c:if test="${item.getStock() != 0}">
                            <input class="form-control form-control-sm col-lg-4" name="quantity" type="number" min="0" max="${item.getStock()}" required value="1"> /
                        </c:if>
                        <c:out value = "${item.getStock()}"/>
                    </div>
                </td>
                <input type="hidden" name="code" value ="${item.getId()}"/>

                <td><input type="submit" class="btn btn-outline-primary ml-sm-2 <c:if test="${item.getStock() == 0}">btn-outline-secondary disabled</c:if>" value="Commander"/></td>
            </form>
        </tr>
    </c:forEach>
    </table>
</div>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>
</html>
