/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.CityDAO;
import huudn.daos.OrderDAO;
import huudn.daos.RoleDAO;
import huudn.daos.StateDAO;
import huudn.daos.UserDAO;
import huudn.dtos.CityDTO;
import huudn.dtos.OrderDTO;
import huudn.dtos.RoleDTO;
import huudn.dtos.StateDTO;
import huudn.dtos.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngochuu
 */
public class AdminShowInfoUserController extends HttpServlet {

    private static final String SUCCESS = "manageShowUser.jsp";
    private static final String FAILED = "AdminSearchUserController";

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
            int userID = Integer.parseInt(request.getParameter("userID"));
            if (userID != 0) {
                UserDAO userDAO = new UserDAO();
                UserDTO userDTO = userDAO.getAllInfoByID(userID);
                if (userDTO != null) {
                    StateDTO stateDTO = null;
                    if (userDTO.getStateID() != 0) {
                        StateDAO stateDAO = new StateDAO();
                        stateDTO = stateDAO.findStateByID(userDTO.getStateID());
                        request.setAttribute("STATE_USER", stateDTO);
                    }
                    if (stateDTO != null) {
                        CityDAO cityDAO = new CityDAO();
                        CityDTO cityDTO = cityDAO.findCityDTObyID(stateDTO.getCityID());
                        request.setAttribute("CITY_USER", cityDTO);
                    }
                    RoleDAO roleDAO = new RoleDAO();
                    RoleDTO roleDTO = roleDAO.getRoleNameByID(userDTO.getRoleID());

                    request.setAttribute("INFO_USER", userDTO);
                    request.setAttribute("ROLE_USER", roleDTO);
                    
                    OrderDAO orderDAO = new OrderDAO();
                    List<OrderDTO> listOrderDTOs = orderDAO.findHistoryByUserID(userID);
                    request.setAttribute("LIST_ORDER", listOrderDTOs);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Cannot find User");
                }
            } else {
                request.setAttribute("MESSAGE", "Cannot find User");
            }
        } catch (Exception e) {
            log("ERROR at AdminShowInfoUserController: " + e.getMessage());
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
