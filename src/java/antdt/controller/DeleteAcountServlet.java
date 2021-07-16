/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.product.ProductDTO;
import antdt.registration.DeleteAccountErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import antdt.registration.RegistrationDAO;
import antdt.registration.RegistrationDTO;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class DeleteAcountServlet extends HttpServlet {

//    private final String ERROR = "error.html";
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

        String username = request.getParameter("pk");
        String searchValue = request.getParameter("keyword");

        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");
        String url = map.get("searchPageJSP");

        DeleteAccountErrors deleteAccountErrors = new DeleteAccountErrors();

        try {
            HttpSession session = request.getSession();
            RegistrationDTO dto = (RegistrationDTO) session.getAttribute("DTO");
            session.setAttribute("DELETE_ACCOUNT", deleteAccountErrors);

            if (username.equals(dto.getUsername())) {

                deleteAccountErrors.setDeleteMyselfErrors("You cannot delete your own account");
                session.setAttribute("DELETE_ACCOUNT", deleteAccountErrors);
                url = "search?"
                            + "&txtSearchValue=" + searchValue;

            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result1 = dao.deleteAccount(username);

                if (result1) {
                    
                    url = "search?"
                            + "&txtSearchValue=" + searchValue;
                } else {
                    deleteAccountErrors.setDeleteAdminRoleErrors("You cannot delete the admin account");
                    session.setAttribute("DELETE_ACCOUNT", deleteAccountErrors);
                    url = "search?"
                            + "&txtSearchValue=" + searchValue;
                }
            }
        } catch (SQLException ex) {
            log("DeleteAccountServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteAccountServlet _ Naming" + ex.getMessage());
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
            Logger.getLogger(DeleteAcountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteAcountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
