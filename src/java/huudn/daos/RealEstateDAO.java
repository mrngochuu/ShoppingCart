/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.RealEstateDTO;
import huudn.utils.DatabaseUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ngochuu
 */
public class RealEstateDAO implements Serializable {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(pstm != null) pstm.close();
        if(conn != null) conn.close();
    }
    
    public List<RealEstateDTO> getListRealEstate(int categoryID) throws Exception {
        List<RealEstateDTO> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT TOP 4 realEstateID, title, description, area, price FROM tblRealEstates WHERE categoryID = ? AND isactive = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, categoryID);
                pstm.setBoolean(2, true);
                rs = pstm.executeQuery();
                list = new ArrayList<>();
                while(rs.next()) {
                    RealEstateDTO dto = new RealEstateDTO();
                    dto.setRealEstateID(rs.getInt("realEstateID"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDescription(rs.getString("description"));
                    dto.setArea(rs.getFloat("area"));
                    dto.setPrice(rs.getInt("price"));
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public RealEstateDTO findRealEstateByID(int realEstateID) throws Exception {
        RealEstateDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT * FROM tblRealEstates WHERE realEstateID = ? AND isactive = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, realEstateID);
                pstm.setBoolean(2, true);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String address = rs.getString("address");
                    float area = rs.getFloat("area");
                    Date postTime = new Date(rs.getDate("postTime").getTime());
                    int price = rs.getInt("price");
                    int stateID = rs.getInt("stateID");
                    int categoryID = rs.getInt("categoryID");
                    dto = new RealEstateDTO(realEstateID, price, title, description, address, area, true, stateID, postTime, categoryID);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
