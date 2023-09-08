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

public class RegisterToClass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RegisterToClass() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();

        Map<String, DtInstitute> institutes = ic.listSportInstitutes();
        request.setAttribute("institutes", institutes);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/registrationToClass.jsp");
        rd.forward(request,response);
    }
}
