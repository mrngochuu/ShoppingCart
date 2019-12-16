/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.UserDAO;
import huudn.dtos.UserDTO;
import huudn.dtos.UserErrorObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngochuu
 */
public class ChangePasswordController extends HttpServlet {

    private static final String SUCCESS = "ShowInfoController";
    private static final String INVALID = "password.jsp";

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
        String url = INVALID;
        try {
            HttpSession session = request.getSession();
            UserDAO userDAO = new UserDAO();
            UserDTO userDTO = (UserDTO) session.getAttribute("USER");

            String oldPassword = request.getParameter("txtOldPassword");
            String newPassword = request.getParameter("txtNewPassword");
            String confirm = request.getParameter("txtConfirm");

            UserErrorObject errorObj = new UserErrorObject();
            boolean valid = true;

            if (oldPassword.isEmpty()) {
                errorObj.setOldPasswordError("Input old password!");
                valid = false;
            }

            if (!newPassword.matches("[a-zA-Z0-9]*")) {
                errorObj.setPasswordError("Password can not contain special characters");
                valid = false;
            }

            if (newPassword.length() < 6 || newPassword.length() > 20) {
                errorObj.setPasswordError("Password must contain from 6 to 20 characters!");
                valid = false;
            }

            if (!newPassword.isEmpty() && !confirm.equals(newPassword)) {
                errorObj.setConfirmError("Retype password is wrong!");
                valid = false;
            }

            if (valid) {
                int roleID = userDAO.checkLogin(userDTO.getUsername(), oldPassword);
                if (roleID == 0) {
                    request.setAttribute("MESSAGE", "Old password is wrong!");
                } else {
                    userDTO.setPassword(newPassword);
                    if(userDAO.updatePassword(userDTO)) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("MESSAGE", "Update password failed!");
                    }
                }
            } else {
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at ChangePasswordController: " + e.getMessage());
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
