package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import publishers.DtActivity;
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
public class SportActivitiesRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SportActivitiesRanking() {
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
		List<DtActivity> activities = listSportsActivitiesRanking();
		request.setAttribute("activities", activities);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/sportActivitiesRanking.jsp");
		rd.forward(request,response);
	}
	
	private List<DtActivity> listSportsActivitiesRanking() throws RemoteException {
		List<DtActivity> lstSportActivitiesRanking = new ArrayList<DtActivity>();
		try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			DtActivity [] activities = ip.listSportsActivitiesRanking();
			for (int i = 0; i < activities.length; ++i) {
				lstSportActivitiesRanking.add(activities[i]);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return lstSportActivitiesRanking;	
	}

}
