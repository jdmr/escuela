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
        <h1>Lista de Alumnos</h1>
        <p>${hola}</p>
        <table>
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
                        <td>${alumno.matricula}</td>
                        <td>${alumno.nombre}</td>
                        <td>${alumno.apellido}</td>
                        <td>${alumno.fechaNacimiento}</td>
                        <td>${alumno.esHombre}</td>
                        <td>${alumno.correo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
