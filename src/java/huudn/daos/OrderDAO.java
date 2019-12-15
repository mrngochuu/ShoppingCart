/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.OrderDTO;
import huudn.utils.DatabaseUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 *
 * @author ngochuu
 */
public class OrderDAO implements Serializable {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(pstm != null) pstm.close();
        if(conn != null) conn.close();
    }
    
    public OrderDTO findOrderByUserID(int userID) throws Exception {
        OrderDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT TOP 1 orderID FROM tblOrders WHERE userID = ? AND isCheckout = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, userID);
                pstm.setBoolean(2, false);
                rs = pstm.executeQuery();
                if(rs.next()) {
                    dto = new OrderDTO();
                    dto.setOrderID(rs.getInt("orderID"));
                    dto.setUserID(userID);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean createOrderByUserID(int userID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "INSERT INTO tblOrders (isCheckout, userID) VALUES (?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, false);
                pstm.setInt(2, userID);
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return true;
    }
    
    public boolean checkOutOrder(int orderID, int total) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "Update tblOrders SET isCheckout = ?, dateCheckout = ?, total = ? WHERE orderID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, true);
                pstm.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                pstm.setInt(3, total);
                pstm.setInt(4, orderID);
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
