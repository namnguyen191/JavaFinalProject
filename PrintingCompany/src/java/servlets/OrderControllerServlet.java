
package servlets;

import dbDAOs.LocationDAO;
import dbDAOs.OrdersDAO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;
import models.Location;
import models.Order;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@MultipartConfig(maxFileSize = 999999999)
public class OrderControllerServlet extends HttpServlet {
    
    OrdersDAO ordersDAO;
    LocationDAO locationsDAO;
    List<String> listAllLocations;
    
    @Override
    public void init() {
        try {
            String jdbcURL = getServletContext().getInitParameter("jdbcURL");
            String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
            String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
            ordersDAO = new OrdersDAO(jdbcURL, jdbcUsername, jdbcPassword);
            locationsDAO = new LocationDAO(jdbcURL, jdbcUsername, jdbcPassword);
            listAllLocations = new ArrayList();
            for(Location loc : locationsDAO.listAllLocations()){
                listAllLocations.add(loc.getLocationName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String action = "";
       
       if(request.getParameter("action")!=null)
       action = request.getParameter("action");
//       System.out.println("The action is: " + action);
       try {
           switch(action){
                case "view":
                    viewDetails(request, response);
                    break;
                case "update":
                    updateOrder(request, response);
                    break;
                case "delete":
                    deleteOrder(request, response);
                    break;
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertOrder(request, response);
                    break;
                default:
                    listOrders(request, response);
                    break;
           }
            } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
            
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order> listOrders = ordersDAO.listAllOrders();
        request.setAttribute("listOrders", listOrders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOrdersList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        request.setAttribute("listAllLocations", listAllLocations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOrderForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void viewDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = ordersDAO.getOrder(id);
        order.setLocations(ordersDAO.getOrderLocation(id, locationsDAO));
        request.setAttribute("listAllLocations", listAllLocations);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOrderForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<String> orderLocations = Arrays.asList(request.getParameterValues("locations"));
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
        int isFlyerArtApproved = Integer.parseInt(request.getParameter("isFlyerArtApproved"));
        int isPaymentReceived = Integer.parseInt(request.getParameter("isPaymentReceived"));
        String flyerLayout = request.getParameter("flyerLayout");
        String paymentInfo = request.getParameter("paymentInfo");
        String comments = request.getParameter("comments");
        String invoiceNo = request.getParameter("invoiceNo");
        Part filePart = request.getPart("uploadFile");
        InputStream flyerImg =  filePart.getInputStream();
        Order order =  new Order(agentId, clientId, flyerQty, personalCopy, isFlyerArtApproved, isPaymentReceived, flyerLayout, paymentInfo, comments, invoiceNo);
        order.setLocations(orderLocations);        
        //Insert the record to the database using the method from the DAO
        int id = ordersDAO.insertOrder(order, flyerImg);
        ordersDAO.addOrderLocationId(id, orderLocations, locationsDAO);
        response.sendRedirect("OrderControllerServlet");
    }
    
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ordersDAO.wipeOrderLocationIdRecord(id);
        ordersDAO.deleteOrder(id);
        response.sendRedirect("OrderControllerServlet");
    }
    
    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<String> orderLocations = Arrays.asList(request.getParameterValues("locations"));      
        int id = Integer.parseInt(request.getParameter("id"));
        Order orderToGetImage = ordersDAO.getOrder(id);
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
        int isFlyerArtApproved = Integer.parseInt(request.getParameter("isFlyerArtApproved"));
        int isPaymentReceived = Integer.parseInt(request.getParameter("isPaymentReceived"));
        Part filePart = request.getPart("uploadFile");
        InputStream flyerImg =  filePart.getInputStream();
        if(flyerImg.available() == 0){flyerImg = (orderToGetImage.getFlyerImg()).getBinaryStream();}
        String flyerLayout = request.getParameter("flyerLayout");
        String paymentInfo = request.getParameter("paymentInfo");
        String comments = request.getParameter("comments");
        String invoiceNo = request.getParameter("invoiceNo");
        Order order = new Order(agentId, clientId, flyerQty, personalCopy, isFlyerArtApproved, isPaymentReceived, flyerLayout, paymentInfo, comments, invoiceNo);
        order.setId(id);
        ordersDAO.wipeOrderLocationIdRecord(id);
        ordersDAO.addOrderLocationId(id, orderLocations, locationsDAO);
        boolean updateSuc = ordersDAO.updateOrder(order, flyerImg);
//        if(updateSuc){
//            System.out.println("Update Successfully");
//        } else {
//            System.out.println("Update Fail");
//        }
        response.sendRedirect("OrderControllerServlet");
    }

}
