<%@page import="dataTypes.DtClass"%>
<%@page import="java.util.List"%>
<%@ page import="dataTypes.DtUser" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp" %>
</head>
<body>
	<%
		DtUser userInfo = (DtUser) request.getAttribute("userInfo");
	%>
	<h1 align="center">Modificar informacion</h1>
	<main class="container">
	<form action="ModifyUserData" method="post">
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Nickname</span>
	  		<input type="text" class="form-control" placeholder="Nickname" aria-label="Nickname">
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Email</span>
	  		<input type="text" class="form-control" placeholder="Nickname" aria-label="Email">
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Nombre</span>
	  		<input type="text" class="form-control" placeholder="Nickname" aria-label="Nombre">
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Apellido</span>
	  		<input type="text" class="form-control" placeholder="Nickname" aria-label="Apellido">
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Fecha de nacimiento</span>
	  		<input type="date" name="userBornDate" value=La fecha del tipo max=La fecha de hoy aria-label="bornDate"/>
		</div>
		
		<button class="btn btn-success" type="submit">Guardar cambios</button>	
	</form>
	</main>
</body>
<%@include file="footer.jsp" %>
</html>