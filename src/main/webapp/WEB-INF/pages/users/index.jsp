<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="width-50">


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
    <h1>Liste des utilisateurs</h1>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Nom</th>
        <th>Pr&eacute;nom</th>
        <th>Mail</th>
        <th>T&eacute;l&eacute;phone</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.nom}</td>
            <td>${user.prenom}</td>
            <td>${user.mail}</td>
            <td>${user.telephone}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

