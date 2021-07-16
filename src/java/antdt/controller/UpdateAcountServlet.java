/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.registration.RegistrationCreateErrors;
import antdt.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateAcountServlet extends HttpServlet {

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String searchValue = request.getParameter("keyword");
        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("chkAdmin"));
        boolean foundError = false;
        RegistrationCreateErrors createErrors = new RegistrationCreateErrors();
        String url = map.get("searchPageJSP");
        try {
            
            HttpSession session = request.getSession();
            session.setAttribute("UPDATE_ACCOUNT", createErrors);
            
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundError = true;
                createErrors.setPasswordLengthError("Password is required from 6 to 30 characters");
            }
            if (foundError) {
                session.setAttribute("UPDATE_ACCOUNT", createErrors);
                url = "search?"
                            + "&txtSearchValue=" + searchValue;
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, password, isAdmin);

                if (result) {
                    url = "search?"
                            + "&txtSearchValue=" + searchValue;
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("UpdateAccountServlet _ SQL" + msg);
            
        } catch (NamingException ex) {
            log("UpdateAccountServlet _ Naming" + ex.getMessage());
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
