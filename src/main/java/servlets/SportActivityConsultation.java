package servlets;

import com.google.gson.Gson;
import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

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
        ControllerFactory controllerFactory = ControllerFactory.getInstance();
        InstituteInterface ic = controllerFactory.getInstance().getInstituteInterface();

        Map<String, DtInstitute> institutesList = ic.listSportInstitutes();
        String institute = (String) request.getHeader("institute");
        String activity = (String) request.getHeader("activity");

        if ((institute != null) &&  (institute != "")) {
            try {
                if (activity == "" || activity == null) {
                    Map<String, DtActivity> activities = ic.selectInstitution(institute);
                    Gson gson = new Gson();
                    String json = gson.toJson(activities);
                    response.setContentType("application/json");
                    response.getWriter().write(json);
                } else {
                    Map<String, DtClass> classes = ic.chooseActivity(activity);
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
}
