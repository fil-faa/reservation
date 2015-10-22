<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="body-box">
  <h2>Liste de mes réservations</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Date de début</th>
      <th>Date de fin</th>
      <th>Utilisateur</th>
      <th>Ressource</th>
      <th>Type de ressource</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reservation" items="${reservations}">
      <tr>
        <td>${reservation.start}</td>
        <td>${reservation.end}</td>
        <td>${reservation.user}</td>
        <td>${reservation.resource.name}</td>
        <td>${reservation.resource.type.name}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>
</div>