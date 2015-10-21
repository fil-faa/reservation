<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Recherche</h1>

<form method="get" action="">
    <div class="form-group">
        <label for="lastname">Nom</label>
        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Dupont" value="${param.userLastName}">
    </div>
    <div class="form-group">
        <label for="firstname">Prénom</label>
        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Laurent" value="${param.userFirstName}">
    </div>
    <div class="form-group">
        <label for="mail">Mail</label>
        <input type="email" class="form-control" id="mail" name="mail" placeholder="example@domaine.com" value="${param.mail}">
    </div>
    <div class="form-group">
        <label for="phone">Téléphone</label>
        <input type="tel" class="form-control" id="phone" name="phone" placeholder="0123456789" value="${param.phone}">
    </div>
    <button type="submit" class="btn btn-default">Valider</button>
</form>

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

