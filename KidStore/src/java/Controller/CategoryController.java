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
public class CategoryController extends HttpServlet {

    private static final String HOMEPAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            response.setContentType("text/html;charset=UTF-8");
            String url = HOMEPAGE;
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            
            ToyDAO toyDAO = new ToyDAO();
            ArrayList<Toy> toyCategory = toyDAO.toyCategoryById(categoryId);
            if(toyCategory != null){
                request.setAttribute("TOY_CATEGORY_LIST", toyCategory);
            } else {
                request.setAttribute("TOY_CATEGORY_LIST_ERROR", "Toy Not Found");
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
