<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>

</div>
<div class="side-box">
    <%@include file="partials/searchForm.jsp"%>
    <button id="addButton" type="submit" class="btn btn-primary form-control" onclick="$('#addForm').toggle('200');$('#addButton').hide();">Ajouter un utilisateur</button>
    <div id="addForm">
    <%@include file="partials/addForm.jsp"%>
    </div>
</div>
<div class="body-box">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h1 class="panel-title">Liste des utilisateurs</h1>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Mail</th>
                    <th>Téléphone</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.lastName}</td>
                        <td>${u.firstName}</td>
                        <td>${u.mail}</td>
                        <td>${u.telephone}</td>
                        <td>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default" onclick="location.href='${appPath}/users/${u.id}'">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                </button>
                                <button type="button" class="btn btn-default" onclick="location.href='${appPath}/reservations/?searchedUser=${u.id}'">
                                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                                </button>
                            <c:if test="${u.id != user.id}">
                                <button type="button" class="btn btn-default" onclick="location.href='${appPath}/users/delete?id=${u.id}'">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>




</div>