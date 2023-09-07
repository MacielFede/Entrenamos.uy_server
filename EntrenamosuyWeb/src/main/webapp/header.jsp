<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Entrenamos uy</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logIn.jsp">Iniciar sesión</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Rankings
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#" onclick="postToServlet('ClassDictationRanking');">Ranking de dictados de clases</a></li>
            <!--<li><a class="dropdown-item" href="#" onclick="postToServlet('SportActivitiesRanking');">Ranking de actividades deportivas</a></li> 
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li> -->
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" aria-disabled="true">Ponele voluntad</a>
        </li>
      </ul>
      <!--
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form> -->
      <form id="rankingForm"  method="post" style="display: none;">
	  	<input type="hidden" name="servletName" id="servletName" value="">
      </form>
      <script>
		  function postToServlet(servletName) {
			// Setea el nombre del servlet en el campo oculto
			document.getElementById("servletName").value = servletName;
			// Envía el formulario
		    var form = document.getElementById("rankingForm");
		    form.action = servletName; // Configura el atributo action del formulario
		    form.submit();
		  }
	  </script>
    </div>
  </div>
</nav>