/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.RoleDAO;
import huudn.daos.UserDAO;
import huudn.dtos.RoleDTO;
import huudn.dtos.UserErrorObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngochuu
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "login.jsp";
    private static final String INVALID = "register.jsp";

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
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            UserDAO userDAO = new UserDAO();
            UserErrorObject errorObj = new UserErrorObject();
            boolean valid = true;

            if (!username.matches("[a-zA-Z0-9]*")) {
                errorObj.setUsernameError("Username can not contain special characters");
                valid = false;
            }

            if (username.length() < 6 || username.length() > 20) {
                errorObj.setUsernameError("Username must contain from 6 to 20 characters");
                valid = false;
            }

            if (userDAO.checkExistedUsername(username)) {
                errorObj.setUsernameError("Username is existed!");
                valid = false;
            }

            if (!password.matches("[a-zA-Z0-9]*")) {
                errorObj.setPasswordError("Password can not contain special characters");
                valid = false;
            }

            if (password.length() < 6 || password.length() > 20) {
                errorObj.setPasswordError("Password must contain from 6 to 20 characters!");
                valid = false;
            }

            if (!password.isEmpty() && !confirm.equals(password)) {
                errorObj.setConfirmError("Retype password is wrong!");
                valid = false;
            }

            if (valid) {
                RoleDAO roleDAO = new RoleDAO();
                RoleDTO roleDTO = roleDAO.getRoleIDByName("user");
                if (userDAO.createAccount(username, password, roleDTO.getRoleID())) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Create the account successful! Login now!");
                } else {
                    request.setAttribute("ERROR", "Register Account failed!");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at RegisterController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
