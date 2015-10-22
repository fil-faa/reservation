<%@ page import="fr.emn.fil.reservation.model.entities.Resource" %>
<h1>Ajouter une reservation</h1>

<form method="POST">
    <div class="form-group">
        <label for="startDate">Date début</label>
        <input type="date" class="form-control" id="startDate" name="startDate">
    </div>
    <div class="form-group">
        <label for="endDate">Date fin</label>
        <input type="date" class="form-control" id="endDate" name="endDate">
    </div>
    <div class="form-group">
        <label for="resourceId">Ressource</label>
        <select id="resourceId" name="resourceId">
            <c:forEach var="resource" items="${resources}" varStatus="loopCount">
                <option value="${resource.id}">${resource.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="userId">Utilisateur</label>
        <select id="userId" name="userId">
            <c:forEach var="user" items="${users}" varStatus="loopCount">
                <option value="${user.id}">${user.firstName} ${user.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-default">Valider</button>
</form>