<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Recherche</h3>
    </div>
    <div class="panel-body">
        <form method="get" action="${appPath}/users/">
            <div class="form-group">
                <label for="search_lastname">Nom</label>
                <input type="text" class="form-control" id="search_lastname" name="searchedLastName" placeholder="Dupont" value="${param.searchedLastName}">
            </div>
            <div class="form-group">
                <label for="search_firstname">Prénom</label>
                <input type="text" class="form-control" id="search_firstname" name="searchedFirstName" placeholder="Laurent" value="${param.searchedFirstName}">
            </div>
            <div class="form-group">
                <label for="search_mail">Mail</label>
                <input type="email" class="form-control" id="search_mail" name="searchedMail" placeholder="example@domaine.com" value="${param.searchedMail}">
            </div>
            <div class="form-group">
                <label for="search_phone">Téléphone</label>
                <input type="tel" class="form-control" id="search_phone" name="searchedPhone" placeholder="0123456789" value="${param.searchedPhone}">
            </div>
            <button type="submit" class="btn btn-primary form-control">Rechercher</button>
        </form>
    </div>
</div>
