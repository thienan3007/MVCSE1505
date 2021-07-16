/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAcountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAcountServlet";
    private final String STARTUP_CONTROLLER = "StartupServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String LOAD_PRODUCT_TO_SHOPPING_PAGE = "LoadProductServlet";
    private final String REMOVE_ITEM_IN_CART = "RemoveItemToCartServlet";
    private final String CHECK_OUT_PAGE = "CheckOutServlet";
    private final String CREATE_ACCOUNT = "CreateAccountSevlet";
    private final String LOG_OUT_SERVLET = "LogOutServlet";

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

        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;

        try {
            if (button == null) {
                url = STARTUP_CONTROLLER;//cookie

            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Item to Your Cart")) {
                url = ADD_ITEM_TO_CART_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_ITEM_IN_CART;
            } else if (button.equals("Shopping Now")) {
                url = LOAD_PRODUCT_TO_SHOPPING_PAGE;
            } else if (button.equals("Out")) {
                url = CHECK_OUT_PAGE;
            } else if (button.equals("Create new Account")) {
                url = CREATE_ACCOUNT;
            } else if (button.equals("Log out")) {
                url = LOG_OUT_SERVLET;
            }

        } finally {

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
