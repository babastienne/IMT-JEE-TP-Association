<%--
  User: babastienne
  Date: 02/11/2017
  Time: 16:21
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
    <div class="container-fluid align-items-center">
        <div class="text-center">
            <h2> Inscrivez-vous à la vegassociation </h2>
            <p>Les champs marqué du caractère (*) doivent obligatoirement être remplis pour pouvoir finaliser l'inscription.</p>
            <br/>
        </div>
        <form class="container-fluid align-items-center" style="max-width: 350px; min-width: 350px; margin-bottom: 5%;" method="POST" action=>
            <div class="form-group">
                <label for="inputEmail">Adresse e-mail (*)</label>
                <input class="form-control" type ="email" name="email" value="" id="inputEmail" placeholder="j.dupont@fai.com" required autofocus/>
            </div>

            <div class="form-group">
                <label for="inputPassword">Mot de passe (*)</label>
                <input type="password" name="password" class="form-control" id= "inputPassword" value="" placeholder="****************" required/>
                <label for="inputConfirmPassword">Confirmer votre mot de passe (*)</label>
                <input type="password" name="password" class="form-control" id= "inputConfirmPassword" value="" placeholder="****************" required/>
            </div>

            <div class="form-group">
                <label for="inputEmail">Prénom (*)</label>
                <input class="form-control" type ="text" name="name" value="" id="name" placeholder="Jean-Marc" required/>
                <label for="inputEmail">Nom de famille (*)</label>
                <input class="form-control" type ="text" name="family-name" value="" id="familyName" placeholder="Dupont" required	/>
            </div>

            <div class="form-group">
                <label for="inputEmail">Adresse</label>
                <input class="form-control" type ="text" name="adress" value="" id="adress" placeholder="124 impasse de l'église"/>
                <label for="inputEmail">Code Postal</label>
                <input class="form-control" type ="number" name="zip" value="" id="zip" placeholder="92185"/>
                <label for="inputEmail">Ville</label>
                <input class="form-control" type ="text" name="city" value="" id="city" placeholder="Clichy"/>
            </div>

            <div class="form-group">
                <select class="form-control bfh-countries" data-country="US"></select>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>

        </form>
    </div>

    <script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
