<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Modifier l'utilisateur</h1>
<form method="post" action="modify">
    <input type="hidden" name="id" value="${user.id}">
    <div class="form-group">
        <label for="lastname">Nom</label>
        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Dupont" value="${user.lastName}">
    </div>
    <div class="form-group">
        <label for="firstname">Prénom</label>
        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Laurent" value="${user.firstName}">
    </div>
    <div class="form-group">
        <label for="mail">Mail</label>
        <input type="email" class="form-control" id="mail" name="mail" placeholder="example@domaine.com" value="${user.mail}">
    </div>
    <div class="form-group">
        <label for="phone">Téléphone</label>
        <input type="tel" class="form-control" id="phone" name="phone" placeholder="0123456789" value="${user.phone}">
    </div>
    <div class="form-group">
        <label for="password">Mot de passe</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" value="${user.password}">
    </div>
    <button type="submit" class="btn btn-default">Modifier l'utilisateur</button>
</form>
<c:if test="${isAdmin}">
    <form method="post" action="delete">
        <button type="submit" class="btn btn-warning">Supprimer l'utilisateur</button>
    </form>
</c:if>