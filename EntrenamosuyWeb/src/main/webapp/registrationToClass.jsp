<%@ page import="java.util.Map" %>
<%@ page import="dataTypes.DtInstitute" %><%--
  Created by IntelliJ IDEA.
  User: Fede Maciel
  Date: 7/9/2023
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
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
    <h1 class="mb-4">Registrese a una clase!</h1>
    <form>
      <section class="form-floating mb-3">
        <select class="form-select" id="instituteSelect" onchange="setSelectedInstitute(this.value)">
          <option selected disabled>Sin seleccionar</option>
            <% for (Map.Entry<String, DtInstitute> institute : institutes.entrySet()) { %>
              <option value="<%= institute.getKey() %>"><%= institute.getKey() %></option>
            <% } %>
        </select>
        <label for="instituteSelect">Instituto</label>
      </section>
      <section class="form-floating mb-3">
        <select class="form-select mb-3" id="activitySelect">
          <option selected disabled>Sin seleccionar</option>

        </select>
        <label for="activitySelect">Actividad</label>
      </section>
      <section class="form-floating mb-3">
        <select class="form-select mb-3" id="classSelect">
          <option selected disabled>Sin seleccionar</option>

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
      <button type="submit" class="btn btn-success" disabled>Confirmar registro</button>
    </form>
  </main>
  <script>
  function setSelectedInstitute(institutes){
	    console.log(institutes)
	    const instit = '<%= institutes %>'
	    console.log(instit)
	    // let htmlToAppend = ''
	    // for (let activity ){
	    //
	    // }
	}
  </script>
</body>
</html>
