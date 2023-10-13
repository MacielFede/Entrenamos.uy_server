package servlets;

import dataTypes.DtInstitute;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import exceptions.AtributeAlreadyExists;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Servlet implementation class AddNewUser
 */
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false) == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setHeader("error", "El usuario no inicio sesion");
			response.getWriter().close();
			return;
		}
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		UserInterface uc = controllerFactory.getUserInterface();

		String nickname = (String) request.getParameter("nickname");
		String email = (String) request.getParameter("email");
		InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();

		Map<String, DtInstitute> institutesList = ic.listSportInstitutes();
		if((nickname != null && email != null) && (nickname != "" && email != "")) {
			try {
				if (uc.existsEmail(email)) {
					throw new AtributeAlreadyExists("email", email);
				}else if (uc.existsNickname(nickname)){
					throw new AtributeAlreadyExists("nickname", nickname);
				}else{
					String name = (String) request.getParameter("name");
					String lastName = (String) request.getParameter("lastName");
					Date bornDate = java.sql.Date.valueOf(request.getParameter("bornDate"));
					String password = (String) request.getParameter("password");
					String institute = (String) request.getParameter("institute");
					boolean isTeacher = Boolean.parseBoolean(request.getParameter("isTeacher"));
					if(isTeacher){
						String description = (String) request.getParameter("description");
						String webPage = (String) request.getParameter("webPage");
						String biography = (String) request.getParameter("biography");
						uc.newUser(new DtProfessor(description, biography, webPage, nickname, name, lastName, email, bornDate, password), institute);
					}else {
						uc.newUser(new DtUser(nickname, name, lastName, email, bornDate, password), institute);
					}
					request.setAttribute("newUserMessage", "Usuario creado con exito");
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/addNewUser.jsp");
					rd.forward(request,response);
				}

			}catch (Exception e) {
				// ==================================================
				// Si se hace debugg, la excepcion se muestra aqui.
				// ==================================================

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.setHeader("error", e.getMessage());
				response.getWriter().close();
			}
		} else {
			request.setAttribute("institutesList", institutesList);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/addNewUser.jsp");
			rd.forward(request,response);
		}
	}

}
