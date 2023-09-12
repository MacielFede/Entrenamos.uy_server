<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp" %>
</head>
<body>
<form id="formInit" method="get" style="display: none;">
    <input type="hidden" name="servletName1" id="servletName1" value="">
</form>
</body>
  	<script>
		  window.onload = function() {
			  let form = document.getElementById("formInit");
			  form.action = "LogIn";
			  form.submit();
		  };
	  </script>
</html>