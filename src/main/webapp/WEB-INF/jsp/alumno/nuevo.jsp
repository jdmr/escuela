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
        <jsp:include page="../menu.jsp" >
            <jsp:param name="menu" value="alumno" />
        </jsp:include>
        <h1>Nuevo Alumno</h1>
        <c:url var="creaUrl" value="/alumno/crea" />
        <form:form commandName="alumno" action="${creaUrl}" method="post">
            <form:errors path="*">
                <div class="alert alert-block alert-error fade in" role="status">
                    <a class="close" data-dismiss="alert">×</a>
                    <c:forEach items="${messages}" var="message">
                        <p>${message}</p>
                    </c:forEach>
                </div>
            </form:errors>
            
            <fieldset>
                <div class="row">
                    <div class="span4 control-group">
                        <label for="matricula">Matrícula</label>
                        <form:input path="matricula" required="true" />
                        <form:errors path="matricula" />
                    </div>
                    <div class="span4 control-group">
                        <label for="nombre">Nombre</label>
                        <form:input path="nombre" />
                        <form:errors path="nombre" />
                    </div>
                </div>
                <div class="row">
                    <div class="span4 control-group">
                        <label for="apellido">Apellido</label>
                        <form:input path="apellido" />
                        <form:errors path="apellido" />
                    </div>
                    <div class="span4 control-group">
                        <label for="fechaNacimiento">Fecha de Nacimiento</label>
                        <form:input path="fechaNacimiento" />
                        <form:errors path="fechaNacimiento" />
                    </div>
                </div>
                <div class="row">
                    <div class="span4 control-group">
                        <label for="esHombre">¿Es hombre?</label>
                        <form:checkbox path="esHombre" />
                        <form:errors path="esHombre" />
                    </div>
                    <div class="span4 control-group">
                        <label for="correo">Correo</label>
                        <form:input path="correo" />
                        <form:errors path="correo" />
                    </div>
                </div>
                <div>
                    <btn type="submit" class="btn btn-primary btn-large"><i class="icon-user"></i> Crea Usuario</btn>
                    <a href="<c:url value='/alumno'/>" class="btn btn-large"><i class="icon-remove-circle"></i> Cancelar</a>
                </div>
            </fieldset>
        </form:form>
        <script>
            document.forms[0].matricula.focus();
        </script>
    </body>
</html>
