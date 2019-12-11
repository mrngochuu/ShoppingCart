/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.StateDTO;
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
public class StateDAO implements Serializable {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(pstm != null) pstm.close();
        if(conn != null) conn.close();
    }
    
    public List<StateDTO> getListState(int cityID) throws Exception {
        List<StateDTO> listState = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT stateID, stateName FROM tblStates WHERE cityID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, cityID);
                rs = pstm.executeQuery();
                listState = new ArrayList<>();
                while(rs.next()) {
                    StateDTO dto = new StateDTO();
                    dto.setStateID(rs.getInt("stateID"));
                    dto.setStateName(rs.getString("stateName"));
                    dto.setCityID(cityID);
                    listState.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listState;
    }
    
    public StateDTO findStateByID(int stateID) throws Exception {
        StateDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT stateName, cityID FROM tblStates WHERE stateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, stateID);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    dto = new StateDTO(stateID, rs.getString("stateName"), rs.getInt("cityID"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
