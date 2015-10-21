<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Recherche</h1>
<form method="get">

    <div class="form-group">
        <label for="search_resourceType">Type de la ressource</label>
        <select class="form-control" id="search_resourceType" name="id_type" >
            <c:forEach items="resourceTypes" var="type">
                <option value="${type.id}" >${type.nom}</option>
            </c:forEach>
            </select>
    </div>
    <div class="form-group">
        <label for="search_lastname">Nom de la ressource</label>
        <input type="text" class="form-control" id="search_lastname" name="name" placeholder="Ordinateur" value="${param.resourceName}">
    </div>

    <button type="submit" class="btn btn-default">Rechercher</button>
</form>