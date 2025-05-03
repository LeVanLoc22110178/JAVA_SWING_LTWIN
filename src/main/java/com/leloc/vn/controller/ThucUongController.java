package com.leloc.vn.controller;

import com.leloc.vn.dao.ThucUongDAO;
import com.leloc.vn.model.ThucUong;
import com.leloc.vn.view.ThucUongView;

import java.util.List;

public class ThucUongController {
    private ThucUongView view;
    private ThucUongDAO dao;

    public ThucUongController(ThucUongView view, ThucUongDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    // Load danh sách thức uống
    public void loadThucUong() {
        List<ThucUong> thucUongs = dao.getAllThucUong();
        view.displayThucUong(thucUongs);
    }

    // Thêm thức uống
    public void addThucUong(String ten, int idLoaiThucUong, float gia, int trangThai, byte[] anh) {
        boolean isSuccess = dao.insertThucUong(ten, idLoaiThucUong, gia, trangThai, anh);
        if (isSuccess) {
            loadThucUong();  // Load lại danh sách thức uống
        } else {
            view.showMessage("Lỗi khi thêm thức uống.");
        }
    }


    // Cập nhật thức uống
    public void updateThucUong(int idThucUong, String ten, int idLoaiThucUong, float gia, int trangThai, byte[] anh) {
        boolean isSuccess = dao.updateThucUong(idThucUong, ten, idLoaiThucUong, gia, trangThai, anh);
        if (isSuccess) {
            loadThucUong();  // Load lại danh sách thức uống
        } else {
            view.showMessage("Lỗi khi cập nhật thức uống.");
        }
    }

    // Xóa thức uống
    public void deleteThucUong(int idThucUong) {
        boolean isSuccess = dao.deleteThucUong(idThucUong);
        if (isSuccess) {
            loadThucUong();  // Load lại danh sách thức uống
        } else {
            view.showMessage("Lỗi khi xóa thức uống.");
        }
    }
}
