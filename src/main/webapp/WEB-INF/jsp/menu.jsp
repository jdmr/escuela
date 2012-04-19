<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul class="nav">
        <li<c:if test="${param.menu eq 'principal'}"> class="active"</c:if>><a href="<c:url value='/'/>">Inicio</a></li>
        <li<c:if test="${param.menu eq 'alumno'}"> class="active"</c:if>><a href='<c:url value="/alumno" />'>Alumnos</a></li>
    </ul>
</nav>