package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import publishers.UserPublisher;
import publishers.DtUser;
import publishers.DtClass;
import publishers.UserPublisherServiceLocator;
import publishers.UserPublisherService;

import javax.xml.rpc.ServiceException;


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
		
		DtUser user = null;
		try {
			user = chooseUser((String) request.getSession().getAttribute("userName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if( request.getParameter("modifyUserExecute") != null) {
        	try {
        		String name = request.getHeader("name");
        		String lastName = request.getHeader("lastName");
        		String newPass = request.getHeader("newPassword");
        		String oldPass = request.getHeader("oldPassword");
        		String[] dateArray = request.getHeader("bornDate").split("-", 3);
        		
        		if (oldPass.isEmpty() || oldPass == null) {
        			newPass = user.getPassword();
        		} else {
        			boolean passwordCheck = oldPass.equals(user.getPassword());
        			
        			if(newPass == null || newPass.isEmpty() || !passwordCheck) {
        				throw new Exception("Alguno de los campos de contraseña es invalido, reviselos por favor");
        			}        			
        		}
        		
        		int year = Integer.parseInt(dateArray[0]);
        		int month = Integer.parseInt(dateArray[1]) - 1; // Subtract 1 because Calendar months are 0-based
        		int day = Integer.parseInt(dateArray[2]);

        		Calendar date = Calendar.getInstance();
        		date.set(Calendar.YEAR, year);
        		date.set(Calendar.MONTH, month);
        		date.set(Calendar.DAY_OF_MONTH, day);
        		DtUser newUserInfo = new DtUser(user.getNickname(), name, lastName, user.getEmail(), date, newPass);

        		this.updateUserInfo(newUserInfo);   
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
	
	private void updateUserInfo(DtUser userUpdated) throws Exception {
		try {
			UserPublisherService ups = new UserPublisherServiceLocator();
			UserPublisher up = ups.getUserPublisherPort();
			up.updateUserInfo(userUpdated);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
