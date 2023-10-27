<%@page import="java.util.Date"%>
<%@page import="publishers.DtClass"%>
<%@page import="java.util.List"%>
<%@ page import="publishers.DtUser" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp" %>
</head>
<body>
	<%
		DtUser userInfo = (DtUser) request.getAttribute("userInfo");
	%>
	<main class="container">
	<div id="feedbackAlert" class="alert hide" style="margin-top: 5px;" role="alert">
	</div>
	<h1 align="center">Modificar informacion</h1>

		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon2">Nickname</span>
	  		<input disabled type="text" id="nicknameInput" class="form-control" placeholder="Nickname" name="Nickname" value="<%= userInfo.getNickname() %>" />
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon3">Email</span>
	  		<input disabled type="text" id="emailInput" class="form-control" placeholder="Email" name="Email" value="<%= userInfo.getEmail() %>"/>
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon4">Nombre</span>
	  		<input id="nameInput" type="text" class="form-control" placeholder="Nombre" name="Nombre" value="<%= userInfo.getName() %>" />
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon5">Apellido</span>
	  		<input id="lastNameInput" type="text" class="form-control" placeholder="Apellido" name="Apellido" value="<%= userInfo.getLastName() %>"/>
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon6">Fecha de nacimiento</span>
	  		<input style="border-radius: 5px; border: var(--bs-border-width) solid var(--bs-border-color); padding: 0 10px 0 10px;"
	  			id="bornDateInput" type="date" name="userBornDate" />
		</div>
		<fieldset class="form-group">
	  		<legend>Cambiar contraseña</legend>
	  		<div class="input-group mb-3">
		  		<span class="input-group-text" id="basic-addon7">Contraseña actual</span>
		  		<input class="form-control" id="currentPassword" type="password" name="currentPassword" />
			</div>
			<div class="input-group mb-3">
		  		<span class="input-group-text" id="basic-addon8">Nueva contraseña</span>
		  		<input class="form-control" id="newPassword" type="password" name="newPassword" />
			</div>
		</fieldset>
		<style>
		fieldset {
		    border: 2px groove threedface;
		    border-radius: 5px;
		    padding: 5px 20px;
		    margin-bottom: 10px;
		    
		}
</style>
		<button class="btn btn-success" id="submitButton" type="submit">Guardar cambios</button>	

	</main>
	
	<script>
		document.getElementById('bornDateInput').value = <%= userInfo.getBornDate().getTime().getYear() %> + "<%= userInfo.getBornDate() %>".slice(4,10)
		document.getElementById('bornDateInput').max = new Date().toLocaleDateString('fr-ca')
		
		function displayAlert(message, alertClass){
			const alert = document.getElementById('feedbackAlert')
			alert.innerHTML = message + `\n<button id="alertDismiss" type="button" class="btn-close" aria-label="Close"></button>`
			alert.classList = 'alert alert-dismissible fade show ' + alertClass
			
			document.getElementById('alertDismiss').addEventListener('click', e => {
				alert.classList = 'alert alert-dismissible fade hide '
			})
		}
		window.addEventListener('load', () => {
			document.getElementById('submitButton').addEventListener('click', (event) => {
				event.target.disabled = true
				document.getElementById('submitButton').innerHTML = `<span class="spinner-border spinner-border-sm" aria-hidden="true"></span>
			  								<span role="status">Cargando...</span>`
				event.stopPropagation()
				event.preventDefault()			
				const nickname = document.getElementById("nicknameInput").value
				const email = document.getElementById("emailInput").value
				const date = document.getElementById("bornDateInput").value
				const userNickname = "<%= userInfo.getNickname() %>"
			  	const userEmail = "<%= userInfo.getEmail() %>"
			  	const newPass = document.getElementById("newPassword").value
			  	const oldPass = document.getElementById("currentPassword").value
			  								
				if(!date || nickname !== userNickname || email !== userEmail){
					displayAlert("El nickname y el email no se pueden modificar, ademas la fecha no puede estar vacia", "alert-danger")
					event.target.innerHTML = 'Guardar cambios'
					event.target.disabled = false
				} else {
					fetch("http://localhost:8080/Entrenamos.uy_server/ModifyUserData?modifyUserExecute=ejecuta", {
						method: 'POST',
						headers: {
							 bornDate: date,
							 name: document.getElementById("nameInput").value,
							 lastName: document.getElementById("lastNameInput").value,
							 newPassword: newPass,
							 oldPassword: oldPass
						 }
					}).then((response) => {
						if(!response.ok) {
				            throw response.headers.get('error')
				        }
						displayAlert("Se modifico la informacion satisfactoriamente!", "alert-success")
						document.getElementById('submitButton').innerHTML = 'Guardar cambios'
						event.target.disabled = false
						document.getElementById("newPassword").value = ""
							document.getElementById("currentPassword").value = ""
					}).catch((e) => {
						displayAlert(e, "alert-danger")
						event.target.innerHTML = 'Guardar cambios'
						event.target.disabled = false
					})
				}
			})
		})
		
				
	</script>
</body>
<%@include file="footer.jsp" %>
</html>