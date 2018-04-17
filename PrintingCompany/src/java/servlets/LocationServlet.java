//UI:
//webpage has lists of all locations
//2 buttons
package Servlets;

import models.Location;
import DAO.LocationDAO;
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
 
public class LocationServlet extends HttpServlet {

    private LocationDAO locDAO; //instance variable called locDAO
 
    @Override
    public void init() {
        //store these into string
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        
        //pass info as agrs into constructor dao
        locDAO = new LocationDAO(jdbcURL, jdbcUsername, jdbcPassword); //initialize locDAO object
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try{
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertLocation(request, response);
                break;
            case "/delete":
                deleteLocation(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateLocation(request, response);
                break;
            default:
                //call listLocation method for user to see all the lists of the locations in the website
                //method accepts request and response therefore method must throw ioexception and servletexception
                listLocation(request, response);
                break;
        }
        }catch(SQLException e){
            throw new ServletException(e);
        }
        

    }
    
    //lists all locations in the databse
    //throws doget and dopost response
    private void listLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Location> listLocation = locDAO.listAllLocations(); //list all locations method of dao class
        request.setAttribute("listLocation", listLocation);
        RequestDispatcher dispatcher = request.getRequestDispatcher("LocationList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("LocationForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Location existingLocation = locDAO.getLocation(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("LocationForm.jsp");
        request.setAttribute("location", existingLocation);
        dispatcher.forward(request, response);
 
    }
 
    private void insertLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String locationName = request.getParameter("locationName");
        int distributionCap = Integer.parseInt(request.getParameter("distributionCapacity"));
 
        Location newLocation = new Location(locationName, distributionCap);
        locDAO.insertLocation(newLocation);
        response.sendRedirect("list");
    }
 
    private void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String locationName = request.getParameter("locationName");
        int distributionCap = Integer.parseInt(request.getParameter("distributionCapacity"));
 
        Location location = new Location(id, locationName, distributionCap);
        locDAO.updateLocation(location);
        response.sendRedirect("list");
    }
 
    private void deleteLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Location location = new Location(id);
        locDAO.deleteLocation(location);
        response.sendRedirect("list");
 
    }
}
