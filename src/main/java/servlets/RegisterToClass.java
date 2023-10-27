package servlets;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;

import publishers.UserPublisher;
import publishers.DtInstitute;
import publishers.DtInstituteActivitiesEntry;
import publishers.DtUser;
import publishers.InstitutePublisher;
import publishers.InstitutePublisherService;
import publishers.InstitutePublisherServiceLocator;
import publishers.DtActivity;
import publishers.DtActivityClassesEntry;
import publishers.DtClass;
import publishers.UserPublisherServiceLocator;
import publishers.UserPublisherService;

import javax.xml.rpc.ServiceException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
    	try {
			institutesCache = listSportInstitutes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
        		jsonToReturn = gson.toJson(getActivitiesMapFromArray(institutesCache.get(institute).getActivities()));            	        	
        	}
        	case "ACTIVITY" -> {
        		// Fill the array with the activity classes
        		jsonToReturn = gson.toJson(getClassesMapFromArray(getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getClasses()));
        	}
        	case "CLASS" -> {
        		
        		// Fill the return object with the class information
        		Map<String, String> classInformation = new HashMap<>();
        		DtClass theClass = getClassesMapFromArray(getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getClasses()).get(aClass);
        		
        		classInformation.put("name", theClass.getName());
        		classInformation.put("url", theClass.getUrl());
        		classInformation.put("price", getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getPrice().toString());
        		// Generating date
        		String date = theClass.getDateAndTime().get(Calendar.YEAR) 
                		+ "-" + 
                		(theClass.getDateAndTime().get(Calendar.MONTH) + 1 >= 10 ? theClass.getDateAndTime().get(Calendar.MONTH) + 1 : "0" + (theClass.getDateAndTime().get(Calendar.MONTH) + 1)) 
                		+ "-" 
                		+ (theClass.getDateAndTime().get(Calendar.DAY_OF_MONTH) >= 10 ? theClass.getDateAndTime().get(Calendar.DAY_OF_MONTH) : "0" + theClass.getDateAndTime().get(Calendar.DAY_OF_MONTH));
        		classInformation.put("date", date);
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
        	try {
        		DtUser user = chooseUser( (String) request.getSession().getAttribute("userName"));
        		this.addEnrollment(chosenClass, user, getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getPrice());
        		
        		response.setStatus(200);
        		response.getWriter().write("Se completó la insctipción satisfactoriamente!");
        		response.getWriter().close();
        	} catch (Exception e) {
        		this.sendBadResponse(response, e.getMessage());
        	}
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
    
    private Map<String, DtActivity> getActivitiesMapFromArray(DtInstituteActivitiesEntry[] activities) {
    	Map<String, DtActivity> activitiesMap = new TreeMap<String, DtActivity>();
    	for (DtInstituteActivitiesEntry act: activities) {
    		activitiesMap.put(act.getValue().getName(), act.getValue());
		}
    	return activitiesMap;
    }
    
    private Map<String, DtClass> getClassesMapFromArray(DtActivityClassesEntry[] classes){
    	Map<String, DtClass> classesMap = new TreeMap<String, DtClass>();
    	for (DtActivityClassesEntry cla: classes) {
    		classesMap.put(cla.getValue().getName(), cla.getValue());
		}
    	return classesMap;
    }
    
    private Map<String,DtInstitute> listSportInstitutes() throws RemoteException {
		Map<String,DtInstitute> institutes = new TreeMap<String,DtInstitute>();
		try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			DtInstitute[] institutesArray = ip.listSportInstitutes();
			for (int i = 0; i < institutesArray.length; ++i) {
				institutes.put(institutesArray[i].getName(), institutesArray[i]);
			}
		}
		catch(ServiceException e) {
			e.printStackTrace();
		}
		return institutes;
	}
    
    private void addEnrollment(String aClass, DtUser user, Float price) throws Exception {
		try {
			UserPublisherService ups = new UserPublisherServiceLocator();
			UserPublisher up = ups.getUserPublisherPort();
			up.addEnrollment(aClass, user, price);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
