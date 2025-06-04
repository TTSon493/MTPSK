package Controller;

import DAO.OrderDAO;
import DTO.OrderWarranty;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trant
 */
public class ShippingWarrantyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            int orderDetailId = Integer.parseInt(request.getParameter("orderDetailId"));
            OrderDAO orderDAO = new OrderDAO();
            OrderWarranty processingWarranty = orderDAO.shippingWarranty(orderDetailId);
            if (processingWarranty != null) {
                ArrayList<OrderWarranty> warrantyList = orderDAO.orderWarranty();
                session.setAttribute("ORDER_WARRANTY", warrantyList);
            } else {
                session.setAttribute("ORDER_WARRANTY_ERROR", "Not Foun List Warranty Order");
            }

            RequestDispatcher rd = request.getRequestDispatcher("ManagerOther.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProcessingOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
