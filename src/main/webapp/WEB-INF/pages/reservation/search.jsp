<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Rechercher une ressource</h2>

<p>Par l'intermédiaire de ce formulaire, choisissez la ressource qui vous convient sur la période de votre choix.</p>

<form action="${appPath}/reservations/search" method="GET">

    <input type="text" class="form-control" name="searchRange" value="${searchRange}" />


    <select class="form-control" name="typeId">
        <option value="">Tous les types</option>
        <c:forEach var="type" items="${types}">
            <option value="${type.id}">${type.name}</option>
        </c:forEach>
    </select>


    <button class="btn btn-primary btn-md" type="submit">Je recherche</button>


</form>

<c:if test="${not empty resources}">
<div class="body-box">
  <h2>Liste des resources disponibles : ${searchRange}</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Nom de la ressource</th>
      <th>Type de la ressource</th>
      <th>Prénom du propriétaire</th>
      <th>Nom du propriétaire</th>
      <th>Réserver</th>
    </tr>
    </thead>
    <tbody>

    <c:if test="${empty resources}">

        <p class="lead">Aucune ressource n'a été trouvée pour la période sélectionnée</p>

    </c:if>

    <c:forEach var="resource" items="${resources}">
      <tr>
        <td>${resource.name}</td>
        <td>${resource.type.name}</td>
        <td>${resource.owner.firstName}</td>
        <td>${resource.owner.lastName}</td>
        <td>


            <form method="POST" action="${appPath}/reservations/">
                <input type="hidden" name="reservationRange" value="${searchRange}"/>
                <input type="hidden" name="resourceId", value="${resource.id}">
                <button class="btn btn-primary" type="submit">Réserver</button>
            </form>
        </td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

</div>
</c:if>

<script type="text/javascript">
    $('input[name="searchRange"]').daterangepicker(
            {
                locale: {
                    format: 'DD/MM/YYYY',
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
