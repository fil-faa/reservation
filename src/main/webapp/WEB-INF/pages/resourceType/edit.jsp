<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Editer un type de ressource</h1>

<form method="POST">
  <div class="form-group">
    <label for="name">Nom de la ressource</label>
    <input type="text" class="form-control" id="name" name="name" placeholder="e.g. Voiture" value="${resourceType.name}">
  </div>

  <button type="submit" class="btn btn-default">Modifier</button>
</form>
