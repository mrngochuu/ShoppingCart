/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.RoleDTO;
import huudn.dtos.UserDTO;
import huudn.utils.DatabaseUtils;
import huudn.utils.MyUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ngochuu
 */
public class UserDAO implements Serializable {

    private static final String DEFAULT_IMG = "img_default.png";

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

    public boolean createAccount(String username, String password, int roleID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUsers (username, password, roleID, avatarURL, isActive) VALUES (?,?,?,?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, MyUtils.generateHash(password));
                pstm.setInt(3, roleID);
                pstm.setString(4, DEFAULT_IMG);
                pstm.setBoolean(5, true);
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkExistedUsername(String username) throws Exception {
        //true means the username was existed
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 1 userID FROM tblUsers WHERE username = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                check = rs.next();
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public int checkLogin(String username, String password) throws Exception {
        int roleID = 0;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT roleID FROM tblUsers WHERE username = ? AND password = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, MyUtils.generateHash(password));
                rs = pstm.executeQuery();
                if (rs.next()) {
                    roleID = rs.getInt("roleID");
                }
            }
        } finally {
            closeConnection();
        }
        return roleID;
    }
    
    public UserDTO findUserIDByUsername(String username) throws Exception {
        UserDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT userID FROM tblUsers WHERE username = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    dto = new UserDTO();
                    dto.setUserID(rs.getInt("userID"));
                    dto.setUsername(username);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
