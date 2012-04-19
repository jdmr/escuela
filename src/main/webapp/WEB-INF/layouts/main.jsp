<%-- 
    Document   : main
    Created on : Jan 24, 2012, 1:43:27 PM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<!doctype html>
<html>
    <head>
        <title><sitemesh:write property='title'/> - Escuela</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-responsive.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/app.css' />" type="text/css">
        <sitemesh:write property='head'/>
    </head>
    <body>
    <div class="container">
            <nav class="navbar">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="<c:url value='/'/>">Escuela</a>
                    <div class="nav-collapse">
                        <sitemesh:write property="nav"/>
                    </div>
                </div>
            </div>
        </nav>
        <sitemesh:write property='body'/>
    </div>

    <!-- JavaScript at the bottom for fast page loading -->

    <!-- Grab Google CDN's jQuery, with a protocol relative URL; fall back to local if offline -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="<c:url value='/js/jquery-1.7.1.min.js'/>"><\/script>')</script>

    <!-- end scripts -->        
    <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/js/app.js' />"></script>
    <sitemesh:write property="content"/>
</body>
</html>
