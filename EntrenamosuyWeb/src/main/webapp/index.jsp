<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<form id="formInit" method="get" style="display: none;">
    <input type="hidden" name="servletName1" id="servletName1" value="">
</form>
<head>
<meta charset="ISO-8859-1">
<title>Entrenamos.uy</title>
</head>
<body>
</body>
  	<script>
		  window.onload = function() {
			  let form = document.getElementById("formInit");
			  form.action = "LogIn";
			  form.submit();
		  };
	  </script>
</html>