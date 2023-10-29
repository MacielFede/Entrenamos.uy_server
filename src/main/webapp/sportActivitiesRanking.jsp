<%@page import="publishers.DtActivity"%>
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
		List<DtActivity> activities = (List<DtActivity>) request.getAttribute("activities");
	%>
	<h1 align="center">Ranking de actividades deportivas</h1>
	<main class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Puesto</th>
					<th scope="col">Nombre</th>
					<th scope="col">Costo</th>
					<th scope="col">Descripción</th>
					<th scope="col">Clases</th>
				</tr>
			</thead>
			<tbody>
				<%
	  		int i = 1;
	  		for (DtActivity dt : activities) {
	  	%>
				<tr>
					<th scope="row"><%=i%></th>
					<td><%=dt.getName()%></td>
					<td><%=dt.getPrice()%></td>
					<td><%=dt.getDescription()%></td>
					<td><%=dt.getClassesQuantity()%></td>
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