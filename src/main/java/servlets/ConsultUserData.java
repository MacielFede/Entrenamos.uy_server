package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		// Requested something
		if (request.getParameter("consultUserExecute") != null) {
			
			// Use-case flow:
			// 1- If member, just get all the class related info and return it as a JSON, then show it into a table (if user clicks on a button
			// that will be like "Show class info". The basic user info will be shown as usual
			// 2- If professor, get the class related info AND the related activity details. Return it as a JSON. 
			// Basic user info will be shown + class info will be shown + the related activity name will be shown. Next to the related activity,
			// There will be a button like "Show activity info" and when clicked, user will be able to be activity details.
			
			

		} else { // Just requested the jsp
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

}
