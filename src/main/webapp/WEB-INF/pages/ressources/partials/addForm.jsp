<h1>Ajouter une ressource</h1>
<form method="post">
    <div class="form-group">
        <label for="resourceName">Nom</label>
        <input type="text" class="form-control" id="resourceName" name="name" placeholder="Dupont">
    </div>
    <div class="form-group">
        <label for="resourceLocalisation">Localisation</label>
        <input type="text" class="form-control" id="resourceLocalisation" name="localisation" placeholder="Salle B116">
    </div>
    <div class="form-group">
        <label for="resourceDescription">Description</label>
        <textarea class="form-control" id="resourceDescription" name="description" rows="3"></textarea>
    </div>
    <div class="form-group">
        <label for="resourceType">Type de la ressource</label>
        <select class="form-control" id="resourceType" name="id_type" >
            <c:forEach items="resourceTypes" var="type">
                <option value="${type.id}" >${type.nom}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="resourceResponsable">Responsable</label>
        <select class="form-control" id="resourceResponsable" name="id_resp" >
            <c:forEach items="resourceTypes" var="type">
                <option value="${type.id}" >${type.nom}</option>
            </c:forEach>
        </select>
    </div>
</form>