<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="side-box">
    <c:import url="/WEB-INF/pages/resources/partials/searchForm.jsp"></c:import>
<c:if test="${admin}">
    <button id="addButton" type="submit" class="btn btn-primary form-control" onclick="$('#addForm').toggle('200');$('#addButton').hide();"><i class="glyphicon glyphicon-plus"></i> Ajouter une ressource</button>
    <div id="addForm">
        <c:import url="/WEB-INF/pages/resources/partials/addForm.jsp"></c:import>
    </div>
</c:if>
</div>
<div class="body-box">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h1 class="panel-title">Liste des ressources</h1>
            </div>
            <div class="panel-body">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Localisation</th>
            <th>Type</th>
            <th>Responsable</th>
            <th style="min-width: 140px">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="resource" items="${resources}">
            <tr>
                <td>${resource.name}</td>
                <td>${resource.description}</td>
                <td>${resource.place}</td>
                <td>${resource.type.name}</td>
                <td>${resource.owner.firstName} ${resource.owner.lastName}</td>
                    <td>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default"
                                    onclick="location.href='${appPath}/reservations/search?&searchedName=${resource.name}'">
                                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                            </button>
                            <c:if test="${admin}">
                                <button type="button" class="btn btn-default"
                                        onclick="location.href='${appPath}/resources/${resource.id}'">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="location.href='${appPath}/resources/delete?id=${resource.id}'">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </c:if>

                        </div>
                    </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
            </div>
        </div>
    </div>
