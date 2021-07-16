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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "CreateAccountSevlet", urlPatterns = {"/CreateAccountSevlet"})
public class CreateAccountSevlet extends HttpServlet {
//    private final String ERROR_PAGE = "createNewAccount.jsp";
//    private final String LOGIN_PAGE = "login.html";

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
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");
        RegistrationCreateErrors createErrors = new RegistrationCreateErrors();
        boolean foundError = false;
        String url = map.get("createNewAccountPage");
        try  {
           //1. Check validation
           if (username.trim().length() < 6 || username.trim().length() > 20) {
               foundError = true;
               createErrors.setUsernameLengthError("Username is required from 6 to 20 characters");
           }
           if (password.trim().length() < 6 || password.trim().length() > 30) {
               foundError = true;
               createErrors.setPasswordLengthError("Pasword is required from 6 to 30 characters");
           } else if (!confirm.trim().equals(password.trim())) {
               foundError = true;
               createErrors.setConfirmNoMatched("Confirm must match password");
           }
           if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
               foundError = true;
               createErrors.setFullNameLengthError("Fullname is required from 2 to 50 characters");
           }
           //2. process
           if (foundError) {
               //. scope errors to 
               request.setAttribute("CREATE_ACCOUNT", createErrors);
           } else {
               //call DAO to ínert
               RegistrationDAO dao = new RegistrationDAO();
               boolean result = dao.createAccount(username, password, fullName, false);
               
               if (result) {
                   //.transfer to login page
                   url = map.get("LOGIN_PAGE");
               } // end if create account successfully
           }// end error occurs 
        } catch(SQLException e) {
            String msg = e.getMessage();
            log("CreateNewAccountServlet _  SQL" + msg);
            if (msg.contains("duplicate")) {
                createErrors.setUsernameIsExisted(username + " existed!!!!");
                request.setAttribute("CREATE_ACCOUNT", createErrors);
            }
        } catch (NamingException e) {
            log("CreateNewAccountServlet _  Naming" + e.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
