/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import antdt.registration.RegistrationDAO;
import antdt.registration.RegistrationDTO;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SearchLastNameServlet", urlPatterns = {"/SearchLastNameServlet"})
public class SearchLastNameServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_RESULT_PAGE = "search.jsp";
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
        
        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");

        String searchValue = request.getParameter("txtSearchValue");

        String url = map.get("searchPageJSP");

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("DTO") != null) {
                if (!searchValue.trim().isEmpty()) {
                    //call DAO
                    RegistrationDAO dao = new RegistrationDAO();
                    dao.searchLastname(searchValue);

                    List<RegistrationDTO> result = dao.getAccountList();
                    url = map.get("searchPageJSP");
                    request.setAttribute("SEARCH_RESULT", result);
                }//end searchValue has input 
            } else {
                url = map.get("LOGIN_PAGE");
            }

        } catch (SQLException ex) {
            log("SearchLastNameServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLastNameServlet _ Naming" + ex.getMessage());
        } finally {
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
