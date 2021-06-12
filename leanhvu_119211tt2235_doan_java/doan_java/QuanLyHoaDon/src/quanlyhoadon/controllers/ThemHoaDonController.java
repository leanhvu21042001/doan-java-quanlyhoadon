/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import quanlyhoadon.LopCoBan.HoaDon;
import quanlyhoadon.LopCoBan.NhanVien;
import quanlyhoadon.LopCoBan.SanPham;
import quanlyhoadon.models.QuanLyHoaDonModel;
import quanlyhoadon.views.QuanLyHoaDonView;
import quanlyhoadon.views.ThemHoaDonView;

/**
 *
 * @author PC
 */
public class ThemHoaDonController {

    private QuanLyHoaDonModel qlhdModel;
    private ThemHoaDonView thdView;
    public NhanVien nvpt;
    public ThemHoaDonController(QuanLyHoaDonModel qlhdModel, ThemHoaDonView thdView) {
        this.qlhdModel = qlhdModel;
        this.thdView = thdView;

        nvpt = this.qlhdModel.getNvPhuTrach();
        String maNV = this.qlhdModel.getNvPhuTrach().getMaNV();
        String tenNV = this.qlhdModel.getNvPhuTrach().getTenNV();
//        JOptionPane.showMessageDialog(thdView, "Ma nhan vien: " + this.qlhdModel.getNvPhuTrach().getMaNV());
        this.thdView.getTxtMaNV().setText(maNV);
        this.thdView.getTxtTenNV().setText(tenNV);

        this.thdView.getBtnThemSP().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPhamVaoTable();
            }
        });

        thucHienHamXoaSPTrenTable();

        btnLuuHoaDonClicked();

        huyHoaDon();
        
        DongForm();
    }

    public void DongForm() {
        this.thdView.getBtnDong().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    QuanLyHoaDonModel qlhdModel = new QuanLyHoaDonModel();
                    QuanLyHoaDonView qlhdView = new QuanLyHoaDonView();

                    qlhdModel.layNhanVienPhuTrachTuFormDangNhap(nvpt);
                    QuanLyHoaDonController qlhdController = new QuanLyHoaDonController(qlhdModel, qlhdView);
                    qlhdView.setLocationRelativeTo(null);
                    qlhdView.setVisible(true);
                    thdView.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(ThemHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void huyHoaDon() {
        this.thdView.getBtnHuyHoaDon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thdView.getTxtMaHD().setText("");
                thdView.getTxtTenKH().setText("");
                thdView.getTxtDiaChi().setText("");
                thdView.getTxtDienThoai().setText("");
                thdView.getTxtTenHang().setText("");
                thdView.getTxtSoLuong().setText("0");
                thdView.getTxtDonGia().setText("0");
                thdView.getTxtTongTien().setText("");
                DefaultTableModel model = (DefaultTableModel) thdView.getTblDanhSachSanPham().getModel();
                model.setRowCount(0);

            }
        });
    }

    public void btnLuuHoaDonClicked() {
        this.thdView.getBtnLuu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD = thdView.getTxtMaHD().getText();

                Date ngayBan = thdView.getJdateNgayBan().getDate();

                String maNV = thdView.getTxtMaNV().getText();
                String tenNV = thdView.getTxtTenNV().getText();
                String tenKH = thdView.getTxtTenKH().getText();
                String diaChi = thdView.getTxtDiaChi().getText();
                String dienThoai = thdView.getTxtDienThoai().getText();

                HoaDon hd = new HoaDon(0, maHD, ngayBan, maNV, tenNV, tenKH, diaChi, dienThoai);

                luuHoaDon(hd);
                // theem danh sach san pham vao
                DefaultTableModel model = (DefaultTableModel) thdView.getTblDanhSachSanPham().getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    SanPham sp = new SanPham(maHD, model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString(), Integer.parseInt(model.getValueAt(i, 2).toString()), Float.parseFloat(model.getValueAt(i, 3).toString()));
                    qlhdModel.themSanPhamMua(sp);
                    System.out.println("Them san pham thu " + i + " thanh cong");
                }
            }
        });
    }

    public void luuHoaDon(HoaDon hd) {
        //
        if (this.qlhdModel.themHoaDon2DB(hd) == true) {
            System.out.println("Them hoa don thanh cong");
        }
    }

    public void thucHienHamXoaSPTrenTable() {

        this.thdView.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPhamTrenTable();
            }
        });
    }

    // ben nay con chay duoc me noi
    public void xoaSanPhamTrenTable() {
        int row = this.thdView.getTblDanhSachSanPham().getSelectedRow();
        if (row != -1) {
            DefaultTableModel model = (DefaultTableModel) this.thdView.getTblDanhSachSanPham().getModel();
            model.removeRow(row);
        }
    }

    // them san pham
    public void themSanPhamVaoTable() {

        String maHang = "";
        String tenHang = "";

        int soLuong = 0;
        float donGia = 0;
        float thanhTien = 0;

        maHang = this.thdView.getCbMaHang().getSelectedItem().toString();
        tenHang = this.thdView.getTxtTenHang().getText();

        soLuong = Integer.parseInt(this.thdView.getTxtSoLuong().getText());
        donGia = Float.parseFloat(this.thdView.getTxtDonGia().getText());
        if (soLuong > 0 && donGia >= 0) {
            thanhTien = soLuong * donGia;
        }

        Object[] row = {maHang, tenHang, soLuong, donGia, thanhTien};
        DefaultTableModel model = (DefaultTableModel) this.thdView.getTblDanhSachSanPham().getModel();
        model.addRow(row);

        // thuc hiện tính tổng tiền
        float tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            tongTien += (float) model.getValueAt(i, 4);
        }
        this.thdView.getTxtTongTien().setText((int) tongTien + "");
    }
}
