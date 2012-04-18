<%-- 
    Document   : ver
    Created on : Apr 18, 2012, 8:33:53 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Alumno ${alumno.matricula}</h1>
        <div>
            <a href="<c:url value='/alumno'/>">Lista de Alumnos</a>
            <a href="<c:url value='/alumno/nuevo'/>">Nuevo Alumno</a>
            <a href="<c:url value='/alumno/edita/${alumno.matricula}'/>">Edita Alumno</a>
            <a href="<c:url value='/alumno/elimina/${alumno.matricula}'/>">Elimina Alumno</a>
        </div>
        <c:if test="${not empty mensaje}">
            <div>
                <p>${mensaje}</p>
            </div>
        </c:if>
        <div>
            <div>
                <h4>Matrícula</h4>
                <h3>${alumno.matricula}</h3>
            </div>
            <div>
                <h4>Nombre</h4>
                <h3>${alumno.nombre}</h3>
            </div>
            <div>
                <h4>Apellido</h4>
                <h3>${alumno.apellido}</h3>
            </div>
            <div>
                <h4>Fecha de Nacimiento</h4>
                <h3><fmt:formatDate value="${alumno.fechaNacimiento}" pattern="dd/MM/yyyy" /></h3>
            </div>
            <div>
                <h4>¿Es hombre?</h4>
                <h3><input type="checkbox" <c:if test="${alumno.esHombre}">checked="checked"</c:if> disabled="true"/></h3>
            </div>
            <div>
                <h4>Correo Electrónico</h4>
                <h3>${alumno.correo}</h3>
            </div>
        </div>
    </body>
</html>
