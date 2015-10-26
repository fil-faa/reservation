<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-plus"></i> Ajouter une ressource</h3>
    </div>
    <div class="panel-body">
<form method="POST">
    <div class="form-group">
        <label for="resourceName">Nom</label>
        <input type="text" class="form-control" id="resourceName" name="name" placeholder="Tondeuse">
    </div>
    <div class="form-group">
        <label for="resourceLocalisation">Localisation</label>
        <input type="text" class="form-control" id="resourceLocalisation" name="place" placeholder="Salle B116">
    </div>
    <div class="form-group">
        <label for="resourceDescription">Description</label>
        <textarea class="form-control" id="resourceDescription" name="description" rows="3"></textarea>
    </div>
    <div class="form-group">
        <label for="resourceType">Type de la ressource</label>
        <select class="form-control" id="resourceType" name="typeId" >
            <c:forEach var="type" items="${resourceTypes}">
                <option value="${type.id}" >${type.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="resourceResponsable">Responsable</label>
        <select class="form-control" id="resourceResponsable" name="userId" >
                <option value="${user.id}" >${user.firstName} ${user.lastName}</option>
        </select>
    </div>

    <button type="submit" class="btn btn-default">Ajouter</button>
</form>
    </div>
</div>