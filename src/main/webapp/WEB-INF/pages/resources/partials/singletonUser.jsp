<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped">
  <thead>
  <tr>
    <th>Nom</th>
    <th>Description</th>
    <th>Localisation</th>
    <th>Type</th>
    <th>Responsable</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="resource" items="${resources}">
    <tr>
      <td>${resource.nom}</td>
      <td>${resource.description}</td>
      <td>${resource.localisation}</td>
      <td>${resource.type.nom}</td>
      <td>${resource.responsable.nom} ${resource.responsable.prenom}</td>

    </tr>
  </c:forEach>

  </tbody>
</table>