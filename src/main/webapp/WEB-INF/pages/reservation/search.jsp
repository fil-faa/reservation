<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="side-box">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Rechercher une ressource</h3>
        </div>
        <div class="panel-body">

    <p>Par l'intermédiaire de ce formulaire, choisissez la ressource qui vous convient sur la période de votre choix.</p>

    <form action="${appPath}/reservations/search" method="GET">

        <div class="form-group">
            <label for="searchRange">Dates</label>
            <input type="text" class="form-control" id="searchRange" name="searchRange" value="${searchRange}" />
        </div>

        <div class="form-group">
            <label for="typeId">Type de la ressource</label>
            <select class="form-control" id="typeId" name="typeId">
                <option value="">Tous les types</option>
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-primary form-control " type="submit">Rechercher</button>



    </form>
            </div>
        </div>
</div>

<div class="body-box">
<c:if test="${empty resources}">
    <div class="alert alert-danger" role="alert">
        Aucune ressource n'a été trouvée pour la période sélectionnée
    </div>

</c:if>

<c:if test="${not empty resources}">
    <div class="panel panel-primary">
    <div class="panel-heading">
        <h1 class="panel-title">Liste des ressources disponibles</h1>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nom de la ressource</th>
                <th>Type de la ressource</th>
                <th>Responsable</th>
                <th>Réserver <br>(période: ${searchRange})</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="resource" items="${resources}">
                <tr>
                    <td>${resource.name}</td>
                    <td>${resource.type.name}</td>
                    <td>${resource.owner.lastName} ${resource.owner.firstName}</td>
                    <td>


                        <form method="POST" action="${appPath}/reservations/">
                            <input type="hidden" name="reservationRange" value="${searchRange}"/>
                            <input type="hidden" name="resourceId", value="${resource.id}">
                            <button class="btn btn-success form-control" type="submit"><b>Réserver</b></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

</c:if>
</div>
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
                        "Août",
                        "Septembre",
                        "Octobre",
                        "Novembre",
                        "Décembre"
                    ],
                }
            });
</script>
