<h1>Recherche d'un type de ressource</h1>

<form method="get" action="">
    <div class="form-group">
        <label for="search_name">Nom du type de ressource</label>
        <input type="text" class="form-control" id="search_name" name="name" placeholder="e.g. Voiture"
               value="${param.ressourceTypeName}">
    </div>

    <button type="submit" class="btn btn-default">Valider</button>
</form>