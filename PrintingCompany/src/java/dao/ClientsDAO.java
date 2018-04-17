/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Clients;

public class ClientsDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ClientsDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
}
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
     public boolean insertClient(Clients client) throws SQLException {
        String sql = "INSERT INTO clients (agentId,firstName,lastName,streetNumber,streetName,city,province,postalCode,telOffice,telCell,email,company,companyType) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setInt(1,client.getAgentId());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getLastName());
        statement.setInt(4, client.getStreetNumber());
        statement.setString (5, client.getStreetName());
        statement.setString (6, client.getCity());
        statement.setString (7, client.getProvince());
        statement.setString (8, client.getPostalCode());
        statement.setString (9, client.getTelOffice());
        statement.setString (10, client.getTelCell());
        statement.setString (11, client.getEmail());
        statement.setString (12, client.getCompany());
        statement.setString (13, client.getCompanyType());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     public List<Clients> listAllClients() throws SQLException {
        List<Clients> listClient = new ArrayList<>();
         
        String sql = "SELECT * FROM clients";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int agentId = resultSet.getInt ("agentId"); 
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int streetNumber = resultSet.getInt("streetNumber");
            String streetName = resultSet.getString("streetName");
            String city = resultSet.getString("city");
            String province = resultSet.getString("province");
            String postalCode = resultSet.getString("postalCode");
            String telOffice = resultSet.getString("telOffice");
            String telCell= resultSet.getString("telCell");
            String email= resultSet.getString("email");
            String company= resultSet.getString("company");
            String companyType= resultSet.getString("companyType");
             
            Clients client = new Clients(id, agentId, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType);
            listClient.add(client);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listClient;
    }
      public boolean deleteClient(Clients client) throws SQLException {
        String sql = "DELETE FROM clients where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, client.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
       public boolean updateClient(Clients client) throws SQLException {
        boolean rowUpdated;
        String sql = "UPDATE clients SET agentId = ?, firstName = ?, lastName = ?, streetNumber = ?, streetName = ?, city = ?, province = ?, postalCode = ?, telOffice = ?, telCell = ?, email = ?, company = ?, companyType = ?";
        sql += " WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, client.getAgentId());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getLastName());
        statement.setInt(4, client.getStreetNumber());
        statement.setString (5, client.getStreetName());
        statement.setString (6, client.getCity());
        statement.setString (7, client.getProvince());
        statement.setString (8, client.getPostalCode());
        statement.setString (9, client.getTelOffice());
        statement.setString (10, client.getTelCell());
        statement.setString (11, client.getEmail());
        statement.setString (12, client.getCompany());
        statement.setString (13, client.getCompanyType());
        statement.setInt(14, client.getId());
        rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
      public Clients getClient(int id) throws SQLException {
        Clients client = null;
        String sql = "SELECT * FROM clients WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            int agentId = resultSet.getInt("agentId"); 
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int streetNumber = resultSet.getInt("streetNumber");
            String streetName = resultSet.getString("streetName");
            String city = resultSet.getString("city");
            String province = resultSet.getString("province");
            String postalCode = resultSet.getString("postalCode");
            String telOffice = resultSet.getString("telOffice");
            String telCell= resultSet.getString("telCell");
            String email= resultSet.getString("email");
            String company= resultSet.getString("company");
            String companyType= resultSet.getString("companyType");
            client = new Clients(id, agentId, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType);
        }
         
        resultSet.close();
        statement.close();
         
        return client;
    }
}