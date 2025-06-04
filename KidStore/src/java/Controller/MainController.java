/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.LoginDAO;
import DAO.NewsDAO;
import DAO.ToyDAO;
import DTO.Category;
import DTO.News;
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
public class MainController extends HttpServlet {
    private final static String HOMEPAGE = "home.jsp";
    private final static String ERRORPAGE = "error.jsp";
    private final static String LOGIN = "LoginController";
    private final static String SIGNUP = "SignUpController";
    private final static String CART = "CartController";
    private final static String ORDER = "OrderController";
    private final static String DETAIL = "DetailController";
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String url = ERRORPAGE;
        try {
            String action = request.getParameter("btAction");
            if(action!=null){
            switch (action) {
                case "Sign in": {
                    url = LOGIN;
                    break;
                }
                case "Sign up": {
                    url = SIGNUP;
                    break;
                }
                case "Sell": {
                    url = CART;
                    break;
                }
                case "Create":{
                    url = ORDER;
                    break;
                }
                case "Detail" :{
                    url = DETAIL;
                    break;
                }
            }
            }
            else {
            ToyDAO toyDAO = new ToyDAO();
            ArrayList<Toy> toyList = toyDAO.toyList();
            //Lấy danh sách Category
            CategoryDAO categeoryDAO = new CategoryDAO();
            ArrayList<Category> categoryList = categeoryDAO.categoryList();
            session.setAttribute("TOY_LIST", toyList);
            session.setAttribute("CATEGORY_LIST", categoryList);
            
            
            url = HOMEPAGE;
            }
            //Lấy danh sách News
            NewsDAO newsDAO = new NewsDAO();
            ArrayList<News> newsList = newsDAO.newsList();
            session.setAttribute("NEWS_LIST", newsList);
            
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
