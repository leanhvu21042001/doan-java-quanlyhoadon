/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.LopCoBan;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author PC
 */
public class NhanVien {
    private int id;
    private String maNV;
    private String tenNV;
    private String username;
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public NhanVien() {}

    public NhanVien(int id, String maNV, String tenNV, String username, String password) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.username = username;
        this.password = password;
    }
    

}
