package com.leloc.vn.model;

public class ChiTietHoaDon {
    private int idChiTietHoaDon;
    private int idHoaDon;
    private int idThucUong;
    private int soLuong;
    private float donGia;

    public ChiTietHoaDon(int idChiTietHoaDon, int idHoaDon, int idThucUong, int soLuong, float donGia) {
        this.idChiTietHoaDon = idChiTietHoaDon;
        this.idHoaDon = idHoaDon;
        this.idThucUong = idThucUong;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters and Setters
    public int getIdChiTietHoaDon() {
        return idChiTietHoaDon;
    }

    public void setIdChiTietHoaDon(int idChiTietHoaDon) {
        this.idChiTietHoaDon = idChiTietHoaDon;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdThucUong() {
        return idThucUong;
    }

    public void setIdThucUong(int idThucUong) {
        this.idThucUong = idThucUong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
}
