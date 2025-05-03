package com.leloc.vn.model;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String loai;
    private int trangThai;

    public TaiKhoan(String tenDangNhap, String matKhau, String loai, int trangThai) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loai = loai;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
