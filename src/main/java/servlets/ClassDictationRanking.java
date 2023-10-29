package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import publishers.DtClass;
import publishers.InstitutePublisher;
import publishers.InstitutePublisherService;
import publishers.InstitutePublisherServiceLocator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

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
		List<DtClass> classes = listClassesDictationRanking();
		request.setAttribute("classes", classes);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/classDictationRanking.jsp");
		rd.forward(request,response);
	}
	
	private List<DtClass> listClassesDictationRanking() throws RemoteException {
		List<DtClass> lstClassDictationRanking = new ArrayList<DtClass>();
		try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			DtClass [] classes = ip.listClassesDictationRanking();
			for (int i = 0; i < classes.length; ++i) {
				lstClassDictationRanking.add(classes[i]);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return lstClassDictationRanking;	
	}

}
