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
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.firstName}</td>
                <td>${u.lastName}</td>
                <td>${u.mail}</td>
                <td>${u.telephone}</td>
                    <td>
                        <a href="${appPath}/users/${u.id}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <c:if test="${u.id != user.id}">
                        <a href="${appPath}/users/delete?id=${u.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                        </c:if>
                    </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>