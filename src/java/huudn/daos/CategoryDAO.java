/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import huudn.dtos.CategoryDTO;
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
public class CategoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    public CategoryDAO() {
    }

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

    public List<CategoryDTO> getListCategory() throws Exception {
        List<CategoryDTO> listCategory = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblCategories";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                listCategory = new ArrayList<>();
                while (rs.next()) {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setCategoryID(rs.getInt("categoryID"));
                    dto.setCategoryName(rs.getString("categoryName"));
                    listCategory.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listCategory;
    }

    public CategoryDTO findCategoryByID(int categoryID) throws Exception {
        CategoryDTO dto = null;
        try {
            conn = DatabaseUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT categoryName FROM tblCategories WHERE categoryID = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, categoryID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    dto = new CategoryDTO(categoryID, rs.getString("categoryName"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
