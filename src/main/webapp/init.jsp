<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Entrenamos.uy</title>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
</head>
<body>
<div>Bienvenido al mejor gimnasio de la historia<%= userName != null ? ", "+userName : "" %>.</div>
</body>
</html>