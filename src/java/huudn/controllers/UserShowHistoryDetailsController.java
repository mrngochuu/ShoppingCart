/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.Order_RealEstateDAO;
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

/**
 *
 * @author ngochuu
 */
public class UserShowHistoryDetailsController extends HttpServlet {

    private static final String SUCCESS = "userHistoryDetails.jsp";
    private static final String FAILED = "UserShowHistoryController";

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
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            if (orderID != 0) {
                RealEstateDAO realEstateDAO = new RealEstateDAO();
                Order_RealEstateDAO order_RealEstateDAO = new Order_RealEstateDAO();
                List<Integer> listRealEstateID = order_RealEstateDAO.getListProduct(orderID);
                List<RealEstateDTO> listRealEstate = realEstateDAO.getListRealEstateByID(listRealEstateID);
                request.setAttribute("REAL_ESTATE", listRealEstate);
                if (listRealEstate.size() > 0) {
                    RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();
                    Hashtable<Integer, String> realEstateImages = new Hashtable<>();
                    for (RealEstateDTO realEstateDTO : listRealEstate) {
                        String imageURL = realEstateImageDAO.getFirstImage(realEstateDTO.getRealEstateID());
                        if (imageURL != null) {
                            realEstateImages.put(realEstateDTO.getRealEstateID(), imageURL);
                        }
                    }
                    request.setAttribute("REAL_ESTATE_IMAGE", realEstateImages);
                }
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "Cannot show order details!");
            }
        } catch (Exception e) {
            log("ERROR at UserShowHistoryDetailsController: " + e.getMessage());
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
