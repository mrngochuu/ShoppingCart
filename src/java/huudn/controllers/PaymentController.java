/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.OrderDAO;
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
public class PaymentController extends HttpServlet {

    private static final String FAILED = "ShowCartController";
    private static final String SUCCESS = "SearchProductController";

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
        String url = FAILED;
        try {
            HttpSession session = request.getSession();
            String listRealEstateStr = request.getParameter("listRealEstate");
            if (!listRealEstateStr.equals("")) {
                String[] temp = listRealEstateStr.split("-");

                //check real estate avaiable
                boolean avaiable = true;
                RealEstateDAO realEstateDAO = new RealEstateDAO();
                for (int i = 0; i < temp.length; i++) {
                    if (!temp[i].equals("")) {
                        if ((avaiable = realEstateDAO.checkSoldout(Integer.parseInt(temp[i]))) == false) {
                            avaiable = false;
                            break;
                        }
                    }
                }

                if (avaiable) {
                    int total = Integer.parseInt(request.getParameter("Total"));
                    OrderDTO orderDTO = (OrderDTO) session.getAttribute("CART");
                    OrderDAO orderDAO = new OrderDAO();
                    if (orderDAO.checkOutOrder(orderDTO.getOrderID(), total)) {
                        //create new cart
                        UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                        if (orderDAO.createOrderByUserID(userDTO.getUserID())) {
                            orderDTO = orderDAO.findOrderByUserID(userDTO.getUserID());
                            session.setAttribute("CART", orderDTO);
                        }
                        //delete product
                        for (int i = 0; i < temp.length; i++) {
                            if (!temp[i].equals("")) {
                                realEstateDAO.delete(Integer.parseInt(temp[i]));
                            }
                        }
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at PaymentController: " + e.getMessage());
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
