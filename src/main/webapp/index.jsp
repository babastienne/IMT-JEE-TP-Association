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
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <title>Vegassociation</title>
</head>
<body>
    <c:if test="${not empty authError}">
        <script>
            window.addEventListener("load",function(){
                alert("${authError}");
            })
        </script>
    </c:if>

    <%@include file="jsp/partials/login.jspf" %>


<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
