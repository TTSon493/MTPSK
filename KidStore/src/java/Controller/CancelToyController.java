package Controller;

import DAO.ToyDAO;
import DTO.Toy;
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
public class CancelToyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            int toyId = Integer.parseInt(request.getParameter("toyId"));
            
            ToyDAO toyDAO = new  ToyDAO();
            Toy cancel = toyDAO.cancelToy(toyId);
            ArrayList<Toy> toyListNotApprove = toyDAO.toyListNotApprove();
            session.setAttribute("TOY_LIST_NOT_APPROVE", toyListNotApprove);
            
            if (cancel != null){
                request.setAttribute("CANCEL_SUCCESS", "Canced");
            } else {
                request.setAttribute("CANCEL_FAILED", "Cance Failed");
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("admin_product.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ApproveCategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
