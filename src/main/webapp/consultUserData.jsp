<%@page import="java.util.Date"%>
<%@page import="dataTypes.DtClass"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dataTypes.DtUser"%>
<%@page import="dataTypes.DtClass"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="header.jsp"%>
	
	<style>
	.user-basic-data {
		width: 100%;
		background-color: #fff;
    	padding: 1%;
    	border-radius: 20px;
    	border: .5px solid #ebebf9;
    	box-sizing: border-box;
    	box-shadow: 8px 8px 16px 4px rgba(133,139,146,.06);
	}
	
	.info-row {
	    display: flex;
    	justify-content: center;
    	align-items: center;
    	gap: 4%;
	}
	
	.class-select-wrapper {
	    display: flex;
    	flex-direction: column;
		width: 90%;
	}
	
	.related-classes-span {
		font-weight: bold;
		text-align: center; 
	}
	
	.no-classes-span {
		font-weight: bold;
		text-align: center;
		font-size: 20px; 
	}
	
	.select-and-reload-button-wrapper {
		display: flex;
		align-items: center;
		gap: 2%;
	}
	</style>
</head>
<body>
	<%
		DtUser userInfo = (DtUser) request.getAttribute("userInfo");
		Map<String,DtClass> classes = new TreeMap<String, DtClass>();
	
		if (request.getSession(false).getAttribute("userType") == "M") {
			classes = (Map<String, DtClass>) request.getAttribute("memberClasses");
		}
		else if (request.getSession(false).getAttribute("userType") == "P") {
			classes = (Map<String, DtClass>) request.getAttribute("professorClasses");
		}
	%>
	
	<main class="container mt-5">
	
	<!-- To show errors -->
	<div id="feedbackAlert" class="alert hide" role="alert"></div>
	
	<div class="user-basic-data">
	
		<h1 style="margin-bottom: 3%" align="center">Consultar informacion</h1>
		
		<div class="info-row">
			<!-- Nickname -->
			<div class="input-group mb-3">
	  			<span class="input-group-text" id="basic-addon2">Nickname</span>
	  			<input disabled type="text" id="nicknameInput" class="form-control" placeholder="Nickname" name="Nickname" value="<%= userInfo.getNickname() %>" />
			</div>
			
			<!-- Email -->
			<div class="input-group mb-3">
	  			<span class="input-group-text" id="basic-addon3">Email</span>
	  			<input disabled type="text" id="emailInput" class="form-control" placeholder="Email" name="Email" value="<%= userInfo.getEmail() %>"/>
			</div>
		</div>
		
		<div class="info-row"> 
			<!-- Name -->
			<div class="input-group mb-3">
	  			<span class="input-group-text" id="basic-addon2">Name</span>
	  			<input disabled type="text" id="nameInput" class="form-control" placeholder="Name" name="Name" value="<%= userInfo.getName() %>" />
			</div>
			
			<!-- Last name -->
			<div class="input-group mb-3">
	  			<span class="input-group-text" id="basic-addon2">Last name</span>
	  			<input disabled type="text" id="lastNameInput" class="form-control" placeholder="LastName" name="LastName" value="<%= userInfo.getLastName() %>" />
			</div>
		</div>
		
		<!-- Born date -->
		<div class="info-row">
			<div class="date-input-container">
				<div class="input-group mb-3">
	  				<span class="input-group-text" id="basic-addon6">Fecha de nacimiento</span>
	  				<input disabled style="border-radius: 5px; border: var(--bs-border-width) solid var(--bs-border-color); padding: 0 10px 0 10px;" id="bornDateInput" type="date" name="userBornDate" />
				</div>
			</div>
		</div>
		
		
		<!-- Related classes, only if there is at least one class -->
		<% if (!classes.isEmpty()) {%>
		<div class="info-row">
			<div class="mb-3 class-select-wrapper">
				<span class="related-classes-span">Clases relacionadas: </span>
				<div class="select-and-reload-button-wrapper">
					<select class="form-select" id="classSelect">
          				<option selected disabled value="Sin seleccionar">Sin seleccionar</option>
            				<% for (Map.Entry<String, DtClass> aClass : classes.entrySet()) { %>
              					<option value="<%= aClass.getKey() %>"><%= aClass.getKey() %></option>
            				<% } %>
        			</select>
        			<button onClick="restartUseCase();" id="reloadButton" disabled style="white-space: nowrap;" class="btn btn-primary">↻ Reload</button>
				</div>
        		
      		</div>
		</div>
		
		<div class="info-row">
			<!-- Show class related info -->
      		<table style="display: none;" id="classTable" class="table caption-top">
        		<caption style="text-align: center; font-weight:bold;">Información de la clase</caption>
        		<thead>
          			<tr>
            			<th scope="col">Nombre</th>
            			<th scope="col">url</th>
            			<th scope="col">Costo</th>
            			<th scope="col">Fecha de comienzo</th>
          			</tr>
        		</thead>
        		<tbody>
          			<tr>
            			<th scope="row" id="className"></th>
            			<td id="classUrl"></td>
            			<td id="classPrice"></td>
            			<td id="classDate"></td>
          			</tr>
        		</tbody>
      		</table>
		</div>
		
		<!-- If she/he's a professor, may want to check the activity as well. -->
		<% if (request.getSession(false).getAttribute("userType") == "P") { %>
			<div style="flex-direction: column;" class="info-row">
				<button onClick="showRelatedActivity();" id="showActivityButton" style="display: none;" class="btn btn-success"> Mostrar actividad deportiva relacionada</button>
				
				<!-- Show activity related info -->
      			<table id="activityTable" style="display: none;" class="table caption-top">
        			<caption style="text-align: center; font-weight:bold;">Información de la actividad</caption>
        				<thead>
          					<tr>
            					<th scope="col">Nombre</th>
            					<th scope="col">Descripcion</th>
            					<th scope="col">Costo</th>
            					<th scope="col">Duracion</th>
            					<th scope="col">Fecha de registro</th>
          					</tr>
        				</thead>
        				<tbody>
          					<tr>
            					<th scope="row" id="activityName"></th>
            					<td id="activityDescription"></td>
            					<td id="activityPrice"></td>
            					<td id="activityDuration"></td>
            					<td id="activityDate"></td>
          					</tr>
        				</tbody>
      			</table>
			</div>
			
			
		<% } %>
		
		<% } else { %>
		<div class="info-row"> 
			<span class="no-classes-span">No hay clases relacionadas para este usuario. </span>
		</div>
		<% } %>
		
		
	</div>
	</main>
</body>
<script>

	// Global variables for activity data (don't want to fetch twice)
	let currentActivity = {};

	// Init date (can't access java datatypes to script modules)
	document.getElementById('bornDateInput').value = <%= userInfo.getBornDate().getYear() %> + "<%= userInfo.getBornDate() %>".slice(4,10)
	document.getElementById('bornDateInput').max = new Date().toLocaleDateString('fr-ca')
	
	// Show alerts
	function displayAlert(message, alertClass) {
		const alert = document.getElementById('feedbackAlert')
		alert.innerHTML = `${message}\n<button id="alertDismiss" type="button" class="btn-close" aria-label="Close"></button>`
		alert.classList = 'alert alert-dismissible fade show ' + alertClass
	
		document.getElementById('alertDismiss').addEventListener('click', e => {
			alert.classList = 'alert alert-dismissible fade hide '
		})
	}
	
	function cleanTables() {
		let classTable = document.getElementById("classTable");
		let activityTable = document.getElementById("activityTable");
		// Class table
		if (classTable) classTable.style.display = "none";
		// Activity table
		if (activityTable) activityTable.style.display = "none";
	}
	
	function restartUseCase() {
		let showActivityButton = document.getElementById('showActivityButton')
		if (showActivityButton) showActivityButton.style.display = 'none';
		document.getElementById("classSelect").selectedIndex = 0;
		document.getElementById("reloadButton").disabled = true;
		cleanTables();
	}

	function showRelatedActivity() {
		// Show table
		document.getElementById("activityTable").style.display = "table";
		// Fulfill values
		document.getElementById('activityName').innerHTML = currentActivity.name
		document.getElementById('activityDescription').innerHTML = currentActivity.description
		document.getElementById('activityPrice').innerHTML = currentActivity.price
		document.getElementById('activityDuration').innerHTML = currentActivity.duration
		document.getElementById('activityDate').innerHTML = currentActivity.date
		// Disable button 
		document.getElementById('showActivityButton').disabled = true
	}

	function showRelatedClass(data) {
		// Show table
    	document.getElementById("classTable").style.display = "table"
       	// Fulfill values
		document.getElementById('className').innerHTML = data.className
		document.getElementById('classUrl').innerHTML = data.classUrl
		document.getElementById('classPrice').innerHTML = data.classPrice
		document.getElementById('classDate').innerHTML = data.classDate
	}
	
	// Class selected event
	document.getElementById('classSelect').addEventListener('change', (event) => {
		cleanTables();
		// Fetch class and activity info -> DISCLAIMER: cambiar el path al servlet segun la compu jiji (el nombre de mi carpeta es Tarea_2, soy Axel)
		fetch('http://localhost:8080/Tarea_2/ConsultUserData?chosenClass=' + event.target.value,
        	{	method: 'GET',
            	headers: {
                'Content-Type': 'application/json',
                action: 'CLASS'
            }}
    	).then((response) => {
        	if(!response.ok) {
				restartUseCase()
            	throw new Error(response.headers.get('error'))
        	}
        	return response.json()
    	}).then((data) => {
        	// Show class info
    		showRelatedClass(data)
			// Let user reload use case
			document.getElementById("reloadButton").disabled = false;
			// If professor, let him see the activity
			if (data.userType === "P") {
				// Let user see button to show activity
				document.getElementById('showActivityButton').style.display = 'block';
				document.getElementById('showActivityButton').disabled = false;
				// Global variable
				currentActivity = {
					name: data.activityName,
					date: data.activityDate,
					description: data.activityDescription,
					duration: data.activityDuration,
					price: data.activityPrice,
				}
			}	
    	}).catch((error) => {
        	displayAlert(error, 'alert-danger')
    	})
	})
	
	
	
	
</script>
<%@include file="footer.jsp" %>
</html>