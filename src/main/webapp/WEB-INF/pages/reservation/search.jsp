<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Je rechercher une ressource</h2>

<p>Par l'interm�diaire de ce formulaire, choisissez la ressource qui vous convient sur la p�riode de votre choix.</p>

<form action="${appPath}/reservations/search" method="GET">

    <input type="text" class="form-control" name="searchRange" value="01/01/2015 - 01/31/2015" />

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
                            "F�vrier",
                            "Mars",
                            "Avril",
                            "Mai",
                            "Juin",
                            "Juillet",
                            "Ao�t",
                            "Septembre",
                            "Octobre",
                            "Novembre",
                            "D�cembre"
                        ],
                    },
                    startDate: new Date(),
                    endDate: new Date()
                });

        function book(resourceId) {
            $.post('${appPath}/reservations/', {
                resourceId: resourceId,
                range: $('input[name="searchRange"]').valueOf()
            })
        }
    </script>

    <button class="btn btn-primary btn-md" type="submit">Je recherche</button>


</form>

<c:if test="${not empty resources}">
<div class="body-box">
  <h2>Liste des resources disponibles</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Nom de la ressource</th>
      <th>Type de la ressource</th>
      <th>Pr�nom du propri�taire</th>
      <th>Nom du propri�taire</th>
      <th>R�server</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="resource" items="${resources}">
      <tr>
        <td>${resource.name}</td>
        <td>${resource.type.name}</td>
        <td>${resource.owner.firstName}</td>
        <td>${resource.owner.lastName}</td>
        <td><a onclick="book()" class="btn btn-primary"></a></td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

</div>
</c:if>
