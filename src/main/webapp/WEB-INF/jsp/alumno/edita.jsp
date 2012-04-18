<%-- 
    Document   : nuevo
    Created on : Apr 18, 2012, 7:59:11 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edita Alumno</h1>
        <c:url var="actualizaUrl" value="/alumno/actualiza" />
        <form:form commandName="alumno" action="${actualizaUrl}" method="post">
            <form:hidden path="id" />
            <form:hidden path="version" />
            <form:errors path="*">
                <div class="alert alert-block alert-error fade in" role="status">
                    <a class="close" data-dismiss="alert">×</a>
                    <c:forEach items="${messages}" var="message">
                        <p>${message}</p>
                    </c:forEach>
                </div>
            </form:errors>
            
            <fieldset>
                <div>
                    <label for="matricula">Matrícula</label><br/>
                    <form:input path="matricula" required="true" />
                    <form:errors path="matricula" />
                </div>
                <div>
                    <label for="nombre">Nombre</label><br/>
                    <form:input path="nombre" />
                    <form:errors path="nombre" />
                </div>
                <div>
                    <label for="apellido">Apellido</label><br/>
                    <form:input path="apellido" />
                    <form:errors path="apellido" />
                </div>
                <div>
                    <label for="fechaNacimiento">Fecha de Nacimiento</label><br/>
                    <form:input path="fechaNacimiento" />
                    <form:errors path="fechaNacimiento" />
                </div>
                <div>
                    <label for="esHombre">¿Es hombre?</label><br/>
                    <form:checkbox path="esHombre" />
                    <form:errors path="esHombre" />
                </div>
                <div>
                    <label for="correo">Correo</label><br/>
                    <form:input path="correo" />
                    <form:errors path="correo" />
                </div>
                <div>
                    <input type="submit" value="Crear Alumno" />
                    <a href="<c:url value='/alumno'/>">Cancelar</a>
                </div>
            </fieldset>
        </form:form>
        <script>
            document.forms[0].matricula.focus();
        </script>
    </body>
</html>
