/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.Order_RealEstateDAO;
import huudn.daos.RealEstateDAO;
import huudn.dtos.OrderDTO;
import huudn.dtos.UserDTO;
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
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ShowProductDetailsController";

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
        try {
            int realEstateID = Integer.parseInt(request.getParameter("realEstateID"));
            RealEstateDAO realEstateDAO = new RealEstateDAO();
            if (realEstateDAO.checkSoldout(realEstateID)) {
                HttpSession session = request.getSession();
                UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                OrderDTO orderDTO = (OrderDTO) session.getAttribute("CART");
                if (orderDTO != null) {
                    Order_RealEstateDAO order_RealEstateDAO = new Order_RealEstateDAO();
                    // check existed in cart
                    if (order_RealEstateDAO.checkExistedInCart(orderDTO.getOrderID(), realEstateID)) {
                        request.setAttribute("MESSAGE", "Already added!");
                    } else {
                        if (order_RealEstateDAO.insertToCart(orderDTO.getOrderID(), realEstateID)) {
                            request.setAttribute("MESSAGE", "Add to your cart is successful!");
                        } else {
                            request.setAttribute("MESSAGE", "Add to your cart is failed!");
                        }
                    }
                }
            } else {
                request.setAttribute("MESSAGE", "The real estate sold out!");
            }
        } catch (Exception e) {
            log("ERROR at AddToCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
