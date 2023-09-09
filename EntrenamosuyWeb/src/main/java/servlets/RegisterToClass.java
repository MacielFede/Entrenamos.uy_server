package servlets;


import dataTypes.DtInstitute;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        // Depending on the parameter sent by the client I know what to return
        // Create an empty JSON array
        Gson array = new Gson();
        String jsonToReturn = "";
        
        if (institute != null) {
            chosenInstitute = institute;
            // Fill the array with the institute activities
            jsonToReturn = array.toJson(institutesCache.get(institute).getActivities());
        } else if (activity != null){
            chosenActivity = activity;
            // Fill the array with the activity classes
            jsonToReturn = array.toJson(institutesCache.get(chosenInstitute).getActivities().get(activity).getClasses());
        } else {
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
        RequestDispatcher rd;
        chosenClass = request.getHeader("chosenClass");
        if (chosenClass == null){
            request.setAttribute("institutes", institutesCache);
            rd = request.getRequestDispatcher("/registrationToClass.jsp");
            rd.forward(request,response);
        } else { // The user has chosen a class

        }
    }
}
