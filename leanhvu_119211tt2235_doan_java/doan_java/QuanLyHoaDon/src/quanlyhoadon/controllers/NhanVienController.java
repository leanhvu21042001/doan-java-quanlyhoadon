/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import quanlyhoadon.LopCoBan.HoaDon;
import quanlyhoadon.LopCoBan.NhanVien;
import quanlyhoadon.models.NhanVienModel;
import quanlyhoadon.models.QuanLyHoaDonModel;
import quanlyhoadon.views.DangNhapView;
import quanlyhoadon.views.QuanLyHoaDonView;
import quanlyhoadon.views.ThemHoaDonView;

/**
 *
 * @author PC
 */
public class NhanVienController {

    private DangNhapView viewDN;
    private NhanVienModel modelNV;

    public NhanVienController(DangNhapView viewDN, NhanVienModel modelNV) {
        this.viewDN = viewDN;
        this.modelNV = modelNV;
        this.viewDN.setTitle("Đăng nhập");

        this.viewDN.getBtnThoat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDN.dispose();
            }
        });
        viewDN.getBtnDangNhap().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = viewDN.getTxtUsername().getText();
                String password = viewDN.getTxtPassword().getText();

                NhanVien nvPhuTrach = modelNV.kiemTraDangNhap(username, password);

                if (nvPhuTrach != null) {
                    JOptionPane.showMessageDialog(viewDN, "Đăng nhập thành công!");

                    try {
                        QuanLyHoaDonModel qlhdModel = new QuanLyHoaDonModel();
                        QuanLyHoaDonView qlhdView = new QuanLyHoaDonView();
                        
                        qlhdModel.layNhanVienPhuTrachTuFormDangNhap(nvPhuTrach);
                        QuanLyHoaDonController qlhdController = new QuanLyHoaDonController(qlhdModel, qlhdView);
                        qlhdView.setLocationRelativeTo(null);
                        qlhdView.setVisible(true);
                        
                        viewDN.dispose();

                    } catch (Exception ex) {
                        Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(viewDN, "Đăng nhập không thành công!");
                }
            }
        });
    }

}
