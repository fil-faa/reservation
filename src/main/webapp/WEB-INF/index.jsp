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

<c:if test="${connected}">
    <%@include file="jspf/menu.jspf"%>
</c:if>
<div class="container">



    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error.label}
        </div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
                ${success.label}
        </div>
    </c:if>
    <jsp:include page="pages/${page}"/>



</div

</body>
</html>
