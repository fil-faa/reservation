<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>

    <%@include file="partials/addForm.jsp"%>
</div>
<div class="side-box">
    <%@include file="partials/searchForm.jsp"%>

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
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.mail}</td>
                <td>${user.telephone}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>