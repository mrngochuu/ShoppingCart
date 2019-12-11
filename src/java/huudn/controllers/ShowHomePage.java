/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.RealEstateDAO;
import huudn.daos.RealEstateImageDAO;
import huudn.dtos.RealEstateDTO;
import java.io.IOException;
import java.util.Hashtable;
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
public class ShowHomePage extends HttpServlet {

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
            HttpSession session = request.getSession();
            RealEstateDAO realEstateDAO = new RealEstateDAO();
            List<RealEstateDTO> listHouse = realEstateDAO.getListRealEstate(1);
            List<RealEstateDTO> listVilla = realEstateDAO.getListRealEstate(2);
            List<RealEstateDTO> listApartment = realEstateDAO.getListRealEstate(3);
            RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();
            //get First images of all list
            Hashtable<Integer, String> houseImages = new Hashtable<>();
            for (RealEstateDTO realEstateDTO : listHouse) {
                String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                if (imageURL != null) {
                    houseImages.put(realEstateDTO.getRealEstateID(), imageURL);
                }
            }

            Hashtable<Integer, String> villaImages = new Hashtable<>();
            for (RealEstateDTO realEstateDTO : listVilla) {
                String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                if (imageURL != null) {
                    villaImages.put(realEstateDTO.getRealEstateID(), imageURL);
                }
            }

            Hashtable<Integer, String> apartmentImages = new Hashtable<>();
            for (RealEstateDTO realEstateDTO : listApartment) {
                String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                if (imageURL != null) {
                    apartmentImages.put(realEstateDTO.getRealEstateID(), imageURL);
                }
            }
            session.setAttribute("HOUSE", listHouse);
            session.setAttribute("HOUSE_IMAGE", houseImages);
            session.setAttribute("VILLA", listVilla);
            session.setAttribute("VILLA_IMAGE", villaImages);
            session.setAttribute("APARTMENT", listApartment);
            session.setAttribute("APARTMENT_IMAGE", apartmentImages);
        } catch (Exception e) {
            log("ERROR at ShowRealEstate: " + e.getMessage());
        } finally {
            response.sendRedirect("index.jsp");
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
