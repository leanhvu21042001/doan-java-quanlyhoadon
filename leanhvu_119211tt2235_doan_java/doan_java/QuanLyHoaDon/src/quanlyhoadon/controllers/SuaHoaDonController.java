/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import quanlyhoadon.LopCoBan.HoaDon;
import quanlyhoadon.LopCoBan.NhanVien;
import quanlyhoadon.LopCoBan.SanPham;
import quanlyhoadon.models.QuanLyHoaDonModel;
import quanlyhoadon.views.QuanLyHoaDonView;
import quanlyhoadon.views.SuaHoaDonView;

/**
 *
 * @author PC
 */
public class SuaHoaDonController {

    private QuanLyHoaDonModel qlhdModel;
    private SuaHoaDonView shdView;
    public NhanVien nvpt;
    public String maHD;

    public SuaHoaDonController(QuanLyHoaDonModel qlhdModel, SuaHoaDonView shdView) throws Exception {
        this.qlhdModel = qlhdModel;
        this.shdView = shdView;

        this.maHD = this.qlhdModel.getMaHD();
        this.nvpt = this.qlhdModel.getNvPhuTrach();
        String maNV = this.qlhdModel.getNvPhuTrach().getMaNV();
        String tenNV = this.qlhdModel.getNvPhuTrach().getTenNV();

        this.shdView.getTxtMaNV().setText(maNV);
        this.shdView.getTxtTenNV().setText(tenNV);
        this.shdView.getTxtMaHD().setText(maHD);

        layDuLieu();
        thucHienHamXoaSPTrenTable();

        DongForm();

        btnLuuHoaDonClicked();

    }
    
    public void layDuLieu() throws Exception {
        ArrayList<SanPham> dsSanSpMua = this.qlhdModel.lay_DSSP_TheoMaHD(this.maHD);
        ArrayList<HoaDon> dsHd = this.qlhdModel.layHoaDonTheoMaHD(this.maHD);

        this.shdView.getTxtTenKH().setText(dsHd.get(0).getTenKH());
        this.shdView.getTxtDiaChi().setText(dsHd.get(0).getDiaChi());
        this.shdView.getTxtDienThoai().setText(dsHd.get(0).getDienThoai());

        duaSanPhamVaoTable(dsSanSpMua);
    }

    public void DongForm() {
        this.shdView.getBtnDong().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    QuanLyHoaDonModel qlhdModel = new QuanLyHoaDonModel();
                    QuanLyHoaDonView qlhdView = new QuanLyHoaDonView();

                    qlhdModel.layNhanVienPhuTrachTuFormDangNhap(nvpt);
                    qlhdView.setLocationRelativeTo(null);
                    QuanLyHoaDonController qlhdController = new QuanLyHoaDonController(qlhdModel, qlhdView);

                    qlhdView.setVisible(true);
                    shdView.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(ThemHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void btnLuuHoaDonClicked() {
        this.shdView.getBtnLuu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD = shdView.getTxtMaHD().getText();

                Date ngayBan = shdView.getJdateNgayBan().getDate();

                String maNV = shdView.getTxtMaNV().getText();
                String tenNV = shdView.getTxtTenNV().getText();
                String tenKH = shdView.getTxtTenKH().getText();
                String diaChi = shdView.getTxtDiaChi().getText();
                String dienThoai = shdView.getTxtDienThoai().getText();

                HoaDon hd = new HoaDon(0, maHD, ngayBan, maNV, tenNV, tenKH, diaChi, dienThoai);

                try {
                    capNhatHoaDon(hd);
                } catch (ParseException ex) {
                    Logger.getLogger(SuaHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
                }

                // theem danh sach san pham vao
                DefaultTableModel model = (DefaultTableModel) shdView.getTblDanhSachSanPham().getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    SanPham sp = new SanPham(maHD, model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString(), Integer.parseInt(model.getValueAt(i, 2).toString()), Float.parseFloat(model.getValueAt(i, 3).toString()));

                    qlhdModel.capNhatSanPhamChoHoaDon(sp);
                    System.out.println("Them san pham thu " + i + " thanh cong");
                }
            }
        });
    }

    public void capNhatHoaDon(HoaDon hd) throws ParseException {
        // update
        if (this.qlhdModel.capNhatHoaDon(hd) == true) {
            System.out.println("Them hoa don thanh cong");
        }
    }

    public void thucHienHamXoaSPTrenTable() {

        this.shdView.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPhamTrenTable();
            }
        });
    }

    // ben nay con chay duoc me noi
    public void xoaSanPhamTrenTable() {
        int row = this.shdView.getTblDanhSachSanPham().getSelectedRow();
        if (row != -1) {
            DefaultTableModel model = (DefaultTableModel) this.shdView.getTblDanhSachSanPham().getModel();
            model.removeRow(row);
        }
    }

    public void duaSanPhamVaoTable(ArrayList<SanPham> sp) {

        DefaultTableModel model = (DefaultTableModel) this.shdView.getTblDanhSachSanPham().getModel();

        for (int i = 0; i < sp.size(); i++) {
            String maHang = "";
            String tenHang = "";

            int soLuong = 0;
            float donGia = 0;
            float thanhTien = 0;

            maHang = sp.get(i).getMaSP();
            tenHang = sp.get(i).getTenSP();

            soLuong = sp.get(i).getSoLuong();
            donGia = sp.get(i).getDonGia();

            thanhTien = soLuong * donGia;
            Object[] row = {maHang, tenHang, soLuong, donGia, thanhTien};

            model.addRow(row);
        }
        // thuc hiện tính tổng tiền
        float tongTien = 0;
        for (int j = 0; j < model.getRowCount(); j++) {
            tongTien += (float) model.getValueAt(j, 4);
        }
        this.shdView.getTxtTongTien().setText((int) tongTien + "");
    }
}
