<%@page import="java.util.Date"%>
<%@page import="dataTypes.DtClass"%>
<%@page import="java.util.List"%>
<%@ page import="dataTypes.DtUser" %>

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
	  		<span class="input-group-text" id="basic-addon1">Nickname</span>
	  		<input disabled type="text" id="nicknameInput" class="form-control" placeholder="Nickname" name="Nickname" value="<%= userInfo.getNickname() %>" />
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Email</span>
	  		<input disabled type="text" id="emailInput" class="form-control" placeholder="Email" name="Email" value="<%= userInfo.getEmail() %>"/>
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Nombre</span>
	  		<input id="nameInput" type="text" class="form-control" placeholder="Nombre" name="Nombre" value="<%= userInfo.getName() %>" />
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Apellido</span>
	  		<input id="lastNameInput" type="text" class="form-control" placeholder="Apellido" name="Apellido" value="<%= userInfo.getLastName() %>"/>
		</div>
		
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="basic-addon1">Fecha de nacimiento</span>
	  		<input style="border-radius: 5px; border: var(--bs-border-width) solid var(--bs-border-color); padding: 0 10px 0 10px;"
	  			id="bornDateInput" type="date" name="userBornDate" />
		</div>
		<button class="btn btn-success" id="submitButton" type="submit">Guardar cambios</button>	

	</main>
	
	<script>
		document.getElementById('bornDateInput').value = <%= userInfo.getBornDate().getYear() %> + "<%= userInfo.getBornDate() %>".slice(4,10)
		document.getElementById('bornDateInput').max = new Date().toLocaleDateString('fr-ca')
		
		function displayAlert(message, alertClass){
			const alert = document.getElementById('feedbackAlert')
			alert.innerHTML = message + `\n<button id="alertDismiss" type="button" class="btn-close" aria-label="Close"></button>`
			alert.classList = 'alert alert-dismissible fade show ' + alertClass
			
			document.getElementById('alertDismiss').addEventListener('click', e => {
				alert.classList = 'alert alert-dismissible fade hide '
			})
		}
		
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
		  								
			if(!date || nickname !== userNickname || email !== userEmail){
				displayAlert("El nickname y el email no se pueden modificar, ademas la fecha no puede estar vacia", "alert-danger")
			} else {
				fetch("http://localhost:8080/EntrenamosuyWeb/ModifyUserData?modifyUserExecute=ejecuta", {
					method: 'POST',
					headers: {
						 bornDate: date,
						 name: document.getElementById("nameInput").value,
						 lastName: document.getElementById("lastNameInput").value
					 }
				}).then((response) => {
					if(!response.ok) {
			            throw response.headers.get('error')
			        }
					displayAlert("Se modifico la informacion satisfactoriamente!", "alert-success")
					document.getElementById('submitButton').innerHTML = 'Guardar cambios'
					event.target.disabled = false
				}).catch((e) => {
					console.log(e)
					displayAlert(e, "alert-danger")
					event.target.innerHTML = 'Guardar cambios'
					event.target.disabled = false
				})
			}
		})
				
	</script>
</body>
<%@include file="footer.jsp" %>
</html>