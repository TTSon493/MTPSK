package Controller;

import DAO.OrderDAO;
import DTO.News;
import DTO.OrderHistory;
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
public class ProcessingOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            int orderDetailId = Integer.parseInt(request.getParameter("orderDetailId"));
            OrderDAO orderDAO = new OrderDAO();
            OrderHistory processingOrder = orderDAO.processingOrder(orderDetailId);
            if (processingOrder != null) {
                ArrayList<OrderHistory> orderList = orderDAO.orderHistory();
                session.setAttribute("ORDER_HISTORY", orderList);
            } else {
                session.setAttribute("ORDER_HISTORY_ERROR", "Not Foun List Order");
            }

            RequestDispatcher rd = request.getRequestDispatcher("LoadHistoryProductController");
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
