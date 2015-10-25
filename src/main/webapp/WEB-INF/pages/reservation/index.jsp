<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="side-box">
  <%@include file="partials/searchForm.jsp"%>

</div>

<div class="body-box">
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h1 class="panel-title">Liste des réservations</h1>
    </div>
    <div class="panel-body">
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Date de début</th>
      <th>Date de fin</th>
      <th>Utilisateur</th>
      <th>Ressource</th>
      <th>Type de ressource</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reservation" items="${reservations}">
      <tr>
        <td><fmt:formatDate type="date" value="${reservation.start}" pattern="dd/MM/YYYY"/></td>
        <td><fmt:formatDate type="date" value="${reservation.end}" pattern="dd/MM/YYYY"/></td>
        <td>${reservation.user.firstName} ${reservation.user.lastName}</td>
        <td>${reservation.resource.name}</td>
        <td>${reservation.resource.type.name}</td>
        <td><a class="btn btn-danger form-control" href="${appPath}/reservations/cancel?reservationId=${reservation.id}">Annuler</a></td>
      </tr>
    </c:forEach>

    </tbody>
  </table>
</div>
    </div>
  </div>
