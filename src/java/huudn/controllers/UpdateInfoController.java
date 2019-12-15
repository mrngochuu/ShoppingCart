/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.StateDAO;
import huudn.daos.UserDAO;
import huudn.dtos.StateDTO;
import huudn.dtos.UserDTO;
import huudn.dtos.UserErrorObject;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngochuu
 */
public class UpdateInfoController extends HttpServlet {

    public static final String INVALID = "info.jsp";
    public static final String SUCCESS = "ShowInfoController";

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
            UserDTO userDTO = (UserDTO) session.getAttribute("USER");
            int cityID = Integer.parseInt(request.getParameter("cbCity"));
            int stateID = Integer.parseInt(request.getParameter("cbState"));
            String txtFullname = request.getParameter("txtFullname");
            String txtPhoneNum = request.getParameter("txtPhoneNum");
            String txtEmail = request.getParameter("txtEmail");
            String txtAddress = request.getParameter("txtAddress");
            String txtAvatarURL = request.getParameter("txtAvatarURL");
            
            //set value in form
            UserDTO info = new UserDTO();
            info.setUserID(userDTO.getUserID());
            info.setFullname(txtFullname);
            info.setPhoneNum(txtPhoneNum);
            info.setAddress(txtAddress);
            info.setAvatarURL(txtAvatarURL);
            info.setEmail(txtEmail);
            info.setStateID(stateID);
            StateDAO stateDAO = new StateDAO();
            StateDTO stateDTO = stateDAO.findStateByID(info.getStateID());
            
            if (stateDTO != null) {
                List<StateDTO> listState = stateDAO.getListState(stateDTO.getCityID());
                request.setAttribute("LIST_STATE", listState);
            }

            UserErrorObject errorObj = new UserErrorObject();
            boolean validate = true;

            //fullname
            if (txtFullname.length() > 50) {
                errorObj.setFullnameError("Fullname must contain 1 to 50 characters");
                validate = false;
            }

            if (!txtFullname.matches("[a-zA-Z0-9\\s]*")) {
                errorObj.setFullnameError("Fullname can not contain special characters");
                validate = false;
            }

            if (txtFullname.isEmpty()) {
                errorObj.setFullnameError("Fullname is required");
                validate = false;
            }

            //address
            if (txtAddress.isEmpty()) {
                errorObj.setAddressError("Address is required");
                validate = false;
            }

            if (!txtPhoneNum.matches("0\\d{9}")) {
                errorObj.setPhoneNumError("Phone format is 0xxxxxxxxx");
                validate = false;
            }

            //email
            if (!txtEmail.matches("[a-zA-Z0-9]+([.-_][a-zA-Z0-9]+)*@[a-zA-Z0-9]+[.][a-zA-Z0-9]+([.][a-zA-Z0-9]+)?")) {
                errorObj.setEmailError("Email is invalid");
                validate = false;
            }

            if (cityID == 0) {
                errorObj.setCityError("City must be choosen");
                validate = false;
            }

            if (stateID == 0) {
                errorObj.setStateError("State must be choosen");
                validate = false;
            }

            if (validate) {
                UserDAO userDAO = new UserDAO();
                if (userDAO.updateInfo(info)) {
                    request.setAttribute("MESSAGE", "Update successful!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Update failed!");
                }
            } else {
                request.setAttribute("INFO", info);
                request.setAttribute("INVALID", errorObj);
            }

        } catch (Exception e) {
            log("ERROR at UpdateInfoController: " + e.getMessage());
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
