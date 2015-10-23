<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${admin}">
<div>
    <c:import url="/WEB-INF/pages/resources/partials/addForm.jsp"></c:import>
</div>
</c:if>
<div class="side-box">
    <c:import url="/WEB-INF/pages/resources/partials/searchForm.jsp"></c:import>
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
            <th>Actions</th>
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
                    <a href="${appName}/resources/${resource.id}"><span class="glyphicon glyphicon-cart" aria-hidden="true"></span></a>
                </td>
                <c:if test="${isAdmin}">
                    <td>
                        <a href="${appName}/resources/${resource.id}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="${appName}/resources/delete?id=${resource.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>