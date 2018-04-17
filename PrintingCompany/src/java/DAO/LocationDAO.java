
package daos;

import models.Location;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/* This DAO class provides CRUD database operations for the location book
  in the database.*/

public class LocationDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection; //instance variable of the locationDAO class
     
    public LocationDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        //connection only if connection is null or closed
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            //connection to db
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertLocation(Location location) throws SQLException {
        String sql = "INSERT INTO location (locationName, distributionCapacity) VALUES (?, ?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, location.getLocationName());
        statement.setInt(2, location.getDistributionCapacity());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Location> listAllLocations() throws SQLException {
        List<Location> listLocation = new ArrayList<>();
         
        String sql = "SELECT * FROM location";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String locationName = resultSet.getString("locationName");
            int distributionCap = resultSet.getInt("distributionCapacity");
             
            Location loc = new Location(id, locationName, distributionCap);
            listLocation.add(loc);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listLocation;
    }
     
    public boolean deleteLocation(Location location) throws SQLException {
        String sql = "DELETE FROM location where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, location.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateLocation(Location location) throws SQLException {
        String sql = "UPDATE location SET locationName = ?, distributionCapacity = ?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, location.getLocationName());
        statement.setInt(2, location.getDistributionCapacity());
        statement.setInt(3, location.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Location getLocation(int id) throws SQLException {
        Location loc = null;
        String sql = "SELECT * FROM location WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String locationName = resultSet.getString("locationName");
            int distributionCap = resultSet.getInt("distributionCapacity");
             
            loc = new Location(id, locationName, distributionCap);
        }
         
        resultSet.close();
        statement.close();
         
        return loc;
    }
}
