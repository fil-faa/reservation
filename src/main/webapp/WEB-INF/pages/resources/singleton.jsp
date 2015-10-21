<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${isAdmin}">
        <div>
        <c:import url="/WEB-INF/pages/resources/partials/singletonAdmin.jsp"></c:import>
        </div>
    </c:when>
    <c:otherwise>
        <div>
        <c:import url="/WEB-INF/pages/resources/partials/singletonUser.jsp"></c:import>
        </div>
    </c:otherwise>
</div>
</c:choose>

<div class="body-box">
    <h2>Réserver la ressource</h2>

    <form method="post">
        <input type="hidden" name="id_ressource" value="${resource.id}">
        <div class="form-group">
            <label for="dateStart">Date de début </label>
            <input type="datetime" class="form-control" id="dateStart" name="dateStart">
        </div>
        <div class="form-group">
            <label for="dateEnd">Date de fin </label>
            <input type="datetime" class="form-control" id="dateEnd" name="dateEnd">
        </div>
        <button type="submit" class="btn btn-default">Réserver la ressource</button>

    </form>
</div>