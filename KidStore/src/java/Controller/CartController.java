/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ToyDAO;
import DTO.Toy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class CartController extends HttpServlet {

    private static final String CARTPAGE = "cart.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = CARTPAGE;
        int value = 0;
        HashMap<Toy, Integer> cartList;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("toyId"));
            ToyDAO dao = new ToyDAO();
            Toy item = dao.getToyUsingID(id);
            System.out.println(item);
            cartList = (HashMap<Toy, Integer>) session.getAttribute("cartList");
            String valueParam = request.getParameter("InputValue");
            if (valueParam != null) {
                value = Integer.parseInt(valueParam);
                if(item.getQuantity()<value){
                    request.setAttribute("TOO_MANY", "We don't have enough items in our stock."
                            + " We will set the maximum we have for you.");
                    value = item.getQuantity();
                }
            }
            //không có List thì tạo cái mới
            if (cartList == null) {
                cartList = new HashMap<>();
                if (valueParam != null) {
                    cartList.put(item, value);
                } else {
                    cartList.put(item, 1);
                }
            } else {
                //check xem đã có trong cart chưa, nếu có thì quantity +1
                if (cartList.containsKey(item)) {
                    int currentQuantity = cartList.get(item);
                    if (value == 0) {
                        value = currentQuantity + 1;
                    }
                    cartList.put(item, value);
                } else {
                    cartList.put(item, 1);
                }
            }

            session.setAttribute("cartList", cartList);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
