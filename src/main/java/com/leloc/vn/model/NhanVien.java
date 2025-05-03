package com.leloc.vn.model;

public class NhanVien {
    private int idNhanVien;
    private String tenDangNhap;
    private String hoTen;
    private String gioiTinh;
    private String dienThoai;
    private String chucVu;
    private int trangThai;

    public NhanVien(int idNhanVien, String tenDangNhap, String hoTen, String gioiTinh, String dienThoai, String chucVu, int trangThai) {
        this.idNhanVien = idNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
