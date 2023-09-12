<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp" %>
</head>
<body>
	<h1 align="center">Iniciar sesiÃ³n</h1>
	<main class="container">
		<form action="LogIn" method="post">
		  <div class="mb-3">
		    <label for="inputUserName" class="form-label">Usuario</label>
		    <input type="text" class="form-control" id="inputUserName" name="inputUserName" aria-describedby="inputUserName">
		    <div id="userHelp" class="form-text">AcÃ¡ no se comparten datos manito.</div>
		  </div>
		  <div class="mb-3">
		    <label for="inputPassword" class="form-label">ContrsaeÃ±a</label>
		    <input type="password" class="form-control" id="inputPassword">
		  </div>
		  <% String logInErr = (String) request.getAttribute("logInErr"); %>
		  <% if (logInErr != null) { %>
			<div class="alert alert-danger">
			        <%= logInErr %>
			</div>
		  <% } %>
		  <!--  
		  <div class="mb-3 form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1">
		    <label class="form-check-label" for="exampleCheck1">Mantener sesiÃ³n iniciada</label>
		  </div>-->
		  <button type="submit" class="btn btn-primary">Confirmar</button>
		 
		</form>
	</main>
</body>
<%@include file="footer.jsp" %>
</html>