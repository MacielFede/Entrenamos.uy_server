package servlets;


import dataTypes.DtClass;
import dataTypes.DtInstitute;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class RegisterToClass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, DtInstitute> institutesCache;
    
    public RegisterToClass() {
        super();
    }
    
    private void sendBadResponse(HttpServletResponse res, String message) throws IOException {
    	res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	res.setHeader("error", message);
        res.getWriter().close();
    }
    
    private void updateIC() {
    	InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
        institutesCache = ic.listSportInstitutes();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String institute = request.getParameter("chosenInstitute");
        String activity = request.getParameter("chosenActivity");
        String aClass = request.getParameter("chosenClass");
        String action = request.getHeader("action");
        
        // Create the Gson handler and the string to return
		Gson gson = new Gson();
        String jsonToReturn = "";
        
        // Depending on the parameter sent by the client I know what to return
        try {
        	switch(action) {
        	case "INSTITUTE" -> {
        		jsonToReturn = gson.toJson(institutesCache.get(institute).getActivities());            	        	
        	}
        	case "ACTIVITY" -> {
        		// Fill the array with the activity classes
        		jsonToReturn = gson.toJson(institutesCache.get(institute).getActivities().get(activity).getClasses());
        	}
        	case "CLASS" -> {
        		
        		// Fill the return object with the class information
        		Map<String, String> classInformation = new HashMap<>();
        		DtClass theClass = institutesCache.get(institute).getActivities().get(activity).getClasses().get(aClass);
        		
        		classInformation.put("name", theClass.getName());
        		classInformation.put("url", theClass.getUrl());
        		classInformation.put("price", institutesCache.get(institute).getActivities().get(activity).getPrice().toString());
        		classInformation.put("date", theClass.getDateAndTime().toString());
        		jsonToReturn = gson.toJson(classInformation);
        	}
        	default -> {
        		this.updateIC();
        		this.sendBadResponse(response, "No se encontraron parametros validos en la petición");
        	}
        	}
        } catch (Exception e) {
        	this.sendBadResponse(response, "La informacion estaba desactualizada, intentalo de nuevo");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonToReturn);
        response.getWriter().close();
	}

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getSession() == null || request.getSession().getAttribute("userType") != "M") {
    		this.sendBadResponse(response, "El usuario no inicio sesión o es un profesor");
    		return;
    	}
    	String chosenClass = request.getHeader("chosenClass");
        if (chosenClass == null){
        	this.updateIC();
        	RequestDispatcher rd;
            request.setAttribute("institutes", institutesCache);
            rd = request.getRequestDispatcher("/registrationToClass.jsp");
            rd.forward(request,response);
        } else { // The user has chosen a class and clicked the confirm button
        	String institute = request.getHeader("institute");
        	String activity = request.getHeader("activity");
        	UserInterface uc = ControllerFactory.getInstance().getUserInterface();
        	try {
        		DtUser user = uc.chooseUser( (String) request.getSession().getAttribute("userName"));
        		uc.addEnrollment(chosenClass, user, institutesCache.get(institute).getActivities().get(activity).getPrice());
        		
        		response.setStatus(200);
        		response.getWriter().write("Se completó la insctipción satisfactoriamente!");
        		response.getWriter().close();
        	} catch (Exception e) {
        		this.sendBadResponse(response, e.getMessage());
        	}
        }
    }
}
