<%@page import="java.util.Date"%>
<%@page import="dataTypes.DtClass"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dataTypes.DtUser"%>
<%@page import="dataTypes.DtInstitute"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Alta de dictado de clase</title>
		<%@include file="header.jsp"%>
		<style>
		
		.form-container {
			width: 100%;
			background-color: #fff;
    		padding: 1%;
    		border-radius: 20px;
    		border: .5px solid #ebebf9;
    		box-sizing: border-box;
    		box-shadow: 8px 8px 16px 4px rgba(133,139,146,.06);
    		display:flex;
    		flex-direction:column;
		}
		
		.info-row {
	    	display: flex;
    		justify-content: center;
    		align-items: center;
    		gap: 4%;
		}	
		</style>
		
		<%
			DtUser userInfo = (DtUser) request.getAttribute("userInfo");
			Map<String, DtInstitute> institutes = (Map<String,DtInstitute>) request.getAttribute("institutes");
		%>	
	</head>
<body>
	<main class="container mt-5"> 
	<!-- To show errors -->
	<div id="feedbackAlert" class="alert hide" role="alert"></div>
	<form class="form-container">
		<h1 style="margin-bottom: 3%" align="center">Alta de dictado de clase</h1>
		
		<div class="info-row">
			<section style="width: 90%" class="form-floating mb-3">
        		<select class="form-select" id="instituteSelect">
          			<option selected disabled value="Sin seleccionar">Sin seleccionar</option>
            		<% for (Map.Entry<String, DtInstitute> institute : institutes.entrySet()) { %>
              			<option value="<%= institute.getKey() %>"><%= institute.getKey() %></option>
            		<% } %>
        		</select>
        		<label for="instituteSelect">Instituto</label>
      		</section>
		</div>
		<div class="info-row">
			<section style="width: 90%" class="form-floating mb-3">
        		<select class="form-select mb-3" id="activitySelect">
          			<option selected disabled value="Sin seleccionar">Sin seleccionar</option>
        		</select>
        		<label for="activitySelect">Actividad</label>
      		</section>
		</div>
		
		<div style="width:90%; margin: auto auto;" class="info-row">
			<div class="input-group mb-3">
	  			<span class="input-group-text">Nombre</span>
	  			<input id="nameInput" type="text" class="form-control" placeholder="Nombre" name="Nombre" />
			</div>
			
			<div class="input-group mb-3">
	  			<span class="input-group-text">Url</span>
	  			<input id="urlInput" type="text" class="form-control" placeholder="Url (opcional)" name="Url" />
			</div>
			
			<div class="input-group mb-3">
	  			<span class="input-group-text">Fecha de inicio</span>
	  			<input style="border-radius: 5px; border: var(--bs-border-width) solid var(--bs-border-color); padding: 0 10px 0 10px;" id="initDateInput" type="date" name="initDate" />
			</div>
		</div>
		<button style="width:90%; margin:auto auto; margin-top: 1%" class="btn btn-success" id="submitButton" type="submit">Confirmar</button>	
	</form>
	</main>
</body>
	
<%@include file="footer.jsp" %>
<script>

function initDateInput() {
	const dateInput = document.getElementById("initDateInput");
	const today = new Date();
	const formattedDate = [today.getFullYear(), ('0' + (today.getMonth() + 1)).slice(-2), ('0' + today.getDate()).slice(-2)].join('-');
	dateInput.value = formattedDate;
}

function displayAlert(message, alertClass) {
	const alert = document.getElementById('feedbackAlert')
	alert.innerHTML = message + '\n<button id="alertDismiss" type="button" class="btn-close" aria-label="Close"></button>';
	alert.classList = 'alert alert-dismissible fade show ' + alertClass
	
	document.getElementById('alertDismiss').addEventListener('click', e => {
		alert.classList = 'alert alert-dismissible fade hide '
	})
}

function getInstituteValue(){
	return document.getElementById("instituteSelect").value
}

function getActivityValue(){
	return document.getElementById("activitySelect").value
}

function getClassName() {
	return document.getElementById('nameInput').value
}

function getClassUrl() {
	return document.getElementById('urlInput').value
}

function getClassInitDate() {
	return document.getElementById('initDateInput').value
}

function restartUseCase(){
	// Reset button
	const submitButton = document.getElementById('submitButton');
	submitButton.innerHTML = 'Confirmar';
	submitButton.disabled = false;
	// Reset select dropdowns
	document.getElementById("instituteSelect").value = 'Sin seleccionar';
	document.getElementById("activitySelect").innerHTML = '<option selected disabled>Sin seleccionar</option>';
	// Reset inputs
	document.getElementById("nameInput").value = '';
	document.getElementById("urlInput").value = '';
	// Reset date with today date
	initDateInput();
}

// Fulfill activities select with the institute activities
document.getElementById('instituteSelect').addEventListener('change', (event) => {
	document.getElementById("activitySelect").value = 'Sin seleccionar'
	fetch('http://localhost:8080/Tarea_2/AddClass?chosenInstitute=' + event.target.value,
        {method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                action: 'INSTITUTE'
            }}
    ).then((response) => {
        if(!response.ok) {
			restartUseCase()
            throw new Error(response.headers.get('error'))
        }
        return response.json()
    }).then((data) => {
		if(data === null || Object.keys(data).length === 0) {
			displayAlert('El instituto indicado no tiene actividades asociadas', 'alert-info')
		}
		// Restart activity options 
		document.getElementById('activitySelect').innerHTML = "\n<option selected disabled value=\"Sin seleccionar\">Sin seleccionar</option>"

		// Add all activities as options
		let htmlToAppend = '';
        for(const activity in data) {
			htmlToAppend += '\n<option>' + activity + '</option>';
		}
		document.getElementById('activitySelect').innerHTML += htmlToAppend;
    }).catch((error) => {
		displayAlert(error, 'alert-danger')
    })
})

document.getElementById('submitButton').addEventListener('click', (event)=> {
	event.stopPropagation();
	event.preventDefault();
	event.target.disabled = true
	document.getElementById('submitButton').innerHTML = `<span class="spinner-border spinner-border-sm" aria-hidden="true"></span> <span role="status">Cargando...</span>`

  	// Values
  	const institute = getInstituteValue();
  	const activity = getActivityValue();
  	const className = getClassName();
  	const url = getClassUrl();
  	const initDate = getClassInitDate();

  	// Prevent empty values
  	if ((!institute || institute == 'Sin seleccionar') || (!activity ||  activity == 'Sin seleccionar') || !className || !initDate) {
  		displayAlert('Por favor, rellene todos los campos.', 'alert-danger');
  		document.getElementById('submitButton').innerHTML = 'Confirmar';
  		document.getElementById('submitButton').disabled = false;
  		return;
  	}

  	// Remember, change path as needed
  	fetch('http://localhost:8080/Tarea_2/AddClass', {
  		method: 'POST',
  		headers: {
 			institute: institute,
  			activity: activity,
  			className: 	className,
  			url: url,
  			initDate: initDate,
  		}
  		}).then((response) => {
  			if(!response.ok) {
  	  			const error = response.headers.get('error');
  	  			if (error == 'El nombre de la clase ya esta en uso.') {
  	  	  			// Just restart button
  	  				document.getElementById('submitButton').innerHTML = 'Confirmar';
  	    			document.getElementById('submitButton').disabled = false;
  	  	  		}else {
  	  	  			restartUseCase();
  	  	  	  	}
  				throw new Error(error);
  			}
  			displayAlert("La clase ha sido creada satisfactoriamente!", "alert-success");
  			restartUseCase();
  		}).catch((error) => {
  			displayAlert(error, 'alert-danger');
  		})
})


initDateInput();

</script>
</html>