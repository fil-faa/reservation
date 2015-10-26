<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-search"></i> Recherche</h3>
    </div>
    <div class="panel-body">
        <form method="get" action="${appPath}/reservations/">

            <div class="form-group">
                <label for="search_resourceType">Type des ressources</label>
                <select class="form-control" id="search_resourceType" name="searchedType" >
                    <option value="">Tous les types</option>
                    <c:forEach var="type" items="${types}">
                        <c:if test="${param.searchedType==type.id}">
                            <option selected="selected" value="${type.id}">${type.name}</option>
                        </c:if>
                        <c:if test="${param.searchedType!=type.id}">
                            <option value="${type.id}">${type.name}</option>
                        </c:if>
                    </c:forEach>

                </select>
            </div>
            <div class="form-group">
                <label for="search_user">Utilisateur</label>
                <select class="form-control" id="search_user" name="searchedUser" >
                    <option value="">Tous les utilisateurs</option>
                    <c:forEach var="user" items="${users}">
                        <c:if test="${param.searchedUser==user.id}">
                            <option selected="selected" value="${user.id}">${user.lastName} ${user.firstName}</option>
                        </c:if>
                        <c:if test="${param.searchedUser!=user.id}">
                            <option value="${user.id}">${user.lastName} ${user.firstName}</option>
                        </c:if>
                    </c:forEach>

                </select>
            </div>
            <div class="form-group">
                <label for="search_lastname">Nom de la ressource</label>
                <input type="text" class="form-control" id="search_lastname" name="searchedName" placeholder="Ordinateur" value="${param.searchedName}">
            </div>
            <div class="form-group">
                <label for="searchRange">Dates</label>
                <input type="text" class="form-control" id="searchRange" name="searchRange" value="${param.searchRange}" />
            </div>
            <button type="submit" class="btn btn-primary form-control">Rechercher</button>
        </form>
    </div>
</div>
<script type="text/javascript">
    $('input[name="searchRange"]').daterangepicker(
            {
                timePicker: true,
                "timePickerIncrement": 15,
                "timePicker24Hour": true,
                locale: {
                    format: 'DD/MM/YYYY hh:mm',
                    applyLabel: "OK",
                    cancelLabel: 'Annuler',
                    "daysOfWeek": [
                        "Lu",
                        "Ma",
                        "Me",
                        "Je",
                        "Ve",
                        "Sa",
                        "Di"
                    ],
                    "monthNames": [
                        "Janvier",
                        "Février",
                        "Mars",
                        "Avril",
                        "Mai",
                        "Juin",
                        "Juillet",
                        "Aoét",
                        "Septembre",
                        "Octobre",
                        "Novembre",
                        "Décembre"
                    ],
                }
            });
</script>
