
package servlets;

import dbDAOs.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Order;
    
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    OrdersDAO ordersDAO;
    Order order;
    @Override
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        ordersDAO = new OrdersDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageName = request.getPathInfo().substring(1);
        int imageId =  Integer.parseInt(imageName);
        try {
            order = ordersDAO.getOrder(imageId);
            Blob blob = order.getFlyerImg();
            byte[] imgData = blob.getBytes(1, (int)blob.length());
            response.setContentType(getServletContext().getMimeType(imageName));
            response.setContentLength(imgData.length);
            response.getOutputStream().write(imgData);
        } catch (SQLException ex) {
            System.out.println("Something Fail!");
            ex.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
