package com.leloc.vn.controller;

import com.leloc.vn.dao.TaiKhoanDAO;

public class LoginController {
    private TaiKhoanDAO taiKhoanDAO;

    public LoginController() {
        this.taiKhoanDAO = new TaiKhoanDAO();
    }
    public boolean login(String tenDangNhap, String matKhau) {
        return taiKhoanDAO.checkLogin(tenDangNhap, matKhau);
    }
}
