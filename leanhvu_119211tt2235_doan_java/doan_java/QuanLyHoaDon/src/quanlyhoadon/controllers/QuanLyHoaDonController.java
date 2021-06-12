/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhoadon.controllers;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import quanlyhoadon.LopCoBan.HoaDon;
import quanlyhoadon.LopCoBan.NhanVien;
import quanlyhoadon.models.QuanLyHoaDonModel;
import quanlyhoadon.views.QuanLyHoaDonView;
import quanlyhoadon.views.SuaHoaDonView;
import quanlyhoadon.views.ThemHoaDonView;

/**
 *
 * @author PC
 */
public class QuanLyHoaDonController {

    private QuanLyHoaDonModel qlhdModel;
    private QuanLyHoaDonView qlhdView;
    public NhanVien nvpt;

    public QuanLyHoaDonController(QuanLyHoaDonModel qlhdModel, QuanLyHoaDonView qlhdView) throws Exception {
        this.qlhdView = qlhdView;
        this.qlhdModel = qlhdModel;
        nvpt = this.qlhdModel.getNvPhuTrach();

        showDataToTable(this.qlhdModel.getDsHoaDon());

        eventSelectedTable();

        btnThemHoaDonMoi();

        btnThoatFrameQLHD();

        btnXoaHoaDon();

        btnSuaHoaDon();

        btnTimKiem();

    }
    
    public void btnTimKiem() {
        this.qlhdView.getBtnTimKiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sKey = null;
                sKey = qlhdView.getTxtMaHD().getText();
                System.out.println();
                // thuc hien tim kiem va show table, neu null thi show lai dataTable
                if (sKey.toString().equals("")) {
                    showDataToTable(qlhdModel.getDsHoaDon());
                    qlhdView.getBtnTimKiem().setText("Tìm");
                } else {
                    try {
                        showDataToTable(danhSachHoaDonTimDuoc(sKey));
                        qlhdView.getBtnTimKiem().setText("Hiển thị toàn bộ");
                        qlhdView.getTxtMaHD().setText("");
                    } catch (Exception ex) {
                        Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public ArrayList<HoaDon> danhSachHoaDonTimDuoc(String maHD) throws Exception {
        ArrayList<HoaDon> result = null;

        result = this.qlhdModel.layDanhSachHoaDonTheoMaHD(maHD);
        return result;
    }

    public void btnSuaHoaDon() {
        this.qlhdView.getBtnSuaHD().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QuanLyHoaDonModel qlhdModel = new QuanLyHoaDonModel();
                    SuaHoaDonView shdView = new SuaHoaDonView();

                    if (qlhdView.getTxtMaHD().getText().toString().equals("")) {
                        return;
                    }

                    qlhdModel.layNhanVienPhuTrachTuFormDangNhap(nvpt);
                    qlhdModel.setMaHD(qlhdView.getTxtMaHD().getText());

                    SuaHoaDonController shdController = new SuaHoaDonController(qlhdModel, shdView);

                    shdView.setVisible(true);

                    thoatFormQuanLyHoaDon();
                } catch (Exception ex) {
                    Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // xoa hoa don
    public void btnXoaHoaDon() {
        this.qlhdView.getBtnXoaHD().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaDongTrenTable();

                String maHD = "";

                maHD = qlhdView.getTxtMaHD().getText();
                qlhdView.getTxtMaHD().setText("");
                if (qlhdModel.xoaSP_TheoMaHD(maHD)) {
                    System.out.println("xoa ds san pham thanh cong");
                }

                if (qlhdModel.xoaHD_TheoMaHD(maHD)) {
                    System.out.println("Xoa hoa don thanh cong");
                }
                // showDataToTable(qlhdModel.getDsHoaDon());
            }
        });
    }

    // lam gi lam ben nay nek :((( lam ben kia bay mau luon thi chet t mai t nop roi 
    public void xoaDongTrenTable() {
        int row = this.qlhdView.getTblToanBoHoaDon().getSelectedRow();
        if (row >= 0) {

            DefaultTableModel model = (DefaultTableModel) this.qlhdView.getTblToanBoHoaDon().getModel();
            model.removeRow(row);
        }
        return;
    }

    // them moi mot hoa don (dang lam)
    public void btnThemHoaDonMoi() {
        this.qlhdView.getBtnThemHD().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QuanLyHoaDonModel qlhdModel = new QuanLyHoaDonModel();
                    ThemHoaDonView thdView = new ThemHoaDonView();
                    qlhdModel.layNhanVienPhuTrachTuFormDangNhap(nvpt);
                    ThemHoaDonController thdController = new ThemHoaDonController(qlhdModel, thdView);
                    thdView.setLocationRelativeTo(null);
                    thdView.setVisible(true);

                    thoatFormQuanLyHoaDon();
                } catch (Exception ex) {
                    Logger.getLogger(QuanLyHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void btnThoatFrameQLHD() {
        this.qlhdView.getBtnThoat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = JOptionPane.showConfirmDialog(qlhdView, "Bạn có muốn thoát chương trình không !");
                if (c == 0) {
                    thoatFormQuanLyHoaDon();
                }
            }
        });
    }

    public void thoatFormQuanLyHoaDon() {
        qlhdView.dispose();
    }

    public void eventSelectedTable() {
        this.qlhdView.getTblToanBoHoaDon().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                getValueSelectedTable();
            }
        });
    }

    // lấy mã hóa đơn và mã nhân viên ra để đưa sang form khác
    public void getValueSelectedTable() {
        int row = this.qlhdView.getTblToanBoHoaDon().getSelectedRow();
        if (row != -1) {
            String maHD = (String.valueOf(this.qlhdView.getTblToanBoHoaDon().getValueAt(row, 1)));

            this.qlhdView.getTxtMaHD().setText(maHD);
        }

    }
    
    // hiển thị giá trị lên 
    public void showDataToTable(ArrayList<HoaDon> dsHoaDon) {
        DefaultTableModel tableModel = (DefaultTableModel) this.qlhdView.getTblToanBoHoaDon().getModel();
        tableModel.setRowCount(0);

        // tableModel.setColumnCount(0);
        // show data
        if (dsHoaDon != null) {

            int i = 1;
            for (HoaDon hoaDon : dsHoaDon) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String dateFormat = formatter.format(hoaDon.getNgayBan());
                tableModel.addRow(
                        new Object[]{i++,
                            hoaDon.getMaHD(),
                            hoaDon.getTenKH(),
                            hoaDon.getDiaChi(),
                            hoaDon.getDienThoai(),
                            dateFormat,
                            hoaDon.getTenNV()
                        });
            }
            this.qlhdView.getTblToanBoHoaDon().setModel(tableModel);
        }
    }

}
