/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
