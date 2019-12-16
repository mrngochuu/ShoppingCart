/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.RoleDTO;
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
public class RoleDAO implements Serializable {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(pstm != null) pstm.close();
        if(conn != null) conn.close();
    }
    
    public List<RoleDTO> getListRole() throws Exception {
        List<RoleDTO> listRole = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT * FROM tblRoles";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                listRole = new ArrayList<>();
                while(rs.next()) {
                    RoleDTO dto = new RoleDTO();
                    dto.setRoleID(rs.getInt("roleID"));
                    dto.setRoleName(rs.getString("roleName"));
                    listRole.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listRole;
    }
    
    public RoleDTO getRoleIDByName(String roleName) throws Exception {
        RoleDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT roleID FROM tblRoles WHERE roleName = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, roleName);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    dto = new RoleDTO(rs.getInt("roleID"), roleName);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public RoleDTO getRoleNameByID(int roleID) throws Exception {
        RoleDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT roleName FROM tblRoles WHERE roleID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, roleID);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    dto = new RoleDTO(roleID, rs.getString("roleName"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
