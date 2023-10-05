package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		if(request.getSession(false) == null) { 
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    	response.setHeader("error", "El usuario no inicio sesion");
	        response.getWriter().close();
	        return;
		}
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		UserInterface uc = controllerFactory.getUserInterface();
		DtUser user = uc.chooseUser((String) request.getSession().getAttribute("userName"));
		if( request.getParameter("modifyUserExecute") != null) {
        	try {
        		String name = request.getHeader("name");
        		String lastName = request.getHeader("lastName");
        		String newPass = request.getHeader("newPassword");
        		String oldPass = request.getHeader("oldPassword");
        		String[] dateArray = request.getHeader("bornDate").split("-", 3);
        		
        		boolean passwordCheck = oldPass.equals(uc.chooseUser(user.getNickname()).getPassword());
        		
        		if(newPass == null || newPass.isEmpty() || !passwordCheck) {
        			throw new Exception("Alguno de los campos de contraseña es invalido, reviselos por favor");
        		}

        		Date date = new Date(Integer.parseInt(dateArray[0]) , Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
        		DtUser newUserInfo = new DtUser(user.getNickname(), name, lastName, user.getEmail(), date, newPass);

        		uc.updateUserInfo(newUserInfo);   
        		response.setStatus(200);
        		response.getWriter().close();
        	} catch (Exception e) {
        		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	    	response.setHeader("error", e.getMessage());
    	        response.getWriter().close();
        	}
		} else {
			request.setAttribute("userInfo", user);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/modifyUserData.jsp");
			rd.forward(request,response);			
		}
	}

}
