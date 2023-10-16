package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

import dataTypes.DtClass;
import dataTypes.DtEnrollment;
import dataTypes.DtInstitute;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;

/**
 * Servlet implementation class AddClass
 */
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInterface uc = ControllerFactory.getInstance().getUserInterface();
	private InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
	private Map<String, DtInstitute> institutesCache;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClass() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void updateIC() {
        institutesCache = ic.listSportInstitutes();
    }
    
    private void sendBadResponse(HttpServletResponse res, String message) throws IOException {
    	res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	res.setHeader("error", message);
        res.getWriter().close();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String institute = request.getParameter("chosenInstitute");
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
        	}
        	
        } catch(Exception e) {
        	this.sendBadResponse(response, "La informacion estaba desactualizada, intentalo de nuevo");
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonToReturn);
        response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get already created session
		HttpSession session = request.getSession(false);
		String userType = (String) session.getAttribute("userType");
		DtUser user = uc.chooseUser((String) session.getAttribute("userName"));
		// Check that user is logged in
		if (session == null || userType != "P") {
			this.sendBadResponse(response, "El usuario no inicio sesión o no es un profesor.");
		}
		
		String chosenInstitute = (String) request.getHeader("institute");
		String chosenActivity = (String) request.getHeader("activity");
		
		// Just send jsp
		if (chosenInstitute == null && chosenActivity == null) {
			request.setAttribute("userInfo", user);
			this.updateIC();
			RequestDispatcher rd;
            request.setAttribute("institutes", institutesCache);
            rd = request.getRequestDispatcher("/addClass.jsp");
            rd.forward(request,response);	
		} else { // Register class
			try {
				String className = (String) request.getHeader("className");
				// Check class name availability
				if (!ic.checkClassNameAvailability(className)) {
					this.sendBadResponse(response, "El nombre de la clase ya esta en uso.");
				}
				String url = (String) request.getHeader("url");
				String[] dateArray = request.getHeader("initDate").split("-", 3);
				Date initDate = new Date(Integer.parseInt(dateArray[0]) - 1900 , Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
				Date registerDate = new Date();
				String professorNickname = user.getNickname();
				// Create class
				DtClass newClass = new DtClass(className, initDate, registerDate, url, 0, new TreeMap<String, DtEnrollment>(), "");
				ic.createSportClass(newClass, chosenActivity, professorNickname);
				// Send response
				response.setStatus(200);
        		response.getWriter().write("Se completó el alta satisfactoriamente!");
        		response.getWriter().close();
			} catch (Exception e) {
				this.sendBadResponse(response, e.getMessage());
			}
		}
	}

}
