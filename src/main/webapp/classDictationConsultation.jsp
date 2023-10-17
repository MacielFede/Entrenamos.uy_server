<%@page import="dataTypes.DtInstitute"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de dictado de clase</title>
<%@include file="header.jsp"%>
</head>
<body>
	<%
	Map<String, DtInstitute> institutes = (Map<String, DtInstitute>) request.getAttribute("institutes");
	%>

	<main class="container mt-5">
		<h1 align="center">Consulta de dictado de clase</h1>
		<div id="feedbackAlert" class="alert hide" role="alert"></div>
		<form>
			<section class="form-floating mb-3">
				<select class="form-select" id="instituteSelect">
					<option selected disabled value="Sin seleccionar">Sin
						seleccionar</option>
					<%
					for (Map.Entry<String, DtInstitute> institute : institutes.entrySet()) {
					%>
					<option value="<%=institute.getKey()%>"><%=institute.getKey()%></option>
					<%
					}
					%>
				</select> <label for="instituteSelect">Instituto</label>
			</section>
			<section class="form-floating mb-3">
				<select class="form-select mb-3" id="activitySelect">
					<option selected disabled value="Sin seleccionar">Sin
						seleccionar</option>

				</select> <label for="activitySelect">Actividad</label>
			</section>
			<section class="form-floating mb-3">
				<select class="form-select mb-3" id="classSelect">
					<option selected disabled value="Sin seleccionar">Sin
						seleccionar</option>

				</select> <label for="classSelect">Clase</label>
			</section>
			<section class="container  text-center">
				<h3>Información de la clase</h3>
				<div class="d-flex flex-row">

					<div id="classInfo" class="d-flex flex-column m-2 p-3"
						style="width: 45%;">
						<div class="mb-3 text-end d-flex flex-row">
							<label for="className" class="p-2 w-50">Nombre</label> <input
								type="text" id="className"
								class="form-control bg-light border-light p-2 w-50" readonly>
						</div>
						<div class="mb-3 text-end d-flex flex-row">
							<label for="classUrl" class="p-2 w-50">URL</label> <input
								type="text" id="classUrl"
								class="form-control bg-light border-light p-2 w-50" readonly>
						</div>
						<div class="mb-3 text-end d-flex flex-row">
							<label for="classUrl" class="p-2 w-50">Precio</label> <input
								type="text" id="classPrice"
								class="form-control bg-light border-light p-2 w-50" readonly>
						</div>
						<div class="mb-3 text-end d-flex flex-row">
							<label for="classUrl" class="p-2 w-50">Fecha de inicio</label> <input
								type="text" id="classDate"
								class="form-control bg-light border-light p-2 w-50" readonly>
						</div>
					</div>
					<div id="members" class="m-2 p-3" style="width: 45%;">
						<table id="membersTable"
							class="table table-responsive table-striped table-hover table-group-divider align-middle max-height-300px">
							<caption>Lista de miembros</caption>
							<thead>
								<tr>
									<th>Nombre</th>
									<th>Apellido</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody id="bodyMembersTable">
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</form>
	</main>

	<script type="module" src="javascript/classDictationConsultation.js"></script>
	<%@include file="footer.jsp"%>
</body>
</html>