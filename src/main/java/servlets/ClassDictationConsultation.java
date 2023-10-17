package servlets;


import dataTypes.DtClass;
import dataTypes.DtEnrollment;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class ClassDictationConsultation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, DtInstitute> institutesCache;
    
    public ClassDictationConsultation() {
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
        		if(theClass.getEnrollmentsQuantity() > 0) {
        			ArrayList<DtUser> users = new ArrayList<>();
            		for(Map.Entry<String, DtEnrollment> e : theClass.getEnrollments().entrySet()) {
            			DtUser currentUser = e.getValue().getMember();
            			// The server only sends the info that's going to be displayed
            			users.add(new DtUser(null, currentUser.getName(), currentUser.getLastName(), currentUser.getEmail(), null, null));
            		}
            		classInformation.put("members",  gson.toJson(users));
        		}
        		else {
        			classInformation.put("members", null);
        		}
        		classInformation.put("name", theClass.getName());
        		classInformation.put("url", theClass.getUrl());
        		classInformation.put("price", institutesCache.get(institute).getActivities().get(activity).getPrice().toString());
        		classInformation.put("date", theClass.getDateAndTime().toString());
        		
        		jsonToReturn = gson.toJson(classInformation);
        	}
        	default -> {
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
    	this.updateIC();
    	RequestDispatcher rd;
        request.setAttribute("institutes", institutesCache);
        rd = request.getRequestDispatcher("/classDictationConsultation.jsp");
        rd.forward(request,response);
    }
 }