/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.models;

import quanlyhoadon.LopCoBan.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author PC
 */
public class NhanVienModel {
    
    //
    private ArrayList<NhanVien> dsNhanVien;
    
    //
    public ArrayList<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }

    public void setDsNhanVien(ArrayList<NhanVien> dsNhanVien) {
        this.dsNhanVien = dsNhanVien;
    }

    //
    public NhanVienModel() throws Exception {
        this.dsNhanVien = this.layDanhSachNhanVien();
    }
    
    // kiem tra dang nhap
    public NhanVien kiemTraDangNhap(String username, String password) {
        for (NhanVien nv : this.dsNhanVien) {
            if (nv.getUsername().equals(username) && nv.getPassword().equals(password)) {
                return nv;
            }
        }
        return null;
    }
    
    
    // lay danh sach nhan vien tu db
    private ArrayList<NhanVien> layDanhSachNhanVien() throws Exception {
        String sql = "SELECT * FROM `nhanvien`";
        ResultSet rs = Connect2DB.getDataFromDB(sql);
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        while(rs.next()) {
            result.add(new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        
        return result;
    }
}
