package com.leloc.vn.model;

import java.util.Date;

public class HoaDon {
    private int idHoaDon;
    private Date ngayLap;
    private float tongTien;
    private int trangThai;
    private int idBan;
    private String tenDangNhap;

    // Constructor, getter và setter
    public HoaDon() {}

    public HoaDon(int idHoaDon, Date ngayLap, float tongTien, int trangThai, int idBan, String tenDangNhap) {
        this.idHoaDon = idHoaDon;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.idBan = idBan;
        this.tenDangNhap = tenDangNhap;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

}
