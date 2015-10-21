<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="width-50">

    <h1>Ajouter un utilisateur</h1>
    <form>
        <div class="form-group">
            <label for="lastname">Nom</label>
            <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Dupont">
        </div>
        <div class="form-group">
            <label for="firstname">Prénom</label>
            <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Laurent">
        </div>
        <div class="form-group">
            <label for="mail">Mail</label>
            <input type="email" class="form-control" id="mail" name="mail" placeholder="example@domaine.com">
        </div>
        <div class="form-group">
            <label for="phone">Téléphone</label>
            <input type="tel" class="form-control" id="phone" name="phone" placeholder="0123456789">
        </div>
        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
        </div>
        <button type="submit" class="btn btn-default">Valider</button>
    </form>
</div>
<div class="width-50">
    <h1>Recherche</h1>
    <form method="get" action="">
    <div class="form-group">
        <label for="search_lastname">Nom</label>
        <input type="text" class="form-control" id="search_lastname" name="lastname" placeholder="Dupont" value="${param.userLastName}">
    </div>
    <div class="form-group">
        <label for="search_firstname">Prénom</label>
        <input type="text" class="form-control" id="search_firstname" name="firstname" placeholder="Laurent" value="${param.userFirstName}">
    </div>
    <div class="form-group">
        <label for="search_mail">Mail</label>
        <input type="email" class="form-control" id="search_mail" name="mail" placeholder="example@domaine.com" value="${param.mail}">
    </div>
    <div class="form-group">
        <label for="search_phone">Téléphone</label>
        <input type="tel" class="form-control" id="search_phone" name="phone" placeholder="0123456789" value="${param.phone}">
    </div>
    <button type="submit" class="btn btn-default">Valider</button>
</form>
</div>
<div class="page-header">
    <h1>Liste des ressources</h1>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Nom</th>
        <th>Description</th>
        <th>Type</th>
        <th>Localisation</th>
        <th>Responsable</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ressource" items="${ressources}">
        <a href="${appPath}/reservations/"><tr>
            <td>${ressource.nom}</td>
            <td>${ressource.description}</td>
            <td>${ressource.type.nom}</td>
            <td>${ressource.resposable.nom} ${ressource.resposable.prenom}</td>
        </tr>
        </a>
    </c:forEach>

    </tbody>
</table>

