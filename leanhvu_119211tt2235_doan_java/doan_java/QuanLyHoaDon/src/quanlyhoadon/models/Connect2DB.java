/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class Connect2DB {
    public static Connection conn;
    private static String db_url = "jdbc:mysql://localhost:3306/quanlyhoadon?characterEncoding=utf8&useSSL=false&useUnicode=true";
    private static String username = "root";
    private static String password = "";
    
    // kết nối tới csdl
    public static Connection getConnection() throws Exception {
        if(conn == null) {
            //  conn.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(db_url, username, password);
            System.out.println("Ket noi thanh cong!");
        }
        return conn;
    }
    
    
    // gửi dử liệu đến database
    public static int setData2DB(String sql) throws Exception {
        return Connect2DB.getConnection().createStatement().executeUpdate(sql);
    }
    
    // lấy dữ liệu từ database
    public static ResultSet getDataFromDB(String sql) throws Exception {
        ResultSet rs = Connect2DB.getConnection().createStatement().executeQuery(sql);
        return rs;
    }
    
    
}

