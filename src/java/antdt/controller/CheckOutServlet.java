/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cart.CartObject;
import antdt.product.ProductDAO;
import antdt.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    private final String CHECK_OUT_PAGE = "checkOut.jsp";

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
            throws ServletException, IOException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");

        String url = map.get("shoppingPage");
        try {
            ProductDAO dao = new ProductDAO();
            dao.removeAllProductFromOrderDetails();
            //1. Customer goes to cart place
            HttpSession session = request.getSession();
            //2. Customer take a cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart != null) {
                //3. cart existed, Customer take item
//            String name = request.getParameter("cboItem");
                //call dto
//            ProductDAO dao = new ProductDAO();
//            // get dto
//            ProductDTO dto = dao.loadProductDataByName(name);
                //4.Customer drop item to cart
                Map<String, ProductDTO> items = cart.getItems();
                

                for (String key : items.keySet()) {
//                ProductDTO dto = dao.loadProductDataByName(key);
//                dao.insertProductToOrderDetail(String.valueOf(count), key, items.get(key), dto.getPrice()*items.get(key), "abc");
                    dao.insertProductToOrderDetailByProduct(items.get(key));
                }
//            cart.addToCart(name);
                session.removeAttribute("CART");
                
                //5.Customer goes to shopping or loop ack to step
            }

        } catch (SQLException e) {
            log("CheckOutServlet _ SQL" + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
