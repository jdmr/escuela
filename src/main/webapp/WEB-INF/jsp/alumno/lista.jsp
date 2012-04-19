<%-- 
    Document   : lista
    Created on : Apr 17, 2012, 9:26:06 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Alumnos</title>
    </head>
    <body>
        <jsp:include page="../menu.jsp" >
            <jsp:param name="menu" value="alumno" />
        </jsp:include>
        <h1>Lista de Alumnos</h1>
        <div class="well">
            <a href="<c:url value='/alumno/nuevo' />" class="btn btn-primary"><i class="icon-user icon-white"></i> Nuevo Alumno</a>
        </div>
        <c:if test="${not empty mensaje}">
            <div>
                <p>${mensaje}</p>
            </div>
        </c:if>
        <table id="lista" class="table table-striped">
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Fecha de Nacimiento</th>
                    <th>¿Es hombre?</th>
                    <th>Correo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${alumnos}" var="alumno">
                    <tr>
                        <td><a href="<c:url value='/alumno/ver/${alumno.matricula}'/>">${alumno.matricula}</a></td>
                        <td>${alumno.nombre}</td>
                        <td>${alumno.apellido}</td>
                        <td>${alumno.fechaNacimiento}</td>
                        <td>${alumno.esHombre}</td>
                        <td><a href="mailto:'${alumno.correo}'" target="_blank">${alumno.correo}</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <content>
        <script type="text/javascript">
            highlightMyTableRows('lista');
        </script>
    </content>
    </body>
</html>
