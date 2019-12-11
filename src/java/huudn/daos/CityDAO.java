/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.CityDTO;
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
public class CityDAO implements Serializable {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(pstm != null) pstm.close();
        if(conn != null) conn.close();
    }
    
    public List<CityDTO> getListCity() throws Exception {
        List<CityDTO> listCity = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT * FROM tblCities";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                listCity = new ArrayList<>();
                while(rs.next()) {
                    CityDTO dto = new CityDTO();
                    dto.setCityID(rs.getInt("cityID"));
                    dto.setCityName(rs.getString("cityName"));
                    listCity.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listCity;
    }
    
    public CityDTO findCityDTObyID(int cityID) throws Exception {
        CityDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT cityName FROM tblCities WHERE cityID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, cityID);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    dto = new CityDTO(cityID, rs.getString("cityName"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
