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
import huudn.dtos.StateDTO;
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
public class AdminSearchProductController extends HttpServlet {

    private static final String SUCCESS = "manageProduct.jsp";
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
            if (listCategory == null) {
                listCategory = categoryDAO.getListCategory();
                session.setAttribute("LIST_CATEGORY", listCategory);
            }

            List<CityDTO> listCity = (List<CityDTO>) session.getAttribute("LIST_CITY");
            if (listCity == null) {
                listCity = cityDAO.getListCity();
                session.setAttribute("LIST_CITY", listCity);
            }

            String titleStr = request.getParameter("txtSearch");
            String categoryStr = request.getParameter("cbCategory");
            String cityStr = request.getParameter("cbCity");
            String stateStr = request.getParameter("cbState");
            if (cityStr != null) {
                if (cityStr.equals("0")) {
                    session.removeAttribute("LIST_STATE");
                }
            }

            if ((titleStr == null && categoryStr == null && cityStr == null && stateStr == null) || (titleStr.equals("") && categoryStr.equals("0")) && cityStr.equals("0") && stateStr.equals("0")) {

                List<RealEstateDTO> listHouse = realEstateDAO.getListRealEstateByCategoryIDWithoutActive(1);
                request.setAttribute("HOUSE", listHouse);
                //get First images of all list
                if (listHouse.size() > 0) {
                    Hashtable<Integer, String> houseImages = new Hashtable<>();
                    for (RealEstateDTO realEstateDTO : listHouse) {
                        String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                        if (imageURL != null) {
                            houseImages.put(realEstateDTO.getRealEstateID(), imageURL);
                        }
                    }
                    request.setAttribute("HOUSE_IMAGE", houseImages);
                }

                List<RealEstateDTO> listVilla = realEstateDAO.getListRealEstateByCategoryIDWithoutActive(2);
                request.setAttribute("VILLA", listVilla);
                if (listVilla.size() > 0) {
                    Hashtable<Integer, String> villaImages = new Hashtable<>();
                    for (RealEstateDTO realEstateDTO : listVilla) {
                        String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                        if (imageURL != null) {
                            villaImages.put(realEstateDTO.getRealEstateID(), imageURL);
                        }
                    }
                    request.setAttribute("VILLA_IMAGE", villaImages);
                }

                List<RealEstateDTO> listApartment = realEstateDAO.getListRealEstateByCategoryIDWithoutActive(3);
                request.setAttribute("APARTMENT", listApartment);
                if (listApartment.size() > 0) {
                    Hashtable<Integer, String> apartmentImages = new Hashtable<>();
                    for (RealEstateDTO realEstateDTO : listApartment) {
                        String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                        if (imageURL != null) {
                            apartmentImages.put(realEstateDTO.getRealEstateID(), imageURL);
                        }
                    }
                    request.setAttribute("APARTMENT_IMAGE", apartmentImages);
                }
            } else {
                // fill in State search
                if (!cityStr.equals("0")) {
                    List<StateDTO> listState = stateDAO.getListState(Integer.parseInt(cityStr));
                    session.setAttribute("LIST_STATE", listState);
                }

                String searchStr = titleStr + "," + categoryStr;
                if (!cityStr.equals("0") && stateStr.equals("0")) {
                    List<StateDTO> listState = stateDAO.getListState(Integer.parseInt(cityStr));
                    for (StateDTO stateDTO : listState) {
                        searchStr += "," + stateDTO.getStateID();
                    }
                } else {
                    searchStr += "," + stateStr;
                }
                List<RealEstateDTO> listRealEstate = realEstateDAO.fintListByTxtSearchWithoutActive(searchStr);
                request.setAttribute("REAL_ESTATE", listRealEstate);
                if (listRealEstate.size() > 0) {
                    Hashtable<Integer, String> realEstateImages = new Hashtable<>();
                    for (RealEstateDTO realEstateDTO : listRealEstate) {
                        String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                        if (imageURL != null) {
                            realEstateImages.put(realEstateDTO.getRealEstateID(), imageURL);
                        }
                    }
                    request.setAttribute("REAL_ESTATE_IMAGE", realEstateImages);
                }
            }
        } catch (Exception e) {
            log("ERROR at AdminSearchProductController: " + e.getMessage());
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
