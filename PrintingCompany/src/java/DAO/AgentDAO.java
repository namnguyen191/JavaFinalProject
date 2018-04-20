package DAO;

import Models.Agent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jpsza
 */
public class AgentDAO {
    private final String url;
    private final String agentsDB;
    private final String passDB;
    private Connection jdbcConnection;
    
    public AgentDAO(String url, String agentsDB, String passDB){
        this.url = url;
        this.agentsDB = agentsDB;
        this.passDB = passDB;
    }
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        url, agentsDB, passDB);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    public boolean insertAgent(Agent agentObj) throws SQLException{
        String sql = "INSERT INTO marketingagent (firstName,lastName, phoneNo, email) VALUES (?,?,?,?)";
        connect();
        boolean rowsInserted;
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(sql)) {
            stmt.setString(1,agentObj.getfName());
            stmt.setString(2,agentObj.getlName());
            stmt.setString(3,agentObj.getPhone());
            stmt.setString(4, agentObj.getEmail());
            rowsInserted = stmt.executeUpdate()>0;
        }
        disconnect();
        return rowsInserted;
    }
    
    public boolean updateAgent(Agent agentObj) throws SQLException{
        String sql = "UPDATE marketingagent SET firstName = ?, lastName = ?,"
                + " phoneNo = ?, email = ? WHERE id = ?";
        connect();
        boolean rowsUpdated;
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(sql)) {
            stmt.setString(1,agentObj.getfName());
            stmt.setString(2,agentObj.getlName());
            stmt.setString(3,agentObj.getPhone());
            stmt.setString(4, agentObj.getEmail());
            stmt.setInt(5,agentObj.getId());
            rowsUpdated = stmt.executeUpdate()>0;
        }
        disconnect();
        return rowsUpdated;
    }
    
    public Agent readAgent(int id) throws SQLException{
        String sql = "SELECT firstName, lastName, phoneNo, email FROM marketingagent WHERE id = ?";
        Agent agent = new Agent();
        connect();
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(sql)) {
            stmt.setInt(1,id);
            try (ResultSet resultSet = stmt.executeQuery(sql)) {
                agent.setId(resultSet.getInt(1));
                agent.setfName(resultSet.getString(2));
                agent.setlName(resultSet.getString(3));
                agent.setPhone(resultSet.getString(4));
                agent.setEmail(resultSet.getString(5));
            }
        }
        disconnect();
        return agent;
    }
    
    public ArrayList readAgents() throws SQLException{
        String sql = "SELECT id, firstName, lastName, phoneNo, email FROM marketingagent";
        ArrayList agents = new ArrayList();
        Agent agent = new Agent();
        connect();
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(sql)) {
            try (ResultSet resultSet = stmt.executeQuery(sql)) {
                while(resultSet.next()){
                    agent.setId(resultSet.getInt(1));
                    agent.setfName(resultSet.getString(2));
                    agent.setlName(resultSet.getString(3));
                    agent.setPhone(resultSet.getString(4));
                    agent.setEmail(resultSet.getString(5));
                    agents.add(agent);
                }
            }
        }
        disconnect();
        return agents;
    }
    
    public boolean deleteAgent(Agent agentObj) throws SQLException{
        String sql = "DELETE FROM marketingagent WHERE id = ?";
        connect();
        boolean rowsUpdated;
        try (PreparedStatement stmt = jdbcConnection.prepareStatement(sql)) {
            stmt.setInt(1,agentObj.getId());
            rowsUpdated = stmt.executeUpdate()>0;
        }
        disconnect();
        return rowsUpdated;
    }
}
