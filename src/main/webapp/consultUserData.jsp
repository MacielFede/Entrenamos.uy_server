<%@page import="java.util.Date"%>
<%@page import="dataTypes.DtClass"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="dataTypes.DtUser"%>
<%@page import="dataTypes.DtClass"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="header.jsp"%>
</head>
<body>
	<%
		DtUser userInfo = (DtUser) request.getAttribute("userInfo");
	
		if (request.getSession(false).getAttribute("userType") == "M") {
			Map<String, DtClass> memberClasses = (Map<String, DtClass>) request.getAttribute("memberClasses");
		}
		else if (request.getSession(false).getAttribute("userType") == "P") {
			Map<String, DtClass> professorClasses = (Map<String, DtClass>) request.getAttribute("professorClasses");
		}
	%>

</body>

<%@include file="footer.jsp" %>
</html>