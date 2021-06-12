/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon;

import quanlyhoadon.LopCoBan.HoaDon;
import quanlyhoadon.controllers.NhanVienController;
import quanlyhoadon.controllers.QuanLyHoaDonController;
import quanlyhoadon.models.NhanVienModel;
import quanlyhoadon.models.QuanLyHoaDonModel;
import quanlyhoadon.views.DangNhapView;
import quanlyhoadon.views.QuanLyHoaDonView;

/**
 *
 * @author PC
 */
public class MainQuanLyHoaDon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DangNhapView viewDN = new DangNhapView();
        NhanVienModel modelNV = new NhanVienModel();
        
        NhanVienController nvController = new NhanVienController(viewDN, modelNV);
        viewDN.setLocationRelativeTo(null);
        viewDN.setVisible(true);

//        QuanLyHoaDonView qldhView = new QuanLyHoaDonView();
//        QuanLyHoaDonModel qldhModel = new QuanLyHoaDonModel();
//        QuanLyHoaDonController q = new QuanLyHoaDonController(qldhModel, qldhView);
//        qldhView.setVisible(true);
    }

}
