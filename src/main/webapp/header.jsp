<%--suppress LossyEncoding --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/titlebar_icon_weightlifter.png" sizes="16x16">
<title>Entrenamos.uy</title>


<% 
	String userName = (String) session.getAttribute("userName"); 
	String userType = (String) session.getAttribute("userType");
%>
<!--
<p>El usuario es <%= userName %>.</p>
<p>De tipo <%= userType %>.</p>
-->

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid ms-3">
    <a class="navbar-brand" href="#">
      <img id="app-main-icon" src="${pageContext.request.contextPath}/assets/titlebar_icon_weightlifter.png" alt="Icono" style="max-height: 30px" />
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      	<%if (userName == null) { %>
	        <li class="nav-item">
	          <a class="nav-link" href="logIn.jsp">Iniciar sesion</a>
	        </li>
        <%} else { %>
        <li class="nav-item">
          <a class="nav-link" id="header-home" aria-current="page" href="init.jsp">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="btn nav-link" id="header-modify-user-data" aria-current="page" onclick="postToServlet('ModifyUserData');">Modificar informacion</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="header-ranking" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Rankings
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#" onclick="postToServlet('ClassDictationRanking');">Ranking de dictados de clases</a></li>
            <!--<li><a class="dropdown-item" href="#" onclick="postToServlet('SportActivitiesRanking');">Ranking de actividades deportivas</a></li> 
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li> -->
          </ul>
        </li>
	        <%if (userType.equals("P")) { %>
	        	<li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" id="header-ranking" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Rankings
		          </a>
		          <ul class="dropdown-menu">
		            <li><a class="dropdown-item" href="#" onclick="postToServlet('ClassDictationRanking');">Ranking de dictados de clases</a></li>
		            <!--<li><a class="dropdown-item" href="#" onclick="postToServlet('SportActivitiesRanking');">Ranking de actividades deportivas</a></li> 
		            <li><hr class="dropdown-divider"></li>
		            <li><a class="dropdown-item" href="#">Something else here</a></li> -->
		          </ul>
		        </li>
	        <%} %>
	         <%if (userType.equals("M")) { %>
				<li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="header-members" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Dropdown socios
		          </a>
		          <ul class="dropdown-menu">
			          <li><a class="dropdown-item" id="header-register-to-class" role="button" href="#" onclick="postToServlet('RegisterToClass');">Registrarse a dictado de clase</a></li>
		          </ul>
		        </li>
	        <%} %>
	        
	        <li class="nav-item">
	          <a class="nav-link" href="#" onclick="postToServlet('LogOut');">Cerrar sesion</a>
	        </li>
        <%} %>
        <li class="nav-item">
          <a class="nav-link disabled" aria-disabled="true">Ponele voluntad</a>
        </li>
      </ul>
      <!--
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form> -->
    </div>
  </div>
  <form id="servletForm"  method="post" style="display: none;">
	<input type="hidden" name="servletName" id="servletName" value="">
  </form>
      
      <script>
      	function setHeaderDisplay(){
      		const url = window.location.href
      		if(url.includes("init")){
    			document.getElementById("header-home").classList.add("active")
    		} else if(url.includes('logIn')){
    			document.getElementById("header-login").classList.add("active")
    		} else if(url.endsWith('RegisterToClass')){
	    		document.getElementById("header-members").classList.add("active")
	        } else if(url.endsWith('Ranking')){
	    		document.getElementById("header-ranking").classList.add("active")
     		} else if (url.endsWith('ModifyUserData')){
     			document.getElementById("header-modify-user-data").classList.add("active")
     		}
      	}
      	
      	setHeaderDisplay()
      	
		  function postToServlet(servletName) {
			// Setea el nombre del servlet en el campo oculto
			document.getElementById("servletName").value = servletName;
			// Envï¿½a el formulario
		    const form = document.getElementsByTagName('form');
		    form[0].action = servletName; // Configura el atributo action del formulario
		    form[0].submit();
		  }
	  </script>
</nav>