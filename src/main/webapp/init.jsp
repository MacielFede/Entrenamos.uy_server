<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp" %>
</head>
<body>
	<div>Bienvenido al mejor gimnasio de la historia<%= userName != null ? ", "+userName : "!" %>.</div>
</body>
<%@include file="footer.jsp" %>
</html>