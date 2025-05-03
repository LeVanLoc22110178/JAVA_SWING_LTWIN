package com.example.model;

import java.sql.Blob;

public class ThucUong {
    private int idThucUong;
    private String ten;
    private int idLoaiThucUong;
    private float gia;
    private int trangThai;
    private Blob anh;  // Lưu ảnh dưới dạng Blob (Binary Large Object)

    // Constructor
    public ThucUong(int idThucUong, String ten, int idLoaiThucUong, float gia, int trangThai, Blob anh) {
        this.idThucUong = idThucUong;
        this.ten = ten;
        this.idLoaiThucUong = idLoaiThucUong;
        this.gia = gia;
        this.trangThai = trangThai;
        this.anh = anh;
    }

    // Getters and Setters
    public int getIdThucUong() {
        return idThucUong;
    }

    public void setIdThucUong(int idThucUong) {
        this.idThucUong = idThucUong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getIdLoaiThucUong() {
        return idLoaiThucUong;
    }

    public void setIdLoaiThucUong(int idLoaiThucUong) {
        this.idLoaiThucUong = idLoaiThucUong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Blob getAnh() {
        return anh;
    }

    public void setAnh(Blob anh) {
        this.anh = anh;
    }
}
