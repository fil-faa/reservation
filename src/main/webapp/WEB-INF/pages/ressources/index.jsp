<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <c:import url="/WEB-INF/pages/ressources/partials/addForm.jsp"></c:import>
</div>
<div class="side-box">
    <c:import url="/WEB-INF/pages/ressources/partials/searchForm.jsp"></c:import>
    </form>
</div>
<div class="body-box">
    <h2>Liste des ressources</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Localisation</th>
            <th>Type</th>
            <th>Responsable</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="resource" items="${resources}">
            <tr>
                <td>${resource.nom}</td>
                <td>${resource.description}</td>
                <td>${resource.localisation}</td>
                <td>${resource.type.nom}</td>
                <td>${resource.responsable.nom} ${resource.responsable.prenom}</td>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>