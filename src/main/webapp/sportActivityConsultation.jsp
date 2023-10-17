<%@page import="dataTypes.DtClass"%>
<%@page import="java.util.List"%>
<%@ page import="dataTypes.DtInstitute" %>
<%@ page import="java.util.Map" %>
<%@ page import="dataTypes.DtActivity" %>
<%@ page import="com.google.gson.Gson" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Consulta de actividad deportiva</title>
	<%@include file="header.jsp"%>
</head>
<body>
<%
	Map<String, DtInstitute> institutesList = (Map<String, DtInstitute>) request.getAttribute("institutesList");
	List<DtClass> classes = (List<DtClass>) request.getAttribute("classes");
%>
<h1 align="center">Consulta de actividad deportiva</h1>
<main class="container">
	<select class="form-select form-control-sm" id="institute">
		<option selected disabled value="Sin seleccionar">Sin seleccionar</option>
		<%
			if (institutesList != null) {
				for (Map.Entry<String, DtInstitute> institute : institutesList.entrySet()) {
		%>
		<option value="<%= institute.getKey() %>"><%= institute.getKey() %></option>
		<%
				}
			}
		%>
	</select>
	<select class="form-select form-control-sm" id="activitiesSelect">
		<option selected disabled value="Sin seleccionar">Sin seleccionar</option>
	</select>

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
			if (classes != null) {
				int i = 1;
				for (DtClass dt : classes) {
		%>
		<tr>
			<th scope="row"><%=i%></th>
			<td><%=dt.getName()%></td>
			<td><%=dt.getDateAndTime() != null ? dt.getDateAndTime().toString() : ""%></td>
			<td><%=dt.getUrl()%></td>
			<td><%=dt.getEnrollmentsQuantity()%></td>
		</tr>
		<%
					i++;
				}
			}
		%>
		</tbody>
	</table>
</main>
<script>
	const instituteSelect = document.getElementById("institute");
	const activitiesSelect = document.getElementById("activitiesSelect");

	instituteSelect.addEventListener("change", async () => {
		const selectedInstitute = instituteSelect.value;
		if (selectedInstitute !== "Sin seleccionar") {
			try {
				const response = await fetch("SportActivityConsultation", {
					method: "POST",
					headers: {
						"Content-Type": "application/x-www-form-urlencoded",
						"institute": selectedInstitute
					},
				});

				if (response.ok) {
					const jsonData = await response.json(); // Analiza la respuesta JSON
					// console.log(jsonData); // Muestra el contenido del JSON en la consola
					activitiesSelect.innerHTML = "<option selected disabled value='Sin seleccionar'>Sin seleccionar</option>";
					for (const key in jsonData) {
						if (jsonData.hasOwnProperty(key)) {
							const activity = jsonData[key];
							const option = document.createElement("option");
							option.value = key;
							option.textContent = activity.name;
							activitiesSelect.appendChild(option);
						}
					}
				} else {
					console.error("Error en la solicitud POST");
				}
			} catch (error) {
				console.error("Error en la solicitud POST: " + error);
			}
		}
	});

	// activitiesSelect.addEventListener("change", async () => {
	// 	const activity = activitiesSelect.value;
	// 	if (activity !== "Sin seleccionar") {
	// 		try {
	// 			const response = await fetch("SportActivityConsultation", {
	// 				method: "POST",
	// 				headers: {
	// 					"Content-Type": "application/x-www-form-urlencoded",
	// 					"institute": instituteSelect.value,
	// 					"activity": activity
	// 				},
	// 			});
	//
	// 			if (response.ok) {
	// 				const jsonData = await response.json();
	// 				const tableBody = document.querySelector(".table tbody");
	//
	// 				// Limpiar el contenido existente en la tabla
	// 				while (tableBody.firstChild) {
	// 					tableBody.removeChild(tableBody.firstChild);
	// 				}
	//
	// 				let i = 1;
	// 				for (const key in jsonData) {
	// 					if (jsonData.hasOwnProperty(key)) {
	// 						const dt = jsonData[key];
	//
	// 						const row = document.createElement("tr");
	// 						const th = document.createElement("th");
	// 						th.setAttribute("scope", "row");
	// 						th.textContent = i;
	// 						row.appendChild(th);
	//
	// 						const tdName = document.createElement("td");
	// 						tdName.textContent = dt.name;
	// 						row.appendChild(tdName);
	//
	// 						const tdDateAndTime = document.createElement("td");
	// 						tdDateAndTime.textContent = dt.dateAndTime ? dt.dateAndTime : "";
	// 						row.appendChild(tdDateAndTime);
	//
	// 						const tdUrl = document.createElement("td");
	// 						tdUrl.textContent = dt.url;
	// 						row.appendChild(tdUrl);
	//
	// 						const tdEnrollmentsQuantity = document.createElement("td");
	// 						tdEnrollmentsQuantity.textContent = dt.enrollmentsQuantity;
	// 						row.appendChild(tdEnrollmentsQuantity);
	//
	// 						tableBody.appendChild(row);
	// 						i++;
	// 					}
	// 				}
	// 			} else {
	// 				console.error("Error en la solicitud POST");
	// 			}
	// 		} catch (error) {
	// 			console.error("Error en la solicitud POST: " + error);
	// 		}
	// 	}
	// });
	// Crear una referencia a la tabla y su cuerpo
	const table = document.querySelector(".table");
	const tableBody = table.querySelector("tbody");

	activitiesSelect.addEventListener("change", async () => {
		const activity = activitiesSelect.value;
		if (activity !== "Sin seleccionar") {
			try {
				const response = await fetch("SportActivityConsultation", {
					method: "POST",
					headers: {
						"Content-Type": "application/x-www-form-urlencoded",
						"institute": instituteSelect.value,
						"activity": activity
					},
				});

				if (response.ok) {
					const jsonData = await response.json();

					// Limpiar el contenido existente en la tabla
					while (tableBody.firstChild) {
						tableBody.removeChild(tableBody.firstChild);
					}

					let i = 1;
					for (const key in jsonData) {
						if (jsonData.hasOwnProperty(key)) {
							const dt = jsonData[key];

							const row = document.createElement("tr");
							const th = document.createElement("th");
							th.setAttribute("scope", "row");
							th.textContent = i;
							row.appendChild(th);

							const tdName = document.createElement("td");
							tdName.textContent = dt.name;
							row.appendChild(tdName);

							const tdDateAndTime = document.createElement("td");
							tdDateAndTime.textContent = dt.dateAndTime ? dt.dateAndTime : "";
							row.appendChild(tdDateAndTime);

							const tdUrl = document.createElement("td");
							tdUrl.textContent = dt.url;
							row.appendChild(tdUrl);

							const tdEnrollmentsQuantity = document.createElement("td");
							tdEnrollmentsQuantity.textContent = dt.enrollmentsQuantity;
							row.appendChild(tdEnrollmentsQuantity);

							tableBody.appendChild(row);
							i++;
						}
					}
				} else {
					console.error("Error en la solicitud POST");
				}
			} catch (error) {
				console.error("Error en la solicitud POST: " + error);
			}
		}
	});


</script>
</body>
<%@include file="footer.jsp"%>
</html>
