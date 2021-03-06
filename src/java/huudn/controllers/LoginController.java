/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.OrderDAO;
import huudn.daos.RoleDAO;
import huudn.daos.UserDAO;
import huudn.dtos.OrderDTO;
import huudn.dtos.RoleDTO;
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
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchProductController";
    private static final String INVALID = "login.jsp";

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
            UserErrorObject errorObj = new UserErrorObject();
            boolean valid = true;
            if (username.isEmpty()) {
                errorObj.setUsernameError("Username is required!");
                valid = false;
            }

            if (password.isEmpty()) {
                errorObj.setPasswordError("Password is required!");
                valid = false;
            }

            if (valid) {
                UserDAO userDAO = new UserDAO();
                int roleID;
                if ((roleID = userDAO.checkLogin(username, password)) != 0) {
                    HttpSession session = request.getSession();
                    url = SUCCESS;
                    RoleDAO roleDAO = new RoleDAO();
                    RoleDTO roleDTO = roleDAO.getRoleNameByID(roleID);
                    UserDTO userDTO = userDAO.findUserIDByUsername(username);
                    if (roleDTO.getRoleName().equals("user")) {
                        OrderDAO orderDAO = new OrderDAO();
                        OrderDTO orderDTO = orderDAO.findOrderByUserID(userDTO.getUserID());
                        //if cart is null create new cart
                        if (orderDTO == null) {
                            if (orderDAO.createOrderByUserID(userDTO.getUserID())) {
                                orderDTO = orderDAO.findOrderByUserID(userDTO.getUserID());
                            }
                        }
                        session.setAttribute("CART", orderDTO);
                    }
                    
                    session.setAttribute("USER", userDTO);
                    session.setAttribute("ROLE", roleDTO);
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", "Invalid username or password!");
                }
            } else {
                url = INVALID;
                request.setAttribute("VALIDATE", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
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
