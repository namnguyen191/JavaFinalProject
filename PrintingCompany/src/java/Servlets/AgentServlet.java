package Servlets;

import DAO.AgentDAO;
import Models.Agent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddAgentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void updateAgent(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
 
        Agent agent = new Agent(id, fname, lname, phone, email);
        agentDao.updateAgent(agent);
        response.sendRedirect("list");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agent agent = agentDao.readAgent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateAgentForm.jsp");
        request.setAttribute("agent", agent);
        dispatcher.forward(request, response);
    }

    private void deleteAgent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Agent agent = new Agent(id);
        agentDao.deleteAgent(agent);
        response.sendRedirect("list");
        
    }

    private void viewAgents(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Agent> agentList = agentDao.readAgents(); //list all locations method of dao class
        request.setAttribute("agentList", agentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AgentIndex.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAgent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        Agent userObj = new Agent(fName, lName, phone, email);
        boolean insertedAgent = agentDao.insertAgent(userObj);
        if(insertedAgent){
            response.sendRedirect("list");
        }
        else{
              
        }
        
    }
}
