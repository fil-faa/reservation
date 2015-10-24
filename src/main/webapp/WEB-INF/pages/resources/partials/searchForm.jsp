<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Recherche</h1>
<form method="get">

    <div class="form-group">
        <label for="search_resourceType">Type de la ressource</label>
        <select class="form-control" id="search_resourceType" name="searchedType" >
            <c:forEach var="type" items="${resourceTypes}">
                <option value="${type.id}" >${type.name}</option>
            </c:forEach>
            </select>
    </div>
    <div class="form-group">
        <label for="search_lastname">Nom de la ressource</label>
        <input type="text" class="form-control" id="search_lastname" name="searchedName" placeholder="Ordinateur" value="${param.resourceName}">
    </div>

    <button type="submit" class="btn btn-default">Rechercher</button>
</form>