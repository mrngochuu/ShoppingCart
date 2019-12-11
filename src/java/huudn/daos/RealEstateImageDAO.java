/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.RealEstateImageDTO;
import huudn.utils.DatabaseUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngochuu
 */
public class RealEstateImageDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public String getFirstImage(int realEstateID) throws Exception {
        String image = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 1 imageURL FROM tblRealEstateImages WHERE realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, realEstateID);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    image = rs.getString("imageURL");
                }
            }
        } finally {
            closeConnection();
        }
        return image;
    }

    public List<RealEstateImageDTO> getListImage(int realEstateID) throws Exception {
        List<RealEstateImageDTO> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT realEstateImageID, imageURL FROM tblRealEstateImages WHERE realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, realEstateID);
                rs = pstm.executeQuery();
                list = new ArrayList<>();
                while(rs.next()) {
                    RealEstateImageDTO dto = new RealEstateImageDTO(rs.getInt("realEstateImageID"), rs.getString("imageURL"), realEstateID);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
