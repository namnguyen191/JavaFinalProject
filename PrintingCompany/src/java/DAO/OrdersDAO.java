
package DAO;


import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Location;
import models.Order;


public class OrdersDAO {
    
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    public OrdersDAO(String jdbcURL, String jdbcUsername, String jdbcPassword){
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
    
    public List<String> getOrderLocation(int orderId, LocationDAO locationsDAO) throws SQLException{
        List<String> orderLocations = new ArrayList();
        String sql = "SELECT locationId FROM locationxorders WHERE orderId = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, orderId);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            orderLocations.add((locationsDAO.getLocation(resultSet.getInt("locationId"))).getLocationName());
        }
        statement.close();
        disconnect();
        return orderLocations;
    }
    
    public boolean addOrderLocationId(int orderId, List<String> orderLocations, LocationDAO locationsDAO) throws SQLException{ 
        List<Location> locList = locationsDAO.listAllLocations();
        connect();
        String query = "INSERT INTO locationxorders(orderId, locationId) VALUES(?,?)";
        PreparedStatement statement = jdbcConnection.prepareStatement(query);
        boolean rowsInserted = false;
        for(String locName : orderLocations){
            for(Location loc : locList){
                if(loc.getLocationName().equalsIgnoreCase(locName)){
                    statement.setInt(1, orderId);
                    statement.setInt(2, loc.getId());
                    rowsInserted = statement.executeUpdate() > 0;
                    break;
                }
            }
        }       
        statement.close();
        disconnect();
        return rowsInserted;
    }
    
    
    public boolean wipeOrderLocationIdRecord(int id) throws SQLException{
        String query = "Delete FROM locationxorders WHERE orderId = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(query);
        statement.setInt(1, id);
        boolean rowsDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowsDeleted;
    }
        
    public List<Order> listAllOrders() throws SQLException {
        List<Order> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            int agentId = resultSet.getInt("agentId");
            int clientId = resultSet.getInt("clientId");
            int flyerQty = resultSet.getInt("flyerQty");
            String flyerLayout = resultSet.getString("flyerLayout");
            Blob flyerImg = resultSet.getBlob("flyerImg");
            int personalCopy = resultSet.getInt("personalCopy");
            String paymentInfo = resultSet.getString("paymentInformation");
            String invoiceNumber = resultSet.getString("invoiceNumber");
            String comments = resultSet.getString("comments");
            int isFlyerArtApproved = resultSet.getInt("isFlyerArtApproved");
            int isPaymentReceived = resultSet.getInt("isPaymentReceived");
            Order order = new Order(id, agentId, clientId, flyerQty, personalCopy, isFlyerArtApproved, isPaymentReceived, flyerLayout, paymentInfo, comments, invoiceNumber, flyerImg);
            ordersList.add(order);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return ordersList;
    }
    
    public int insertOrder(Order order, InputStream in) throws SQLException{
        String query = "INSERT INTO orders(agentId, clientId, flyerQty, flyerLayout, flyerImg, personalCopy, paymentInformation, invoiceNumber, comments, isFlyerArtApproved, isPaymentReceived) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(query);
        statement.setInt(1, order.getAgentId());
        statement.setInt(2, order.getClientid());
        statement.setInt(3, order.getFlyerQty());
        statement.setString(4, order.getFlyerLayout());
        statement.setBlob(5, in);
        statement.setInt(6, order.getPersonalCopy());
        statement.setString(7, order.getPaymentInfo());
        statement.setString(8, order.getInvoiceNo());
        statement.setString(9, order.getComments());
        statement.setInt(10, order.getIsFlyerArtApproved());
        statement.setInt(11, order.getIsPaymentReceived());
        boolean rowsInserted = statement.executeUpdate() > 0;
        query = "SELECT MAX(id) FROM orders";
        Statement stm = jdbcConnection.createStatement();
        ResultSet resultSet = stm.executeQuery(query);
        int id = 0;
        while(resultSet.next()){
            id = resultSet.getInt(1);
        }
        statement.close();
        disconnect();
        return id;
    }
    
    public boolean updateOrder(Order order, InputStream in) throws SQLException {
        String query = "UPDATE orders SET agentId = ?, clientId = ?, flyerQty = ?, flyerLayout = ?, personalCopy = ?, paymentInformation = ?, invoiceNumber = ?, comments = ?, isFlyerArtApproved = ?, isPaymentReceived =?, flyerImg=? WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(query);
        statement.setInt(1, order.getAgentId());
        statement.setInt(2, order.getClientid());
        statement.setInt(3, order.getFlyerQty());
        statement.setString(4, order.getFlyerLayout());
        statement.setInt(5, order.getPersonalCopy());
        statement.setString(6, order.getPaymentInfo());
        statement.setString(7, order.getInvoiceNo());
        statement.setString(8, order.getComments());
        statement.setInt(9, order.getIsFlyerArtApproved());
        statement.setInt(10, order.getIsPaymentReceived());
        statement.setBlob(11, in);
        statement.setInt(12, order.getId());
        boolean rowsUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowsUpdated;
    }
    
    public Order getOrder(int id) throws SQLException {
        Order order = new Order();
        String query = "SELECT * FROM orders WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            int agentId = resultSet.getInt("agentId");
            int clientId = resultSet.getInt("clientId");
            int flyerQty = resultSet.getInt("flyerQty");
            String flyerLayout = resultSet.getString("flyerLayout");
            Blob flyerImg = resultSet.getBlob("flyerImg");
            int personalCopy = resultSet.getInt("personalCopy");
            String paymentInfo = resultSet.getString("paymentInformation");
            String invoiceNumber = resultSet.getString("invoiceNumber");
            String comments = resultSet.getString("comments");
            int isFlyerArtApproved = resultSet.getInt("isFlyerArtApproved");
            int isPaymentReceived = resultSet.getInt("isPaymentReceived");
            order = new Order(id, agentId, clientId, flyerQty, personalCopy, isFlyerArtApproved, isPaymentReceived, flyerLayout, paymentInfo, comments, invoiceNumber, flyerImg);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return order;
    }
    
    public boolean deleteOrder(int id) throws SQLException{
        String query = "DELETE FROM orders WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(query);
        statement.setInt(1, id);
        boolean rowsDeleted = statement.executeUpdate() > 0;
        wipeOrderLocationIdRecord(id);
        statement.close();
        disconnect();
        return rowsDeleted;
    }
    
    public boolean updateOrderImage(Order order) throws SQLException{
        String query = "UPDATE orders SET flyerImg = ? WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(query);      
        statement.setBlob(1, order.getFlyerImg());     
        statement.setInt(2, order.getId());
        boolean rowsUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowsUpdated;
    }
    
    
}
