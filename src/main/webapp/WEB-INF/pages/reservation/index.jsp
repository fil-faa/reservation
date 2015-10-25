<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Mes r�servations</h1>


<div class="side-box">
  <%@include file="partials/searchForm.jsp"%>

</div>

<div class="body-box">
  <h2>Liste des r�servations</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Date de d�but</th>
      <th>Date de fin</th>
      <th>Utilisateur</th>
      <th>Ressource</th>
      <th>Type de ressource</th>
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
      </tr>
    </c:forEach>

    </tbody>
  </table>
</div>