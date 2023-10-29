package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import publishers.DtActivity;
import publishers.DtActivityClassesEntry;
import publishers.DtClass;
import publishers.DtClassEnrollmentsEntry;
import publishers.DtInstitute;
import publishers.DtInstituteActivitiesEntry;
import publishers.DtUser;
import publishers.InstitutePublisher;
import publishers.InstitutePublisherService;
import publishers.InstitutePublisherServiceLocator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

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
    
    private void updateIC() throws Exception {
        institutesCache = this.listSportInstitutes();
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
        		DtInstituteActivitiesEntry[] activities = institutesCache.get(institute).getActivities();
        		Map<String, DtActivity> activitiesMap = new TreeMap<String, DtActivity>();
        		if(activities.length != 0) {
        			activitiesMap = getActivitiesMapFromArray(activities);
        		}
        		jsonToReturn = gson.toJson(activitiesMap);            	        	
        	}
        	case "ACTIVITY" -> {
        		// Fill the array with the activity classes
        		DtActivityClassesEntry[] classes = getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getClasses();
        		Map<String, DtClass> classesMap = new TreeMap<String, DtClass>();
        		if(classes.length != 0) {
        			classesMap = getClassesMapFromArray(classes);
        		}
        		jsonToReturn = gson.toJson(classesMap);
        	}
        	case "CLASS" -> {
        		// Fill the return object with the class information
        		Map<String, String> classInformation = new HashMap<>();
        		Map<String, DtClass> classesMap = getClassesMapFromArray(getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getClasses());
        		DtClass theClass = classesMap.get(aClass);
        		if(theClass.getEnrollmentsQuantity() > 0) {
        			ArrayList<DtUser> users = new ArrayList<>();
        			DtClassEnrollmentsEntry[] enrrollments = theClass.getEnrollments();
        			if(enrrollments.length != 0) {
        				for(DtClassEnrollmentsEntry e : enrrollments) {
                			DtUser currentUser = e.getValue().getUser();
                			// The server only sends the info that's going to be displayed
                			users.add(new DtUser(null, currentUser.getName(), currentUser.getLastName(), currentUser.getEmail(), null, null));
                		}
        			}
            		classInformation.put("members",  gson.toJson(users));
        		}
        		else {
        			classInformation.put("members", null);
        		}
        		classInformation.put("name", theClass.getName());
        		classInformation.put("url", theClass.getUrl());
        		classInformation.put("price", getActivitiesMapFromArray(institutesCache.get(institute).getActivities()).get(activity).getPrice().toString()); 
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
    	try {
			this.updateIC();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	RequestDispatcher rd;
        request.setAttribute("institutes", institutesCache);
        rd = request.getRequestDispatcher("/classDictationConsultation.jsp");
        rd.forward(request,response);
    }
    
    private Map<String, DtInstitute> listSportInstitutes() throws Exception {
    	Map<String, DtInstitute> siMap = new TreeMap<String, DtInstitute>();
    	try {
    		InstitutePublisherService ups = new InstitutePublisherServiceLocator();
			InstitutePublisher up = ups.getInstitutePublisherPort();
			DtInstitute[] arrayI = up.listSportInstitutes();
			for (DtInstitute inst: arrayI) {
				siMap.put(inst.getName(), inst);
			}
    	}catch(ServiceException e) {
    		e.printStackTrace();
    	}
    	return siMap;
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
    
    
 }