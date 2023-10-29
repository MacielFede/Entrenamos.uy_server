<%@ page import="java.util.Map" %>
<%@ page import="publishers.DtInstitute" %>
<%@ page import="publishers.DtActivity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="header.jsp"%>
</head>
<body>
    <%
      Map<String, DtInstitute> institutes = (Map<String,DtInstitute>) request.getAttribute("institutes");
    %>
  <main class="container mt-5">
  	<div id="feedbackAlert" class="alert hide" role="alert">
	</div>
    <h1 class="mb-4">Registrese a una clase!</h1>
    <form>
      <section class="form-floating mb-3">
        <select class="form-select" id="instituteSelect">
          <option selected disabled value="Sin seleccionar">Sin seleccionar</option>
            <% for (Map.Entry<String, DtInstitute> institute : institutes.entrySet()) { %>
              <option value="<%= institute.getKey() %>"><%= institute.getKey() %></option>
            <% } %>
        </select>
        <label for="instituteSelect">Instituto</label>
      </section>
      <section class="form-floating mb-3">
        <select class="form-select mb-3" id="activitySelect">
          <option selected disabled value="Sin seleccionar">Sin seleccionar</option>

        </select>
        <label for="activitySelect">Actividad</label>
      </section>
      <section class="form-floating mb-3">
        <select class="form-select mb-3" id="classSelect">
          <option selected disabled value="Sin seleccionar">Sin seleccionar</option>

        </select>
        <label for="classSelect">Clase</label>
      </section>

      <table class="table caption-top">
        <caption>Información de la clase</caption>
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
      <button type="button" id="confirmButon" class="btn btn-success" disabled>Confirmar registro</button>
    </form>
  </main>
  <script type="module" src="javascript/registrationToClass.js"></script>
  <%@include file="footer.jsp"%>
</body>
</html>
