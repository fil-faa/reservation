<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>Liste des utilisateurs</p>


<c:forEach var="user" items="${users}">

    <p>${user.mail}</p>

</c:forEach>
