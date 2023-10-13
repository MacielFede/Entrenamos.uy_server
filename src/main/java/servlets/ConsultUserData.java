package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtMember;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;

/**
 * Servlet implementation class ConsultUserData
 */
public class ConsultUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Activities to fetch from classes
	private Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
	// Controllers
	private InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
	private UserInterface uc = ControllerFactory.getInstance().getUserInterface();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultUserData() {
		super();
		// Get all the activities only once
		this.activities = ic.getAllActivities();
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
			Map<String, DtClass> currentActivityClasses = activity.getClasses();
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
		DtUser user = uc.chooseUser((String) session.getAttribute("userName"));
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
	        		DtClass theClass = ic.chooseClassByName(aClass);
	        		// Get activity from class
	        		DtActivity relatedActivity = this.getActivityFromClass(aClass);
	        		// User type
	        		information.put("userType", userType);
	        		// Class info
	        		information.put("className", theClass.getName());
	        		information.put("classUrl", theClass.getUrl());
	        		information.put("classPrice", relatedActivity.getPrice().toString());
	        		information.put("classDate", theClass.getDateAndTime().toString());
	        		// Activity info
	        		information.put("activityName", relatedActivity.getName());
	        		information.put("activityDescription", relatedActivity.getDescription());
	        		information.put("activityDuration", relatedActivity.getDuration().toString());
	        		information.put("activityDate", relatedActivity.getRegistryDate().toString());
	        		information.put("activityPrice", relatedActivity.getPrice().toString());
	        		jsonToReturn = gson.toJson(information);
				}
				default -> {
        			this.sendBadResponse(response, "No se encontraron parametros validos en la petición");
				}
			}
		} catch (Exception e) {
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
		DtUser user = uc.chooseUser((String) session.getAttribute("userName"));
		String userType = (String) session.getAttribute("userType");
		
		// If user is member, return related classes
		if (userType == "M") {
			Map<String, DtClass> memberClasses = uc.getMemberEnrolledClasses(user.getNickname());
			request.setAttribute("memberClasses", memberClasses);
		}
		// If user is professor, return his classes
		else if (userType == "P") {
			Map<String, DtClass> professorClasses = ((DtProfessor) user).getRelatedClasses();
			request.setAttribute("professorClasses", professorClasses);
		}
		request.setAttribute("userInfo", user);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/consultUserData.jsp");
		rd.forward(request, response);
	}

}
