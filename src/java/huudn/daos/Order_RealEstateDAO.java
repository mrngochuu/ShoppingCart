/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

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
public class Order_RealEstateDAO implements Serializable {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(pstm != null) pstm.close();
        if(conn != null) conn.close();
    }
    
    public boolean insertToCart(int orderID, int realEstateID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "INSERT INTO tblOrders_RealEstates VALUES (?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, orderID);
                pstm.setInt(2, realEstateID);
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean checkExistedInCart(int orderID, int realEstateID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT realEstateID FROM tblOrders_RealEstates WHERE orderID = ? AND realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, orderID);
                pstm.setInt(2, realEstateID);
                rs = pstm.executeQuery();
                check = rs.next();
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<Integer> getListProduct (int orderID) throws Exception {
        List<Integer> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT realEstateID FROM tblOrders_RealEstates WHERE orderID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, orderID);
                rs = pstm.executeQuery();
                list = new ArrayList<>();
                while(rs.next()) {
                    list.add(rs.getInt("realEstateID"));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean deleteProduct(int orderID, int realEstateID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if(conn != null) {
                String sql = "DELETE FROM tblOrders_RealEstates WHERE orderID = ? AND realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, orderID);
                pstm.setInt(2, realEstateID);
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
