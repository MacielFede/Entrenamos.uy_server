package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publishers.DtActivity;
import publishers.DtActivityClassesEntry;
import publishers.DtClass;
import publishers.DtProfessor;
import publishers.DtProfessorRelatedClassesEntry;
import publishers.DtUser;
import publishers.InstitutePublisher;
import publishers.InstitutePublisherService;
import publishers.InstitutePublisherServiceLocator;
import publishers.UserPublisher;
import publishers.UserPublisherService;
import publishers.UserPublisherServiceLocator;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.rpc.ServiceException;

import com.google.gson.Gson;

/**
 * Servlet implementation class ConsultUserData
 */
public class ConsultUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Activities to fetch from classes
	private Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultUserData() {
		super();
		// Get all the activities only once
		try {
			this.activities = getAllActivities();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendBadResponse(HttpServletResponse res, String message) throws IOException {
		res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		res.setHeader("error", message);
		res.getWriter().close();
	}
	
	private DtActivity getActivityFromClass(String className) {
		// Iterate through activities to find the class related activity
		for (Map.Entry<String, DtActivity> entry : activities.entrySet()) {
			DtActivity activity = entry.getValue();
			// Get activity classes
			DtActivityClassesEntry[] currentActivityClassesArray = activity.getClasses();
			Map<String,DtClass> currentActivityClasses = getClassesMapFromArray(currentActivityClassesArray);
			for (Map.Entry<String, DtClass> classEntry : currentActivityClasses.entrySet()) {
				DtClass aClass = classEntry.getValue();
				if (aClass.getName().equals(className))
					return activity;
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get already created session
		HttpSession session = request.getSession(false);
		// Get user info
		try {
			DtUser user = chooseUser((String) session.getAttribute("userName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userType = (String) session.getAttribute("userType");
		// Get parameters
		String aClass = request.getParameter("chosenClass");
		String action = request.getHeader("action");

		// Create the Gson handler and the string to return
		Gson gson = new Gson();
		String jsonToReturn = "";

		// Get info
		try {
			switch(action) {
				case "CLASS" -> {
					// Fill the return object with the class information
	        		Map<String, String> information = new HashMap<>();
	        		// Get class info
	        		
	        		System.out.println(aClass);
	        		DtClass theClass = chooseClassByName(aClass);
	        		System.out.println(theClass.toString());
	        		// Get activity from class
	        		DtActivity relatedActivity = this.getActivityFromClass(aClass);
	        		// User type
	        		information.put("userType", userType);
	        		// Class info
	        		information.put("className", theClass.getName());
	        		information.put("classUrl", theClass.getUrl());
	        		information.put("classPrice", relatedActivity.getPrice().toString());
	        		// Class date
	        		String date = theClass.getDateAndTime().get(Calendar.YEAR) 
	                		+ "-" + 
	                		(theClass.getDateAndTime().get(Calendar.MONTH) + 1 >= 10 ? theClass.getDateAndTime().get(Calendar.MONTH) + 1 : "0" + (theClass.getDateAndTime().get(Calendar.MONTH) + 1)) 
	                		+ "-" 
	                		+ (theClass.getDateAndTime().get(Calendar.DAY_OF_MONTH) >= 10 ? theClass.getDateAndTime().get(Calendar.DAY_OF_MONTH) : "0" + theClass.getDateAndTime().get(Calendar.DAY_OF_MONTH));
	        		information.put("classDate", date);
	        		// Activity info
	        		information.put("activityName", relatedActivity.getName());
	        		information.put("activityDescription", relatedActivity.getDescription());
	        		information.put("activityDuration", relatedActivity.getDuration().toString());      		
	        		// Activity date
	        		String activityDate = relatedActivity.getRegistryDate().get(Calendar.YEAR) 
	                		+ "-" + 
	                		(relatedActivity.getRegistryDate().get(Calendar.MONTH) + 1 >= 10 ? relatedActivity.getRegistryDate().get(Calendar.MONTH) + 1 : "0" + (relatedActivity.getRegistryDate().get(Calendar.MONTH) + 1)) 
	                		+ "-" 
	                		+ (relatedActivity.getRegistryDate().get(Calendar.DAY_OF_MONTH) >= 10 ? relatedActivity.getRegistryDate().get(Calendar.DAY_OF_MONTH) : "0" + relatedActivity.getRegistryDate().get(Calendar.DAY_OF_MONTH));
	        		information.put("activityDate", activityDate);
	        		information.put("activityPrice", relatedActivity.getPrice().toString());
	        		jsonToReturn = gson.toJson(information);
				}
				default -> {
        			this.sendBadResponse(response, "No se encontraron parametros validos en la petición");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.sendBadResponse(response, "La informacion estaba desactualizada, intentalo de nuevo");
		}
		// Send response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonToReturn);
        response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get already created session
		HttpSession session = request.getSession(false);
		// Check that user is logged in
		if (session == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setHeader("error", "El usuario no inicio sesion");
			response.getWriter().close();
			return;
		}

		// Get user info
		DtUser user = null;
		try {
			user = chooseUser((String) session.getAttribute("userName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userType = (String) session.getAttribute("userType");
		
		// If user is member, return related classes
		if (userType == "M") {
			Map<String, DtClass> memberClasses;
			try {
				memberClasses = getMemberEnrolledClasses(user.getNickname());
				request.setAttribute("memberClasses", memberClasses);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		// If user is professor, return his classes
		else if (userType == "P") {
			DtProfessorRelatedClassesEntry[] professorClassesArray = ((DtProfessor) user).getRelatedClasses();
			Map<String,DtClass> professorClasses = getProfessorClassesMapFromArray(professorClassesArray);
			request.setAttribute("professorClasses", professorClasses);
		}
		request.setAttribute("userInfo", user);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/consultUserData.jsp");
		rd.forward(request, response);
	}
	
	private DtClass chooseClassByName(String className) throws Exception {
		try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			return ip.chooseClassByName(className);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Map<String, DtActivity> getAllActivities() throws Exception {
		Map<String,DtActivity> activities = new TreeMap<String,DtActivity>();
		try {
			InstitutePublisherService ups = new InstitutePublisherServiceLocator();
			InstitutePublisher up = ups.getInstitutePublisherPort();
			DtActivity[] activitiesArray = up.getAllActivities();
			for (int i = 0; i < activitiesArray.length; ++i) {
				activities.put(activitiesArray[i].getName(), activitiesArray[i]);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return activities;
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
	
	private Map<String, DtClass> getClassesMapFromArray(DtActivityClassesEntry[] classes){
    	Map<String, DtClass> classesMap = new TreeMap<String, DtClass>();
    	for (DtActivityClassesEntry cla: classes) {
    		classesMap.put(cla.getValue().getName(), cla.getValue());
		}
    	return classesMap;
    }
	
	private Map<String, DtClass> getProfessorClassesMapFromArray(DtProfessorRelatedClassesEntry[] classes){
    	Map<String, DtClass> classesMap = new TreeMap<String, DtClass>();
    	for (DtProfessorRelatedClassesEntry cla: classes) {
    		classesMap.put(cla.getValue().getName(), cla.getValue());
		}
    	return classesMap;
    }
	
	private Map<String, DtClass> getMemberEnrolledClasses(String nickname) throws Exception {
		Map<String,DtClass> classes = new TreeMap<String,DtClass>();
		try {
			UserPublisherService ups = new UserPublisherServiceLocator();
			UserPublisher up = ups.getUserPublisherPort();
			DtClass[] classesArray = up.getMemberEnrolledClasses(nickname);
			for (int i = 0; i < classesArray.length; ++i) {
				classes.put(classesArray[i].getName(), classesArray[i]);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	

}
