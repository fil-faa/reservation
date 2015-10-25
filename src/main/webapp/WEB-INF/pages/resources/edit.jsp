<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-default">
    <div class="panel-body">
<h1>Editer une ressource</h1>

<form method="post">
    <div class="form-group">
        <label for="resourceName">Nom</label>
        <input type="text" class="form-control" id="resourceName" name="name" placeholder="Tondeuse"
               value="${resource.name}">
    </div>
    <div class="form-group">
        <label for="resourceLocalisation">Localisation</label>
        <input type="text" class="form-control" id="resourceLocalisation" name="place" placeholder="Salle B116"
               value="${resource.place}">
    </div>
    <div class="form-group">
        <label for="resourceDescription">Description</label>
        <textarea class="form-control" id="resourceDescription" name="description" rows="3">
            ${resource.description}
        </textarea>
    </div>
    <div class="form-group">
        <label for="resourceType">Type de la ressource</label>
        <select class="form-control" id="resourceType" name="typeId">
            <c:forEach var="type" items="${resourceTypes}">
                <option value="${type.id}"
                        <c:if test="${type.id == resource.type.id}">
                            selected
                        </c:if>
                        >${type.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="resourceResponsable">Responsable</label>
        <select class="form-control" id="resourceResponsable" name="userId">
            <option value="${resource.owner.id}">${resource.owner.firstName} ${resource.owner.lastName}</option>
        </select>
    </div>

    <button type="submit" class="btn btn-default">Modifier</button>
</form>
        </div>
    </div>