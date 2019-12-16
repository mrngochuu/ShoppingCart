/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngochuu
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String REGISTER = "RegisterController";
    private static final String LOGOUT = "LogoutController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String SEARCH_PRODUCT = "SearchProductController";
    private static final String UPDATE_INFO = "UpdateInfoController";
    private static final String CHANGE_CITY = "ChangeCityController";
    private static final String PAYMENT = "PaymentController";
    private static final String DELETE_PRODUCT = "DeleteProductController";
    private static final String DELETE_FROM_CART = "DeleteFromCartController";
    private static final String SHOW_PRODUCT_DETAILS = "ShowProductDetailsController";
    private static final String SHOW_CART = "ShowCartController";
    private static final String SHOW_INFO = "ShowInfoController";
    private static final String CHANGE_PASSWORD = "ChangePasswordController";
    private static final String ADMIN_CHANGE_CITY = "AdminChangeCityController";
    private static final String ADMIN_SEARCH_PRODUCT = "AdminSearchProductController";
    private static final String ADMIN_INSERT_PRODUCT = "AdminInsertProductController";
    private static final String ADMIN_SEARCH_USER = "AdminSearchUserController";
    private static final String ADMIN_UPDATE_USER = "AdminUpdateUserController";
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
            String action = request.getParameter("action");
            
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("SearchProduct")) {
                url = SEARCH_PRODUCT;
            } else if (action.equals("Register")) {
                url = REGISTER;
            } else if (action.equals("ShowInfo")) {
                url = SHOW_INFO;
            } else if (action.equals("UpdateInfo")) {
                url = UPDATE_INFO;
            } else if (action.equals("AdminSearchProduct")) {
                url = ADMIN_SEARCH_PRODUCT;
            } else if (action.equals("AdminInsertProduct")) {
                url = ADMIN_INSERT_PRODUCT;
            } else if (action.equals("AdminSearchUser")) {
                url = ADMIN_SEARCH_USER;
            } else if (action.equals("AdminUpdateUser")) {
                url = ADMIN_UPDATE_USER;
            } else if (action.equals("AdminChangeCity")) {
                url = ADMIN_CHANGE_CITY;
            } else if (action.equals("ChangePassword")) {
                url = CHANGE_PASSWORD;
            } else if (action.equals("ShowProductDetails")) {
                url = SHOW_PRODUCT_DETAILS;
            } else if (action.equals("AddToCart")) {
                url = ADD_TO_CART;
            } else if (action.equals("ShowCart")) {
                url = SHOW_CART;
            } else if (action.equals("Payment")) {
                url = PAYMENT;
            } else if (action.equals("DeleteFromCart")) {
                url = DELETE_FROM_CART;
            } else if (action.equals("DeleteProduct")) {
                url = DELETE_PRODUCT;
            } else if (action.equals("ChangeCity")) {
                url = CHANGE_CITY;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else {
                request.setAttribute("ERROR", "Action is invalid!");
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
