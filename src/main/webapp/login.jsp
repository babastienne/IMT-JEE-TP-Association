
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
    <div class="container-fluid align-items-center form-login">
        <div class="text-center">
            <h2> Authentification </h2>
            <br/>
        </div>
        <form method="POST" action=>
            <label for="inputEmail" class="sr-only form">Adresse e-mail</label>
            <input class="form-control" type ="email" name="email" value="" id="inputEmail" placeholder="Adresse e-mail" required autofocus/>
            <br/>
            <label for="inputPassword" class="sr-only">Mot de passe</label>
            <input type="password" name="password" class="form-control" id= "inputPassword" value="" placeholder="Mot de passe" required/>
            <br/>
            <div>
                <label>
                    <input type="checkbox" value="remember"> Se souvenir de moi
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
        </form>

        <div>
        	<br/>
            <p>Si vous ne possédez pas d'identifiants pour vous connecter sur notre site internet, cliquez ici pour <a href="register">créer un compte</a>.</p>
        </div>
    </div>

<script src="assets/js/jquery.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
