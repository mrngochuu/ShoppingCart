/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.RealEstateDAO;
import huudn.daos.RealEstateImageDAO;
import huudn.daos.StateDAO;
import huudn.dtos.RealEstateDTO;
import huudn.dtos.RealEstateImageDTO;
import huudn.dtos.StateDTO;
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
public class AdminShowProductDetailsController extends HttpServlet {

    private static final String SUCCESS = "manageUpdateProduct.jsp";
    private static final String FAILED = "AdminSearchProduct.jsp";

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
            String realEstateID = request.getParameter("realEstateID");
            if (!realEstateID.equals("0")) {
                RealEstateDAO realEstateDAO = new RealEstateDAO();
                RealEstateDTO realEstateDTO = realEstateDAO.findRealEstateByIDWithoutActive(Integer.parseInt(realEstateID));
                if (realEstateDTO != null) {
                    //get image
                    RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();
                    List<RealEstateImageDTO> realEstateImageDTO = realEstateImageDAO.getListImage(realEstateDTO.getRealEstateID());
                    request.setAttribute("IMAGES", realEstateImageDTO);
                    
                    //get list state
                    StateDAO stateDAO = new StateDAO();
                    StateDTO stateDTO = stateDAO.findStateByID(realEstateDTO.getStateID());
                    if (stateDTO != null) {
                        List<StateDTO> list_State = stateDAO.getListState(stateDTO.getCityID());
                        request.setAttribute("LIST_STATE", list_State);
                    }
                    
                    request.setAttribute("REAL_ESTATE", realEstateDTO);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Cannot find Real Estate");
                }
            } else {
                request.setAttribute("MESSAGE", "Cannot see Details");
            }
        } catch (Exception e) {
            log("ERROR at AdminShowProductDetailsController: " + e.getMessage());
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
