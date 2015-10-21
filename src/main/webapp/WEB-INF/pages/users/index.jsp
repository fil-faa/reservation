<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <c:import url="/WEB-INF/pages/users/partials/addForm.jsp"></c:import>
</div>
<div class="side-box">
    <c:import url="/WEB-INF/pages/users/partials/searchForm.jsp"></c:import>
    </form>
</div>
<div class="body-box">
        <h2>Liste des utilisateurs</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Pr&eacute;nom</th>
            <th>Mail</th>
            <th>T&eacute;l&eacute;phone</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.nom}</td>
                <td>${user.prenom}</td>
                <td>${user.mail}</td>
                <td>${user.telephone}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>