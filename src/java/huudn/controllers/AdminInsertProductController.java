/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.controllers;

import huudn.daos.RealEstateDAO;
import huudn.daos.RealEstateImageDAO;
import huudn.dtos.RealEstateDTO;
import huudn.dtos.RealEstateErrorObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngochuu
 */
public class AdminInsertProductController extends HttpServlet {

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
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            String area = request.getParameter("area");
            String address = request.getParameter("address");
            String category = request.getParameter("cbCategory");
            String city = request.getParameter("cbCity");
            String state = request.getParameter("cbState");

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
            
            if(description.isEmpty()) {
                errorObj.setDescriptionError("Description is required");
                valid = false;
            }
            
            try {
                int temp = Integer.parseInt(price);
                if(temp < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                errorObj.setPriceError("Price is a numberic and greater than 0");
                valid = false;
            }
            
            try {
                float temp = Float.parseFloat(area);
                if(temp < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                errorObj.setAreaError("Area is a float and greater than 0");
                valid = false;
            }
            
            if(address.isEmpty()) {
                errorObj.setAddressError("Address is required");
                valid = false;
            }
            
            if(category.equals("0")) {
                errorObj.setCategoryError("Choose category");
                valid = false;
            }
            
            if(city.equals("0")) {
                errorObj.setCityError("Choose city");
                valid = false;
            }
            
            if(state.equals("0")) {
                errorObj.setStateError("Choose state");
                valid = false;
            }
            
            if(valid) {
                RealEstateDTO realEstateDTO = new RealEstateDTO();
                realEstateDTO.setTitle(title);
                realEstateDTO.setDescription(description);
                realEstateDTO.setPrice(Integer.parseInt(price));
                realEstateDTO.setArea(Float.parseFloat(area));
                realEstateDTO.setAddress(address);
                realEstateDTO.setStateID(Integer.parseInt(state));
                realEstateDTO.setCategoryID(Integer.parseInt(category));
                
                RealEstateDAO realEstateDAO = new RealEstateDAO();
                int realEstateID = realEstateDAO.insertNewProduct(realEstateDTO);
                if(realEstateID != 0) {
                    RealEstateImageDAO realEstateImageDAO = new RealEstateImageDAO();
                    if(realEstateImageDAO.insert(realEstateID)) {
                        request.setAttribute("MESSAGE", "Insert success");
                    } else {
                        request.setAttribute("MESSAGE", "Insert img failed");
                    }
                } else {
                    request.setAttribute("MESSAGE", "Insert failed");
                }
            } else {
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at AdminInsertProductController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("managePost.jsp").forward(request, response);
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
