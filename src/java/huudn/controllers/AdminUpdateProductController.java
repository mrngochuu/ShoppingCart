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
import huudn.dtos.RealEstateErrorObject;
import huudn.dtos.RealEstateImageDTO;
import huudn.dtos.StateDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngochuu
 */
public class AdminUpdateProductController extends HttpServlet {

    private static final String SUCCESS = "AdminShowProductDetailsController";
    private static final String INVALID = "manageUpdateProduct.jsp";

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
            //set DTO to fill the field
            RealEstateDTO realEstateDTO = new RealEstateDTO();
            int cityID = Integer.parseInt(request.getParameter("cbCity"));
            String realEstateID = request.getParameter("realEstateID");
            realEstateDTO.setRealEstateID(Integer.parseInt(realEstateID));

            String title = request.getParameter("title");
            realEstateDTO.setTitle(title);

            String description = request.getParameter("description");
            realEstateDTO.setDescription(description);

            String price = request.getParameter("price");
            try {
                int temp = Integer.parseInt(price);
                if (temp <= 0) {
                    throw new Exception();
                }
                realEstateDTO.setPrice(temp);
            } catch (Exception e) {
                realEstateDTO.setPrice(0);
            }
            String area = request.getParameter("area");
            try {
                float temp = Float.parseFloat(area);
                if (temp <= 0) {
                    throw new Exception();
                }
                realEstateDTO.setArea(temp);
            } catch (Exception e) {
                realEstateDTO.setArea(0);
            }

            String address = request.getParameter("address");
            realEstateDTO.setAddress(address);

            String active = request.getParameter("cbActive");
            realEstateDTO.setActive(Boolean.parseBoolean(active));

            String category = request.getParameter("cbCategory");
            realEstateDTO.setCategoryID(Integer.parseInt(category));

            String state = request.getParameter("cbState");
            realEstateDTO.setStateID(Integer.parseInt(state));
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("postTime"));
            realEstateDTO.setPostTime(date);

            request.setAttribute("REAL_ESTATE", realEstateDTO);

            RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();
            List<RealEstateImageDTO> realEstateImageDTO = realEstateImageDAO.getListImage(realEstateDTO.getRealEstateID());
            request.setAttribute("IMAGES", realEstateImageDTO);

            if (cityID != 0) {
                StateDAO stateDAO = new StateDAO();
                List<StateDTO> listState = stateDAO.getListState(cityID);
                request.setAttribute("LIST_STATE", listState);
            }

            //valid
            RealEstateErrorObject errorObj = new RealEstateErrorObject();
            boolean valid = true;
            if (title.isEmpty()) {
                errorObj.setTitleError("Title is required");
                valid = false;
            }

            if (title.length() > 200) {
                errorObj.setTitleError("Title contains 200 characters");
                valid = false;
            }

            if (description.isEmpty()) {
                errorObj.setDescriptionError("Description is required");
                valid = false;
            }

            try {
                int temp = Integer.parseInt(price);
                if (temp < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                errorObj.setPriceError("Price is a numberic and greater than 0");
                valid = false;
            }

            try {
                float temp = Float.parseFloat(area);
                if (temp < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                errorObj.setAreaError("Area is a float and greater than 0");
                valid = false;
            }

            if (address.isEmpty()) {
                errorObj.setAddressError("Address is required");
                valid = false;
            }

            if (category.equals("0")) {
                errorObj.setCategoryError("Choose category");
                valid = false;
            }

            if (cityID == 0) {
                errorObj.setCityError("Choose city");
                valid = false;
            }

            if (state.equals("0")) {
                errorObj.setStateError("Choose state");
                valid = false;
            }

            if (valid) {
                RealEstateDAO realEstateDAO = new RealEstateDAO();
                if (realEstateDAO.update(realEstateDTO)) {
                    request.setAttribute("MESSAGE", "Update success");
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Update failed");
                }
            } else {
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at AdminInsertProductController: " + e.getMessage());
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
