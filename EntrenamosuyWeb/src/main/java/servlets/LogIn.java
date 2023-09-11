package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import dataTypes.DtMember;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.UserInterface;
/**
 * Servlet implementation class LogIn
 */
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = "";
		String userType = "";
	    HttpSession session = request.getSession(false);  
	    //HttpSession session = request.getSession(); 
		//session.setAttribute("userName", userName);
	    
	    if (session != null) {
	        userName = (String) session.getAttribute("userName");
	        userType = (String) session.getAttribute("userType");
	    }

	    //session.setAttribute("userName", userName);
	    request.setAttribute("userName", userName);
	    request.setAttribute("userType", userType);
	    response.sendRedirect("init.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "/index.jsp";
		
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		UserInterface ic = controllerFactory.getUserInterface();
		String userName = (String) request.getParameter("inputUserName");
		DtUser user = null;
		
		if(userName != null) {
			user = ic.chooseUser(userName);
		} else {
			request.setAttribute("logInErr", "Ingrese datos válidos");
			redirect = "/logIn.jsp";
		}
		
		boolean authUser = (user != null);
		if (authUser) {
			String userType = "P";
			
			if (user instanceof DtMember) {
				userType = "S";
			}
			
		    HttpSession session = request.getSession(); 
			session.setAttribute("userName", userName);
			session.setAttribute("userType", userType);
		} else {
			request.setAttribute("logInErr", "Usuario o contraseña incorrectos");
			redirect = "/logIn.jsp";
		}
		
		//request.setAttribute("logInErr", userName);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(redirect);
		rd.forward(request,response);
		//response.sendRedirect(redirect);
	}

}
