<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Recherche</h3>
    </div>
    <div class="panel-body">
            <form method="get" action="">
                <div class="form-group">
                    <label for="search_name">Nom du type de ressource</label>
                    <input type="text" class="form-control" id="search_name" name="name" placeholder="e.g. Voiture"
                           value="${param.resourceTypeName}">
                </div>

                <button type="submit" class="btn btn-primary form-control">Rechercher</button>
            </form>
    </div>
</div>