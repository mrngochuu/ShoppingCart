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
public class SearchProductController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";

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
            RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();
            CityDAO cityDAO = new CityDAO();
            StateDAO stateDAO = new StateDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            
            List<CategoryDTO> listCategory = (List<CategoryDTO>) session.getAttribute("LIST_CATEGORY");
            if(listCategory == null) {
                listCategory = categoryDAO.getListCategory();
                session.setAttribute("LIST_CATEGORY", listCategory);
            }
            
            List<CityDTO> listCity = (List<CityDTO>) session.getAttribute("LIST_CITY");
            if(listCity == null) {
                listCity = cityDAO.getListCity();
                session.setAttribute("LIST_CITY", listCity);
            }
            
            String titleStr = request.getParameter("txtSearch");
            String categoryStr = request.getParameter("cbCategory");
            String cityStr = request.getParameter("cbCity");
            String stateStr = request.getParameter("cbState");
            
            if ((titleStr == null && categoryStr == null && cityStr == null && stateStr == null) || (titleStr.equals("") && categoryStr.equals("0-type")) && cityStr.equals("0-city") && stateStr.equals("0-state")) {
                List<RealEstateDTO> listHouse = realEstateDAO.getListRealEstate(1);
                List<RealEstateDTO> listVilla = realEstateDAO.getListRealEstate(2);
                List<RealEstateDTO> listApartment = realEstateDAO.getListRealEstate(3);
                
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
                request.setAttribute("HOUSE", listHouse);
                request.setAttribute("HOUSE_IMAGE", houseImages);
                request.setAttribute("VILLA", listVilla);
                request.setAttribute("VILLA_IMAGE", villaImages);
                request.setAttribute("APARTMENT", listApartment);
                request.setAttribute("APARTMENT_IMAGE", apartmentImages);
            } else {
                String searchStr = "title:" + titleStr +",category:"+ categoryStr + ",";
                if(cityStr != null) {
                    searchStr += "city:" + cityStr +",";
                }
                if(stateStr != null) {
                    searchStr += "state:" + stateStr;
                }
                
            }
        } catch (Exception e) {
            log("ERROR at SearchProductController: " + e.getMessage());
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
