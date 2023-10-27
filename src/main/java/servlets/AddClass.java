package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publishers.UserPublisher;
import publishers.UserPublisherService;
import publishers.UserPublisherServiceLocator;
import publishers.InstitutePublisherServiceLocator;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

import com.google.gson.Gson;

import publishers.DtClass;
import publishers.DtClassEnrollmentsEntry;
import publishers.DtEnrollment;
import publishers.DtInstitute;
import publishers.DtUser;
import publishers.InstitutePublisher;
import publishers.InstitutePublisherService;

/**
 * Servlet implementation class AddClass
 */
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, DtInstitute> institutesCache;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClass() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void updateIC() {
        try {
			institutesCache = listSportInstitutes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
		DtUser user = null;
		try {
			user = chooseUser((String) session.getAttribute("userName"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				if (!checkClassNameAvailability(className)) {
					this.sendBadResponse(response, "El nombre de la clase ya esta en uso.");
				}
				String url = (String) request.getHeader("url");
				String[] dateArray = request.getHeader("initDate").split("-", 3);
				Calendar initDate = Calendar.getInstance();
				initDate.set(Calendar.YEAR, Integer.parseInt(dateArray[0]));
				initDate.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1); // Calendar month is 0-based
				initDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]));
				
				Calendar registerDate = Calendar.getInstance();
				String professorNickname = user.getNickname();
				DtClassEnrollmentsEntry[] enrollments = new DtClassEnrollmentsEntry[0];
				// Create class
				DtClass newClass = new DtClass(className, initDate, registerDate, url, "", 0, enrollments);
				createSportClass(newClass, chosenActivity, professorNickname);
				// Send response
				response.setStatus(200);
        		response.getWriter().write("Se completó el alta satisfactoriamente!");
        		response.getWriter().close();
			} catch (Exception e) {
				this.sendBadResponse(response, e.getMessage());
			}
		}
	}
	
	private void createSportClass(DtClass newClass, String chosenActivity, String professorNickname) throws Exception {
		try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			ip.createSportClass(newClass, chosenActivity, professorNickname);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	
	private boolean checkClassNameAvailability(String className) throws Exception {
		try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			return ip.checkClassNameAvailability(className, null);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;	
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

}
