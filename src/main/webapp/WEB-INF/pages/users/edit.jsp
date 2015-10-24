<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Editer un utilisateur</h1>
<form method="POST">
  <div class="form-group">
    <label for="lastName">Nom</label>
    <input type="text" class="form-control" id="lastname" name="lastName" placeholder="Dupont" value="${user.lastName}">
  </div>
  <div class="form-group">
    <label for="firstName">Pr�nom</label>
    <input type="text" class="form-control" id="firstname" name="firstName" placeholder="Laurent" value="${user.firstName}">
  </div>
  <div class="form-group">
    <label for="mail">Mail</label>
    <input type="email" class="form-control" id="mail" name="mail" placeholder="example@domaine.com" value="${user.mail}">
  </div>
  <div class="form-group">
    <label for="phone">T�l�phone</label>
    <input type="tel" class="form-control" id="phone" name="phone" placeholder="0123456789" value="${user.telephone}">
  </div>
  <div class="form-group">
    <label for="password">Mot de passe (laisser vide pour ne pas modifier)</label>
    <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
  </div>
  <button type="submit" class="btn btn-default">Valider</button>
</form>