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
public class DeleteToyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            int idToy = Integer.parseInt(request.getParameter("idToy"));
            ToyDAO toyDAO = new ToyDAO();
            Toy deleteToy = toyDAO.deleteToy(idToy);
            if (deleteToy != null) {
                request.setAttribute("DELETE_TOY_SUCCESS", "Added Success");
            } else {
                request.setAttribute("DELETE_TOY_FAILED", "Added Failed");
            }
            
            ArrayList<Toy> toyList = toyDAO.toyList();
            session.setAttribute("TOY_LIST", toyList);
            
            RequestDispatcher rd = request.getRequestDispatcher("ManagerProduct.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DeleteToyController.class.getName()).log(Level.SEVERE, null, ex);
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
