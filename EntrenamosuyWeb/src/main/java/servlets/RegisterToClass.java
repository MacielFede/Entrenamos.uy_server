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
    private final Map<String, DtInstitute> institutesCache;
    private String chosenInstitute = null;
    private String chosenActivity = null;
    private String chosenClass = null;
    
    public RegisterToClass() {
        super();
        InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
        institutesCache = ic.listSportInstitutes();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String institute = request.getParameter("chosenInstitute");
        String activity = request.getParameter("chosenActivity");
        String aClass = request.getParameter("chosenClass");
        // Create the Gson handler and the string to return
		Gson gson = new Gson();
        String jsonToReturn = "";
        
        // Depending on the parameter sent by the client I know what to return
        if (institute != null) {
            chosenInstitute = institute;
            // Fill the array with the institute activities
            jsonToReturn = gson.toJson(institutesCache.get(institute).getActivities());
        } else if (activity != null){
            chosenActivity = activity;
            // Fill the array with the activity classes
            jsonToReturn = gson.toJson(institutesCache.get(chosenInstitute).getActivities().get(activity).getClasses());
        } else if(aClass != null){
        	chosenClass = aClass;
        	// Fill the return object with the class information
        	Map<String, String> classInformation = new HashMap<>();
        	DtClass theClass = institutesCache.get(chosenInstitute).getActivities().get(chosenActivity).getClasses().get(aClass);
            
        	classInformation.put("name", theClass.getName());
        	classInformation.put("url", theClass.getUrl());
        	classInformation.put("price", institutesCache.get(chosenInstitute).getActivities().get(chosenActivity).getPrice().toString());
        	classInformation.put("date", theClass.getDateAndTime().toString());
        	jsonToReturn = gson.toJson(classInformation);
        }else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("No se encontraron parametros validos en la petición");
            response.getWriter().close();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonToReturn);
        response.getWriter().close();
	}

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getSession() == null || request.getSession().getAttribute("userType") != "M") {
    		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    		response.getWriter().write("El usuario no inicio sesión o es un profesor");
    		response.getWriter().close();
    	}
        if (chosenClass == null){
        	RequestDispatcher rd;
            request.setAttribute("institutes", institutesCache);
            rd = request.getRequestDispatcher("/registrationToClass.jsp");
            rd.forward(request,response);
        } else { // The user has chosen a class
        	UserInterface uc = ControllerFactory.getInstance().getUserInterface();
        	try {
        		DtUser user = uc.chooseUser((String) request.getSession().getAttribute("userName"));
        		uc.addEnrollment(chosenClass, user, institutesCache.get(chosenInstitute).getActivities().get(chosenActivity).getPrice());
        		response.setStatus(200);
        		response.getWriter().write("Se completó la insctipción satisfactoriamente!");
        		response.getWriter().close();
        	} catch (Exception e) {
        		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        		response.getWriter().write(e.getMessage());
        		response.getWriter().close();
        	}
        }
    }
}
