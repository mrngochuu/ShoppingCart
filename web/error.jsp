<%-- 
    Document   : error
    Created on : Dec 2, 2019, 10:00:17 PM
    Author     : ngochuu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>G.A.U | Error page</title>
    </head>
    <body>
        <h1>ERROR PAGE</h1>
        <font color="red">
        ${requestScope.ERROR}
        </font>
    </body>
</html>
