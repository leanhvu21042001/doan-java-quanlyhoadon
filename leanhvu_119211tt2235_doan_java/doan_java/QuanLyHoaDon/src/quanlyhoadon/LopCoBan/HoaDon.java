package quanlyhoadon.LopCoBan;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class HoaDon {
    private int id;
    private String maHD;
    private Date ngayBan;
    private String maNV;
    private String tenNV;
    private String tenKH;
    private String diaChi;
    private String dienThoai;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
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

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public HoaDon() {}
    
    public HoaDon(int id, String maHD, Date ngayBan, String maNV, String tenNV, String tenKH, String diaChi, String dienThoai) {
        this.id = id;
        this.maHD = maHD;
        this.ngayBan = ngayBan;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }
    
    
}
