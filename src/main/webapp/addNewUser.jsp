<%@ page import="dataTypes.DtInstitute" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>Gimnasio | Agregar Socio</title>
<%@include file="header.jsp" %>

</head>
<body>
<%
  Map<String, DtInstitute> institutesList = (Map<String, DtInstitute>) request.getAttribute("institutesList");
%>
<h1 align="center">Agregar nuevo usuario</h1>
    <div class="container mt-5">
        <div class="border p-4">
          <form class="text-center" action="AddNewUser" method="post">
            <div class="mb-3 row">
              <label for="inputNickname" class="col-sm-4 col-form-label">Nickname</label>
              <div class="col-md-6">
                <input type="text" class="form-control form-control-sm" id="inputNickname" name="nickname">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputEmail" class="col-sm-4 col-form-label">Email</label>
              <div class="col-md-6">
                <input type="email" class="form-control form-control-sm" id="inputEmail" aria-describedby="emailHelp" name="email">
                <div id="emailHelp" class="form-text">No compartiremos tu informacion con nadie.</div>
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputName" class="col-sm-4 col-form-label">Name</label>
              <div class="col-md-6">
                <input type="text" class="form-control form-control-sm" id="inputName" name="name">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputLastName" class="col-sm-4 col-form-label">LastName</label>
              <div class="col-md-6">
                <input type="text" class="form-control form-control-sm" id="inputLastName" name="lastName">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputPassword" class="col-sm-4 col-form-label">Contraseña</label>
              <div class="col-md-6">
                <input type="password" class="form-control form-control-sm" id="inputPassword" name="password">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputConfirmPassword" class="col-sm-4 col-form-label">Confirmar Contraseña</label>
              <div class="col-md-6">
                <input type="password" class="form-control form-control-sm" id="inputConfirmPassword">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputFechaNacimiento" class="col-sm-4 col-form-label">Fecha de Nacimiento</label>
              <div class="col-md-6">
                <input type="date" class="form-control form-control-sm" id="inputFechaNacimiento" name="bornDate">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputEsProfesor" class="col-sm-4 col-form-label">¿Eres Profesor?</label>
              <div class="col-md-6">
                <div class="form-check">
                  <input type="checkbox" class="form-check-input" id="inputEsProfesor" name="isTeacher">
                </div>
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputDescripcion" class="col-sm-4 col-form-label">Descripción</label>
              <div class="col-md-6">
                <input type="text" class="form-control form-control-sm" id="inputDescripcion" name="description">
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputBiografia" class="col-sm-4 col-form-label">Biografía</label>
              <div class="col-md-6">
                <textarea class="form-control form-control-sm" id="inputBiografia" rows="3" name="biography"></textarea>
              </div>
            </div>
            <div class="mb-3 row">
              <label for="inputWebPage" class="col-sm-4 col-form-label">Página Web</label>
              <div class="col-md-6">
                <input type="url" class="form-control form-control-sm" id="inputWebPage" name="webPage">
              </div>
            </div>
            <div class="mb-3 row">
              <label class="col-sm-4 col-form-label" for="institute">Instituto</label>
              <div class="col-md-6">
                <select class="form-select form-control-sm" id="institute">
                  <option selected disabled value="Sin seleccionar">Sin seleccionar</option>
                  <% for (Map.Entry<String, DtInstitute> institute : institutesList.entrySet()) { %>
                  <option value="<%= institute.getKey() %>"><%= institute.getKey() %></option>
                  <% } %>
                </select>
              </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
          <h1>${newUserMessage}</h1>
        </div>
      </div>
    
      <script>
        const checkboxEsProfesor = document.getElementById('inputEsProfesor');
        const descripcionInput = document.getElementById('inputDescripcion');
        const biografiaTextarea = document.getElementById('inputBiografia');
        const webPageInput = document.getElementById('inputWebPage');
        const institutoSelect = document.getElementById('institute');
        const fechaNacimientoInput = document.getElementById('inputFechaNacimiento');
    
        // Función para habilitar o deshabilitar campos según el estado del checkbox
        function toggleCamposProfesor() {
          const estaSeleccionado = checkboxEsProfesor.checked;
    
          descripcionInput.disabled = !estaSeleccionado;
          biografiaTextarea.disabled = !estaSeleccionado;
          webPageInput.disabled = !estaSeleccionado;
          institutoSelect.disabled = !estaSeleccionado;
        }
    
        // Agregar un evento al checkbox para llamar a la función cuando cambie
        checkboxEsProfesor.addEventListener('change', toggleCamposProfesor);
    
        // Llamar a la función inicialmente para establecer el estado inicial
        toggleCamposProfesor();
      </script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<%@include file="footer.jsp" %>
</html>