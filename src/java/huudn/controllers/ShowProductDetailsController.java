/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.CategoryDAO;
import huudn.daos.CityDAO;
import huudn.daos.RealEstateDAO;
import huudn.daos.RealEstateImageDAO;
import huudn.daos.StateDAO;
import huudn.dtos.CategoryDTO;
import huudn.dtos.CityDTO;
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
public class ShowProductDetailsController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "productDetails.jsp";

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
            int realEstateID = Integer.parseInt(request.getParameter("realEstateID"));
            if (realEstateID > 0) {
                url = SUCCESS;
                RealEstateDAO realEstateDAO = new RealEstateDAO();
                RealEstateDTO realEstateDTO = realEstateDAO.findRealEstateByID(realEstateID);
                if (realEstateDTO == null) {
                    request.setAttribute("MESSAGE", "PRODUCT SOLD OUT!");
                } else {
                    StateDAO stateDAO = new StateDAO();
                    CityDAO cityDAO = new CityDAO();
                    CategoryDAO categoryDAO = new CategoryDAO();
                    RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();

                    StateDTO stateDTO = stateDAO.findStateByID(realEstateDTO.getStateID());
                    CityDTO cityDTO = cityDAO.findCityDTObyID(stateDTO.getCityID());
                    CategoryDTO categoryDTO = categoryDAO.findCategoryByID(realEstateDTO.getCategoryID());
                    List<RealEstateImageDTO> listImage = realEstateImageDAO.getListImage(realEstateID);
                    
                    request.setAttribute("REAL_ESTATE_DTO", realEstateDTO);
                    request.setAttribute("STATE", stateDTO);
                    request.setAttribute("CITY", cityDTO);
                    request.setAttribute("IMAGES", listImage);
                }
            } else {
                request.setAttribute("ERROR", "REAL ESTATE IS NOT EXISTED!");
            }
        } catch (Exception e) {
            log("ERROR at ShowProductDetailsController: " + e.getMessage());
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
