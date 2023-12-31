<%@page import="publishers.DtClass"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Ranking de clases dictadas</title>
<%@include file="header.jsp"%>
</head>
<body>
	<%
		List<DtClass> classes = (List<DtClass>) request.getAttribute("classes");
	%>
	<h1 align="center">Ranking de dictado a clases</h1>
	<main class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Puesto</th>
					<th scope="col">Nombre</th>
					<th scope="col">Fecha</th>
					<th scope="col">URL</th>
					<th scope="col">Cant. Socios</th>
				</tr>
			</thead>
			<tbody>
				<%
	  		int i = 1;
	  		for (DtClass dt : classes) {
	  	%>
				<tr>
					<th scope="row"><%=i%></th>
					<td><%=dt.getName()%></td>
					<td><%=dt.getDateAndTime() != null ? dt.getDateAndTime().getTime().toString() : ""%></td>
					<td><%=dt.getUrl()%></td>
					<td><%=dt.getEnrollmentsQuantity()%></td>
				</tr>
				<%
			i++;
	  	}
		%>

			</tbody>
		</table>
	</main>

</body>
<%@include file="footer.jsp"%>
</html>