<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="side-box">
  <%@include file="partials/searchForm.jsp"%>
  <c:if test="${admin}">
    <button id="addButton" type="submit" class="btn btn-primary form-control" onclick="$('#addForm').toggle('200');$('#addButton').hide();"><i class="glyphicon glyphicon-plus"></i> Ajouter un type</button>
    <div id="addForm">
      <%@include file="partials/addForm.jsp"%>
  </div>
  </c:if>
</div>
<div class="body-box">
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h1 class="panel-title">Liste des types de ressources</h1>
    </div>
    <div class="panel-body">
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
    <c:forEach var="resourceType" items="${resourceTypes}">
      <tr>
        <td><a href="${appPath}/resources/?searchedType=${resourceType.id}">${resourceType.name}</a></td>
        <c:if test="${admin}">
          <td>
            <div class="btn-group" role="group">
              <button type="button" class="btn btn-default" onclick="location.href='${appPath}/resourceTypes/${resourceType.id}'"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
              <button type="button" class="btn btn-default" onclick="location.href='${appPath}/resourceTypes/delete?id=${resourceType.id}'"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
            </div>
          </td>
        </c:if>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>
    </div>
  </div>

