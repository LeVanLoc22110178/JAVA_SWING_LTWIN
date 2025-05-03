package com.leloc.vn.controller;

import com.leloc.vn.dao.*;
import com.leloc.vn.model.Ban;
import com.leloc.vn.model.TaiKhoan;
import com.leloc.vn.model.ThucUong;

import java.util.List;
import java.util.stream.Collectors;

public class DatHangController {

    private ThucUongDAO thuocUongDAO;
    private BanDAO banDAO;
    private TaiKhoanDAO taiKhoanDAO;
    private ChiTietHoaDonDAO chiTietHoaDonDAO;
    private HoaDonDAO hoaDonDAO;


    public DatHangController() {

        this.thuocUongDAO = new ThucUongDAO();
        this.banDAO = new BanDAO();
        this.taiKhoanDAO = new TaiKhoanDAO();
        this.chiTietHoaDonDAO = new ChiTietHoaDonDAO();
        this.hoaDonDAO = new HoaDonDAO();

    }

    public boolean createOrder(String tenBan, String tenTaiKhoan, List<Object[]> cartItems) {
        // Lấy ID bàn từ tên bàn
        int idBan = getIdBanByName(tenBan);
        if (idBan == -1) {
            System.out.println("Không tìm thấy bàn.");
            return false;
        }

        // Tạo hóa đơn mới
        int trangThai = 1;  // Đơn hàng chưa thanh toán
        int idHoaDon = hoaDonDAO.insertHoaDon(idBan, tenTaiKhoan, trangThai);

        if (idHoaDon == -1) {
            System.out.println("Có lỗi xảy ra khi tạo hóa đơn.");
            return false;
        }

        // Duyệt qua các mặt hàng trong giỏ hàng để thêm chi tiết hóa đơn
        for (Object[] item : cartItems) {
            String tenThucUong = (String) item[1];
            int soLuong = Integer.parseInt((String) item[3]);
            int idThucUong = getIdThucUongByName(tenThucUong);
            if (idThucUong == -1) {
                System.out.println("Không tìm thấy thức uống.");
                return false;
            }

            float donGia = getDonGiaById(idThucUong);

            // Thêm chi tiết hóa đơn vào cơ sở dữ liệu
            boolean isInserted = chiTietHoaDonDAO.insertChiTietHoaDon(idHoaDon, idThucUong, soLuong, donGia);
            if (!isInserted) {
                System.out.println("Có lỗi xảy ra khi thêm chi tiết hóa đơn.");
                return false;
            }
        }

        return true;
    }

    private int getIdBanByName(String tenBan) {
        for (Ban ban : banDAO.getAllBan()) {
            if (ban.getTen().equals(tenBan)) {
                return ban.getIdBan();
            }
        }
        return -1;
    }

    // Lấy id_thucUong từ tên thức uống
    private int getIdThucUongByName(String tenThucUong) {
        for (ThucUong thucUong : thuocUongDAO.getAllThucUong()) {
            if (thucUong.getTen().equals(tenThucUong)) {
                return thucUong.getIdThucUong();
            }
        }
        return -1;
    }

    // Lấy donGia từ id_thucUong
    private float getDonGiaById(int idThucUong) {
        for (ThucUong thucUong : thuocUongDAO.getAllThucUong()) {
            if (thucUong.getIdThucUong() == idThucUong) {
                return thucUong.getGia();
            }
        }
        return 0;
    }




    public List<String> getAllTenThucUong() {
        List<ThucUong> danhSachThucUong = thuocUongDAO.getAllThucUong();
        return danhSachThucUong.stream()
                               .map(ThucUong::getTen)
                               .collect(Collectors.toList());
    }

    public List<String> getAllTenBan() {
        List<Ban> danhSachBan = banDAO.getAllBan();
        return danhSachBan.stream()
                          .map(Ban::getTen)
                          .collect(Collectors.toList());
    }

    public List<String> getAllTenTaiKhoan() {
        List<TaiKhoan> danhSachTaiKhoan = taiKhoanDAO.getAllTaiKhoan();
        return danhSachTaiKhoan.stream()
                               .map(TaiKhoan::getTenDangNhap)
                               .collect(Collectors.toList());
    }


}
