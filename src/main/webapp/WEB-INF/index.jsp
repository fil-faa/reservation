<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserDTO: alexa
  Date: 09/10/2015
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>

<%@include file="jspf/constants.jspf"%>


<%@include file="jspf/header.jspf"%>


<body>

<div class="container">

    <c:if test="${connected}">
        <%@include file="jspf/menu.jspf"%>
    </c:if>


    <jsp:include page="pages/${page}"/>


</div>
</body>
</html>
