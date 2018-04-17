package Servlets;

import DAO.AgentDAO;
import Models.Agent;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jpsza
 */
public class AgentServlet extends HttpServlet {

    AgentDAO agentDao;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;
    
    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        
        agentDao = new AgentDAO(jdbcURL, jdbcUserName, jdbcPassword);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getServletPath();
        try{
            switch(action){
                //Runs when user clicks save button on addAgentForm page
                case "/saveAdd":
                    insertAgent(request, response);
                    break;
                
                //Runs when user clicks add new agent link on index page
                case "/add":
                    showAddForm(request, response);
                    break;
                
                //Runs when user clicks update link in index page
                case "/update":
                    showUpdateForm(request, response);
                    break;
                    
                //Runs when user clicks save button on updateAgentForm page
                case "/saveUpdate":
                    updateAgent(request, response);
                    break;
                
                //Runs when user clicks delete link on index page
                case "/delete":
                    deleteAgent(request, response);
                    break;

                //Runs when index page loads
                default:
                    viewAgents(request, response);
                    break;

            }
        }catch(SQLException ex){
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateAgent(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteAgent(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void viewAgents(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertAgent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Agent userObj = new Agent(fName, lName, phone, email, username, password);
        boolean insertedAgent = agentDao.insertAgent(userObj);
        if(insertedAgent){
            response.sendRedirect("list");
        }
        else{
            
        }
        
    }
}
