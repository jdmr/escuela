<%-- 
    Document   : index
    Created on : Apr 16, 2012, 10:54:59 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/menu.jsp" >
            <jsp:param name="menu" value="principal" />
        </jsp:include>
        <h1>Hello World!</h1>
    </body>
</html>
