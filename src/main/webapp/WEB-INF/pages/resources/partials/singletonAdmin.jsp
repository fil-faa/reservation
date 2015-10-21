<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Modifier la ressource</h1>
<form method="post">
  <input type="hidden" name="resourceId" value="${resource.id}">
  <div class="form-group">
    <label for="resourceName">Nom</label>
    <input type="text" class="form-control" id="resourceName" name="name" placeholder="Ordinateur" value="${ressource.name}">
  </div>
  <div class="form-group">
    <label for="resourceLocalisation">Localisation</label>
    <input type="text" class="form-control" id="resourceLocalisation" name="localisation" placeholder="Salle B116" value="value="${ressource.localisation}">
  </div>
  <div class="form-group">
    <label for="resourceDescription">Description</label>
    <textarea class="form-control" id="resourceDescription" name="description" rows="3" content="value="${ressource.description}"></textarea>
  </div>
  <div class="form-group">
    <label for="resourceType">Type de la ressource</label>
    <select class="form-control" id="resourceType" name="id_type" >
      <c:forEach items="resourceTypes" var="type">
        <option  value="${type.id}" >${type.nom}</option>
        <c:choose>
          <c:when test="${type.id==ressource.id}" >
          <option selected="selected"  value="${type.id}" >${type.nom}</option>
          </c:when>
          <c:otherwise>
            <option  value="${type.id}" >${type.nom}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </div>
  <div class="form-group">
    <label for="resourceResponsable">Responsable de la ressource</label>
    <select class="form-control" id="resourceResponsable" name="id_responsable" >
      <c:forEach items="users" var="user">
        <option  value="${user.id}" >${user.nom}</option>
        <c:choose>
          <c:when test="${user.id==ressource.responsable}" >
            <option selected="selected"  value="${user.id}" >${user.nom}</option>
          </c:when>
          <c:otherwise>
            <option  value="${user.id}" >${user.nom}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </div>
  <button type="submit" class="btn btn-default">Modifier la ressource</button>
</form>
<form method="post" action="delete">
  <input type="hidden" name="resourceId" value="${resource.id}">
  <button type="submit" class="btn btn-warning">Supprimer la ressource</button>

</form>