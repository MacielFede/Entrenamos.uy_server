package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publishers.UserPublisher;
import publishers.UserPublisherService;
import publishers.UserPublisherServiceLocator;

import publishers.DtUser;
import publishers.DtMember;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

/*
import dataTypes.DtMember;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.UserInterface;
*/
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
		
		/* Ya no se usa el .jar manito
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		UserInterface ic = controllerFactory.getUserInterface();
		*/
		String userName = (String) request.getParameter("inputUserName");
		String password = (String) request.getParameter("inputPassword");
		publishers.DtUser user = null;
		boolean logErr = (userName == null);
		
		if(!logErr) {
			try {
				user = chooseUser(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			logErr = !(user != null && user.getPassword().equals(password));
		} else {
			request.setAttribute("logInErr", "Ingrese datos válidos");
			redirect = "/logIn.jsp";
		}
		
		if (!logErr) {
			String userType = "P";
			
			if (user instanceof DtMember) {
				userType = "M";
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
	
	private DtUser chooseUser(String userName) throws Exception {
		try {
			UserPublisherService ups = new UserPublisherServiceLocator();
			UserPublisher up = ups.getUserPublisherPort();
			return up.chooseUser(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;	
	}

}
