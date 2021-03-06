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
import java.util.Calendar;
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

    public List<RealEstateDTO> getListRealEstateByCategoryID(int categoryID) throws Exception {
        List<RealEstateDTO> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 4 realEstateID, title, description, area, price FROM tblRealEstates WHERE categoryID = ? AND isactive = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, categoryID);
                pstm.setBoolean(2, true);
                rs = pstm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
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

    public List<RealEstateDTO> getListRealEstateByCategoryIDWithoutActive(int categoryID) throws Exception {
        List<RealEstateDTO> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 4 realEstateID, title, description, area, price, isActive FROM tblRealEstates WHERE categoryID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, categoryID);
                rs = pstm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    RealEstateDTO dto = new RealEstateDTO();
                    dto.setRealEstateID(rs.getInt("realEstateID"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDescription(rs.getString("description"));
                    dto.setArea(rs.getFloat("area"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setActive(rs.getBoolean("isActive"));
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
            if (conn != null) {
                String sql = "SELECT * FROM tblRealEstates WHERE realEstateID = ? AND isactive = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, realEstateID);
                pstm.setBoolean(2, true);
                rs = pstm.executeQuery();
                if (rs.next()) {
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
    
    public RealEstateDTO findRealEstateByIDWithoutActive(int realEstateID) throws Exception {
        RealEstateDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblRealEstates WHERE realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, realEstateID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String address = rs.getString("address");
                    float area = rs.getFloat("area");
                    Date postTime = new Date(rs.getDate("postTime").getTime());
                    int price = rs.getInt("price");
                    int stateID = rs.getInt("stateID");
                    int categoryID = rs.getInt("categoryID");
                    boolean active = rs.getBoolean("isActive");
                    dto = new RealEstateDTO(realEstateID, price, title, description, address, area, active, stateID, postTime, categoryID);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<RealEstateDTO> getListRealEstateByID(List<Integer> listRealEstateID) throws Exception {
        List<RealEstateDTO> list = null;
        if (listRealEstateID.size() > 0) {
            try {
                conn = DatabaseUtils.getConnection();
                if (conn != null) {
                    String sql = "SELECT realEstateID, title, area, price, isActive FROM tblRealEstates WHERE realEstateID = ?";

                    for (int i = 1; i < listRealEstateID.size(); i++) {
                        sql += " OR realEstateID = " + listRealEstateID.get(i);
                    }
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, listRealEstateID.get(0));
                    rs = pstm.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        RealEstateDTO dto = new RealEstateDTO();
                        dto.setRealEstateID(rs.getInt("realEstateID"));
                        dto.setTitle(rs.getString("title"));
                        dto.setArea(rs.getFloat("area"));
                        dto.setPrice(rs.getInt("price"));
                        dto.setActive(rs.getBoolean("isActive"));
                        list.add(dto);
                    }
                }
            } finally {
                closeConnection();
            }
        }
        return list;
    }

    public boolean delete(int realEstateID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblRealEstates SET isActive = ? WHERE realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, false);
                pstm.setInt(2, realEstateID);
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(RealEstateDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblRealEstates SET title = ?, description =?, price =?, area = ?, isActive = ?, categoryID = ?, stateID = ?, postTime = ? WHERE realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, dto.getTitle());
                pstm.setString(2, dto.getDescription());
                pstm.setInt(3, dto.getPrice());
                pstm.setFloat(4, dto.getArea());
                pstm.setBoolean(5, dto.isActive());
                pstm.setInt(6, dto.getCategoryID());
                pstm.setInt(7, dto.getStateID());
                pstm.setDate(8, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                pstm.setInt(9, dto.getRealEstateID());
                check = pstm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<RealEstateDTO> fintListByTxtSearch(String txtSearch) throws Exception {
        List<RealEstateDTO> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 4 realEstateID, title, description, area, price FROM tblRealEstates WHERE isActive = ? AND ";
                String[] temp = txtSearch.split(",");
                if (!temp[0].equals("")) {
                    sql += "title like '%" + temp[0] + "%' AND";
                }

                if (!temp[1].equals("0")) {
                    sql += " categoryID = " + temp[1] + " AND";
                }

                if (!temp[2].equals("0")) {
                    sql += " (stateID = " + temp[2];
                    for (int i = 3; i < temp.length; i++) {
                        sql += " OR stateID = " + temp[i];
                    }
                    sql += ")";
                } else {
                    if (sql.lastIndexOf("AND") == (sql.length() - 3)) {
                        sql = sql.substring(0, sql.length() - 3);
                    }
                }

                list = new ArrayList<>();
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, true);
                rs = pstm.executeQuery();
                while (rs.next()) {
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

    public List<RealEstateDTO> fintListByTxtSearchWithoutActive(String txtSearch) throws Exception {
        List<RealEstateDTO> list = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "Select realEstateID, title, description, area, price, isActive FROM tblRealEstates WHERE ";
                String[] temp = txtSearch.split(",");
                if (!temp[0].equals("")) {
                    sql += "title like '%" + temp[0] + "%' AND";
                }

                if (!temp[1].equals("0")) {
                    sql += " categoryID = " + temp[1] + " AND";
                }

                if (!temp[2].equals("0")) {
                    if (sql.contains("AND")) {
                        sql += "(";
                    }
                    sql += "stateID = " + temp[2];
                    for (int i = 3; i < temp.length; i++) {
                        sql += " OR stateID = " + temp[i];
                    }
                    if (sql.contains("AND")) {
                        sql += ")";
                    }
                } else {
                    if (sql.lastIndexOf("AND") == (sql.length() - 3)) {
                        sql = sql.substring(0, sql.length() - 3);
                    }
                }

                list = new ArrayList<>();
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    RealEstateDTO dto = new RealEstateDTO();
                    dto.setRealEstateID(rs.getInt("realEstateID"));
                    dto.setTitle(rs.getString("title"));
                    dto.setDescription(rs.getString("description"));
                    dto.setArea(rs.getFloat("area"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setActive(rs.getBoolean("isActive"));
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean checkSoldout(int realEstateID) throws Exception {
        boolean check = false;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT isActive FROM tblRealEstates WHERE realEstateID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, realEstateID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    check = rs.getBoolean("isActive");
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public int insertNewProduct(RealEstateDTO dto) throws Exception {
        int num = 0;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String generatedColumns[] = {"realEstateID"};
                String sql = "INSERT INTO tblRealEstates VALUES(?,?,?,?,?,?,?,?,?)";
                pstm = conn.prepareStatement(sql, generatedColumns);
                pstm.setString(1, dto.getTitle());
                pstm.setString(2, dto.getDescription());
                pstm.setInt(3, dto.getPrice());
                pstm.setFloat(4, dto.getArea());
                pstm.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                pstm.setBoolean(6, true);
                pstm.setInt(7, dto.getStateID());
                pstm.setString(8, dto.getAddress());
                pstm.setInt(9, dto.getCategoryID());
                pstm.executeUpdate();
                rs = pstm.getGeneratedKeys();
                if(rs.next()) {
                    num = rs.getInt(1);
                }
            }
        } finally {
            closeConnection();
        }
        return num;
    }
}
