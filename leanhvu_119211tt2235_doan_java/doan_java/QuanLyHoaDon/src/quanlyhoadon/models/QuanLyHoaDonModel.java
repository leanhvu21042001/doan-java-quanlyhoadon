/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import quanlyhoadon.LopCoBan.HoaDon;
import quanlyhoadon.LopCoBan.NhanVien;
import quanlyhoadon.LopCoBan.SanPham;

/**
 *
 * @author PC
 */
public class QuanLyHoaDonModel {

    private ArrayList<HoaDon> dsHoaDon;

    private NhanVien nvPhuTrach;

    private String maHD;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public NhanVien getNvPhuTrach() {
        return nvPhuTrach;
    }

    public void setNvPhuTrach(NhanVien nvPhuTrach) {
        this.nvPhuTrach = nvPhuTrach;
    }

    public ArrayList<HoaDon> getDsHoaDon() {
        return dsHoaDon;
    }

    public void setDsHoaDon(ArrayList<HoaDon> dsHoaDon) {
        this.dsHoaDon = dsHoaDon;
    }

    public QuanLyHoaDonModel() throws Exception {
        this.dsHoaDon = layDanhSachHoaDon(); // lay du lieu tu database xuong
    }

    public void layNhanVienPhuTrachTuFormDangNhap(NhanVien nv) {
        this.nvPhuTrach = nv;
    }

    // lay danh sach hoa don xuong
    public ArrayList<HoaDon> layDanhSachHoaDonTheoMaHD(String maHD) throws Exception {
        String sql = "SELECT * FROM hoadon WHERE maHD like '%" + maHD + "%'";
        ResultSet rs = Connect2DB.getDataFromDB(sql);
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();

        while (rs.next()) {
            // chuyen tu chuoi sang date

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String sDate = rs.getString(3);
            Date date = formatter.parse(sDate);

            result.add(new HoaDon(rs.getInt(1), rs.getString(2), date, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        return result;
    }

    public boolean capNhatSanPhamChoHoaDon(SanPham sp) {
        try {
            String updateSQL = "UPDATE `sanpham` SET `tenSP`=?,`soLuong`=?,`donGia`=? WHERE `maHD`=? and `maSP`=?";
            PreparedStatement ps = Connect2DB.conn.prepareStatement(updateSQL);

            ps.setString(1, sp.getTenSP());
            ps.setInt(2, sp.getSoLuong());
            ps.setFloat(3, sp.getDonGia());
            ps.setString(4, sp.getMaHD());
            ps.setString(5, sp.getMaSP());
            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }
        return false;
    }

    public boolean capNhatHoaDon(HoaDon hd) throws ParseException {
        try {
            String updateSQL = "UPDATE `hoadon` SET `ngayBan`=?,`maNV`=?,`tenNV`=?,`tenKH`=?,`diaChi`=?,`dienThoai`=? WHERE `maHD`=?";
            PreparedStatement ps = Connect2DB.conn.prepareStatement(updateSQL);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String dateFormat = formatter.format(hd.getNgayBan());

            ps.setString(1, dateFormat);
            ps.setString(2, hd.getMaNV());
            ps.setString(3, hd.getTenNV());
            ps.setString(4, hd.getTenKH());
            ps.setString(5, hd.getDiaChi());
            ps.setString(6, hd.getDienThoai());
            ps.setString(7, hd.getMaHD());
            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }
        return false;
    }

    // lay hoa don theo ma hoa don
    public ArrayList<HoaDon> layHoaDonTheoMaHD(String maHD) throws Exception {
        String sql = "SELECT * FROM hoadon WHERE maHD = '" + maHD + "'";

        ResultSet rs = Connect2DB.getDataFromDB(sql);

        ArrayList<HoaDon> result = new ArrayList<HoaDon>();

        while (rs.next()) {
            // chuyen tu chuoi sang date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String sDate = rs.getString(3);
            Date date = formatter.parse(sDate);

            result.add(new HoaDon(rs.getInt(1), rs.getString(2), date, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        return result;
    }

    // lay danh sach san pham theo ma hoa don
    public ArrayList<SanPham> lay_DSSP_TheoMaHD(String maHD) throws Exception {
        String sql = "SELECT * FROM sanpham WHERE maHD = '" + maHD + "'";

        ResultSet rs = Connect2DB.getDataFromDB(sql);
        ArrayList<SanPham> result = new ArrayList<SanPham>();

        while (rs.next()) {
            result.add(new SanPham(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6)));
        }
        return result;
    }

    public boolean xoaHD_TheoMaHD(String maHD) {
        try {
            String insertSQL = "DELETE FROM `hoadon` WHERE maHD = ?";
            PreparedStatement ps = Connect2DB.conn.prepareStatement(insertSQL);

            ps.setString(1, maHD);

            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }

        return false;
    }

    public boolean xoaSP_TheoMaHD(String maHD) {
        try {
            String insertSQL = "DELETE FROM `sanpham` WHERE maHD = ?";
            PreparedStatement ps = Connect2DB.conn.prepareStatement(insertSQL);

            ps.setString(1, maHD);

            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }

        return false;
    }

    // them san pham vao hoa don
    public boolean themHoaDon2DB(HoaDon hd) {

        try {
            String insertSQL = "INSERT INTO `hoadon` VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = Connect2DB.conn.prepareStatement(insertSQL);

            ps.setString(1, hd.getMaHD());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String dateFormat = formatter.format(hd.getNgayBan());

            ps.setString(2, dateFormat); // loi ngay cho nay
            ps.setString(3, hd.getMaNV());
            ps.setString(4, hd.getTenNV());
            ps.setString(5, hd.getTenKH());
            ps.setString(6, hd.getDiaChi());
            ps.setString(7, hd.getDienThoai());

            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }
        return false;
    }

    public boolean themSanPhamMua(SanPham sp) {
        try {
            String insertSQL = "INSERT INTO `sanpham` VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement ps = Connect2DB.conn.prepareStatement(insertSQL);

            ps.setString(1, sp.getMaHD());
            ps.setString(2, sp.getMaSP());
            ps.setString(3, sp.getTenSP());
            ps.setInt(4, sp.getSoLuong());
            ps.setFloat(5, sp.getDonGia());

            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex);
        }
        return false;
    }

    // lay danh sach hoa don xuong
    public ArrayList<HoaDon> layDanhSachHoaDon() throws Exception {
        String sql = "SELECT * FROM hoadon ORDER BY maHD DESC";
        ResultSet rs = Connect2DB.getDataFromDB(sql);
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();

        while (rs.next()) {
            // chuyen tu chuoi sang date

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String sDate = rs.getString(3);
            Date date = formatter.parse(sDate);

            result.add(new HoaDon(rs.getInt(1), rs.getString(2), date, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        return result;
    }
}
