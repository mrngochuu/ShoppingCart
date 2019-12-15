/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.StateDAO;
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
public class ChangeCityController extends HttpServlet {

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
            int cityID = Integer.parseInt(request.getParameter("cbCity"));
            String txtFullname = request.getParameter("txtFullname");
            String txtPhoneNum = request.getParameter("txtPhoneNum");
            String txtEmail = request.getParameter("txtEmail");
            String txtAddress = request.getParameter("txtAddress");
            String txtAvatarURL = request.getParameter("txtAvatarURL");
            
            UserDTO userDTO = new UserDTO();
            userDTO.setFullname(txtFullname);
            userDTO.setPhoneNum(txtPhoneNum);
            userDTO.setAddress(txtAddress);
            userDTO.setAvatarURL(txtAvatarURL);
            userDTO.setEmail(txtEmail);
            
            request.setAttribute("INFO", userDTO);
            if (cityID != 0) {
                StateDAO stateDAO = new StateDAO();
                List<StateDTO> listState = stateDAO.getListState(cityID);
                request.setAttribute("LIST_STATE", listState);
            }
        } catch (Exception e) {
            log("ERROR at ChangeCityController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("info.jsp").forward(request, response);
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
