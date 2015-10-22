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
      <c:if test="${admin}">
      <th>Actions</th>
      </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ressourceType" items="${ressourceTypes}">
      <tr>
        <td><a href="${appPath}?id_type=${ressourceType.id}">${ressourceType.name}</a></td>
        <c:if test="${admin}">
          <td>
            <a href="${appPath}/resourceTypes/${ressourceType.id}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
            <a href="${appPath}/resourceTypes/delete?id=${ressourceType.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
          </td>
        </c:if>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>
