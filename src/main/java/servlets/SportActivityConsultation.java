package servlets;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.TreeMap;

import publishers.InstitutePublisher;
import publishers.InstitutePublisherService;
import publishers.InstitutePublisherServiceLocator;
import publishers.DtActivity;
import publishers.DtClass;
import publishers.DtInstitute;

import javax.xml.rpc.ServiceException;

@WebServlet("/SportActivityConsultation")
public class SportActivityConsultation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SportActivityConsultation() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        if (request.getSession(false) == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setHeader("error", "El usuario no inicio sesion");
            response.getWriter().close();
            return;
        }

        Map<String, DtInstitute> institutesList = this.listSportInstitutes();
        String institute = (String) request.getHeader("institute");
        String activity = (String) request.getHeader("activity");

        if ((institute != null) &&  (institute != "")) {
            try {
                if (activity == "" || activity == null) {
                    Map<String, DtActivity> activities = this.selectInstitution(institute);
                    Gson gson = new Gson();
                    String json = gson.toJson(activities);
                    response.setContentType("application/json");
                    response.getWriter().write(json);
                } else {
                    Map<String, DtClass> classes = this.chooseActivity(activity);
                    Gson gson = new Gson();
                    String json = gson.toJson(classes);
                    response.setContentType("application/json");
                    response.getWriter().write(json);
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setHeader("error", e.getMessage());
                response.getWriter().close();
            }
        } else {
            request.setAttribute("institutesList", institutesList);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/sportActivityConsultation.jsp");
            rd.forward(request,response);
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

	private Map<String, DtActivity> selectInstitution(String instituteName) throws Exception {
		Map<String,DtActivity> activities = new TreeMap<String,DtActivity>();
        try {
			InstitutePublisherService ips = new InstitutePublisherServiceLocator();
			InstitutePublisher ip = ips.getInstitutePublisherPort();
			DtActivity[] activitiesArray = ip.selectInstitution(instituteName);
			for (int i = 0; i < activitiesArray.length; ++i) {
				activities.put(activitiesArray[i].getName(), activitiesArray[i]);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return activities;
	}

    private  Map<String, DtClass> chooseActivity(String activityName) throws Exception {
    	Map<String,DtClass> classes = new TreeMap<String,DtClass>();
        try {
            InstitutePublisherService ips = new InstitutePublisherServiceLocator();
            InstitutePublisher ip = ips.getInstitutePublisherPort();
            DtClass[] classesArray = ip.chooseActivity(activityName);
            for (int i = 0; i < classesArray.length; ++i) {
				classes.put(classesArray[i].getName(), classesArray[i]);
			}
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return classes;
    }


    
}
