
package servlets;

import dao.ClientsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Clients;


 
public class ClientServlet extends HttpServlet {

   //private static final long serialVersionUID = 1L;
    ClientsDAO clientDAO;
    String jdbcUsername;
    String jdbcPassword;
    String jdbcURL;
    
 
 
    public void init() {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        clientDAO = new ClientsDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertClient(request, response);
                break;
            case "/delete":
                deleteClient(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateClient(request, response);
                break;
            default:
                listClient(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }
 
    private void listClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
      
        List<Clients> listClient = clientDAO.listAllClients();
        request.setAttribute("listClient", listClient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientList.jsp");
        dispatcher.forward(request, response);
   
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Clients existingClient = clientDAO.getClient(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientForm.jsp");
        request.setAttribute("client", existingClient);
        dispatcher.forward(request, response);
    }
 
    private void insertClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //id, agentId, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
        String streetName = request.getParameter("streetName");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");
        String telOffice = request.getParameter("telOffice");
        String telCell = request.getParameter("telCell");
        String email = request.getParameter("email");
        String company = request.getParameter("company");
        String companyType = request.getParameter("companyType");
 
        Clients newClient = new Clients(agentId, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType);
        clientDAO.insertClient(newClient);
        response.sendRedirect("list");
    }
 
    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

       
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
        String streetName = request.getParameter("streetName");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");
        String telOffice = request.getParameter("telOffice");
        String telCell = request.getParameter("telCell");
        String email = request.getParameter("email");
        String company = request.getParameter("company");
        String companyType = request.getParameter("companyType");
         int id = Integer.parseInt(request.getParameter("id"));
      
 
        Clients client = new Clients(id,agentId,firstName,lastName,streetNumber,streetName,city,province,postalCode,telOffice,telCell,email,company,companyType);
        clientDAO.updateClient(client);
        response.sendRedirect("list");
    }
 
    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Clients client = new Clients(id);
        clientDAO.deleteClient(client);
        response.sendRedirect("list");
 
    }
}
