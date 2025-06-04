/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.LoginDAO;
import DAO.NewsDAO;
import DAO.OrderDAO;
import DAO.ToyDAO;
import DTO.Account;
import DTO.Category;
import DTO.News;
import DTO.OrderHistory;
import DTO.Toy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class LoginController extends HttpServlet {

    private final static String LOGINPAGE = "login.jsp";
    private static final String HOMEPAGE = "MainController";
    private static final String ADMIN = "AccountController";
    private static final String STAFF = "ManagerProduct.jsp";

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
        String url = HOMEPAGE;
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            //Lấy username và password
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            LoginDAO dao = new LoginDAO();
            //check có đúng tài khoản ko
            Account acc = dao.Login(username, password);
            ToyDAO toyDAO = new ToyDAO();
            ArrayList<Toy> toyList = toyDAO.toyList();
            ArrayList<Toy> toyListNotApprove = toyDAO.toyListNotApprove();
            //Lấy danh sách Category approve
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> categoryList = categoryDAO.categoryList();

            //Lấy danh sách Category not approve
            ArrayList<Category> categoryListNotApprove = categoryDAO.categoryListNotApprove();

            //Lấy danh sách Category All
            ArrayList<Category> categoryListAll = categoryDAO.categoryListAll();

            //Lấy danh sách News
            NewsDAO newsDAO = new NewsDAO();
            ArrayList<News> newsList = newsDAO.newsList();

            //Lấy danh sách OrderHistory
            OrderDAO orderDAO = new OrderDAO();
            ArrayList<OrderHistory> orderList = orderDAO.orderHistory();

            session.setAttribute("TOY_LIST", toyList);
            session.setAttribute("CATEGORY_LIST", categoryList);
            session.setAttribute("CATEGORY_LIST_ALL", categoryListAll);
            session.setAttribute("CATEGORY_LIST_NOT_APPROVE", categoryListNotApprove);
            session.setAttribute("TOY_LIST_NOT_APPROVE", toyListNotApprove);
            session.setAttribute("NEWS_LIST", newsList);
            //đúng trả về home
            if (acc != null) {
                session.setAttribute("acc", acc);
                if (acc.isActive()) {
                    if (acc.getRoleId() == 1) {
                        url = ADMIN;
                    } else if (acc.getRoleId() == 2) {
                        session.setAttribute("ORDER_HISTORY", orderList);
                        url = STAFF;
                    } else if (acc.getRoleId() == 3) {
                        url = HOMEPAGE;
                    }
                } else {
                    request.setAttribute("LOGIN_ERROR", "Your Account has been banned");
                    url = LOGINPAGE;
                }
            } //sai trả về login và báo lỗi
            else {
                request.setAttribute("LOGIN_ERROR", "Username or password is incorrect");
                url = LOGINPAGE;
            }
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
