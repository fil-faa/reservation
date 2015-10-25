<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Recherche</h3>
    </div>
    <div class="panel-body">
<form method="get">

    <div class="form-group">
        <label for="search_resourceType">Type de la ressource</label>
        <select class="form-control" id="search_resourceType" name="searchedType" >
            <option value="">Tous les types</option>
            <c:forEach var="type" items="${types}">
                <option value="${type.id}">${type.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="search_lastname">Nom de la ressource</label>
        <input type="text" class="form-control" id="search_lastname" name="searchedName" placeholder="Ordinateur" value="${param.resourceName}">
    </div>

    <button type="submit" class="btn btn-primary form-control">Rechercher</button>
</form>
        </div>
    </div>
