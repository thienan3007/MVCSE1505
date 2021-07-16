/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.registration.AccountErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import antdt.registration.RegistrationDAO;
import antdt.registration.RegistrationDTO;
import java.util.Map;
import javax.servlet.ServletContext;

public class LoginServlet extends HttpServlet {

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

        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");
        String url = map.get("LoginPageJSP");
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");

        try {
            AccountErrors errorObj = new AccountErrors();
            boolean valid = true;

            if (username.length() == 0) {
                valid = false;
                errorObj.setUsernameNoMatched("Username can not be blank");
            }
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                valid = false;
                errorObj.setUsernameNoMatched("Username is required from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                valid = false;
                errorObj.setPasswordNoMatched("Pasword is required from 6 to 30 characters");
            }
            if (password.length() == 0) {
                valid = false;
                errorObj.setPasswordNoMatched("Password can not be blank");
            }

            if (!valid) {
                request.setAttribute("LOGIN_ACCOUNT", errorObj);
            } else {

                //call DAO method --> new Dao Object, then call Dao 's method
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.checkLogin(username, password);

//                HttpSession session = request.getSession();
                if (!result) {
                    if (!dao.findUsername(username)) {
                        errorObj.setUsernameNotExisted("This username not existed");
                        request.setAttribute("LOGIN_ACCOUNT", errorObj);
                    } else if (!dao.findPassword(password, username)) {
                        errorObj.setPasswordWrong("This password was wrong");
                        request.setAttribute("LOGIN_ACCOUNT", errorObj);
                    } else {
                        errorObj.setUsernameHasBeenRemoved("This account has been removed");
                        request.setAttribute("LOGIN_ACCOUNT", errorObj);
                    }

                } else {
//                    session.setAttribute("WelcomeName", welcomeName);
                    url = map.get("searchPageJSP");
                    HttpSession session = request.getSession();
                    RegistrationDTO dto = dao.searchRegistration(username);
                    session.setAttribute("DTO", dto);
                    //get FullName-lastname from username

//                    session.setAttribute("FULLNAME", fullname);
//                    Cookie cookie = new Cookie(username, password);
//                    cookie.setMaxAge(60 * 5);
//                    response.addCookie(cookie);
                }
            }
        } catch (SQLException ex) {
            log("LoginServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
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
