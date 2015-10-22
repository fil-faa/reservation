<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>

  <%@include file="partials/addForm.jsp"%>
</div>
<div class="side-box">
  <%@include file="partials/searchForm.jsp"%>

</div>
<div class="body-box">
  <h2>Liste des types de ressource</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Nom</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ressourceType" items="${ressourceTypes}">
      <tr>
        <td>${ressourceType.name}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>
</div>
