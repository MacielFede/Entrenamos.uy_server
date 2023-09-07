package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dataTypes.DtClass;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

/**
 * Servlet implementation class ClasDictationRanking
 */
public class ClassDictationRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassDictationRanking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		InstituteInterface ic = controllerFactory.getInstituteInterface();
		
		List<DtClass> classes = ic.listClassesDictationRanking();
		request.setAttribute("classes", classes);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/classDictationRanking.jsp");
		rd.forward(request,response);
	}

}
