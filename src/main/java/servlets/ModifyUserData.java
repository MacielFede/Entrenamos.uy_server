package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dataTypes.DtClass;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;


public class ModifyUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyUserData() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		if(request.getSession() == null) { 
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    	response.setHeader("error", "El usuario no inicio sesion");
	        response.getWriter().close();
		}
		if( la query trae algo, hago el post de la nueva info) {
			
		} else {
			ControllerFactory controllerFactory = ControllerFactory.getInstance();
			UserInterface uc = controllerFactory.getUserInterface();
			
			DtUser user = uc.chooseUser((String) request.getSession().getAttribute("userName"));
			request.setAttribute("userInfo", user);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/modifyUserData.jsp");
			rd.forward(request,response);			
		}
	}

}
