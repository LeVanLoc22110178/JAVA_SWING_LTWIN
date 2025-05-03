package com.example.controller;

import com.example.dao.TaiKhoanDAO;

public class LoginController {
    private TaiKhoanDAO taiKhoanDAO;

    public LoginController() {
        this.taiKhoanDAO = new TaiKhoanDAO();
    }
    public boolean login(String tenDangNhap, String matKhau) {
        return taiKhoanDAO.checkLogin(tenDangNhap, matKhau);
    }
}
