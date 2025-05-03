package com.example.model;

public class LoaiThucUong {
    private int idLoaiThucUong;
    private String ten;
    private int trangThai;

    // Constructor, getter và setter
    public LoaiThucUong() {}

    public LoaiThucUong(int idLoaiThucUong, String ten, int trangThai) {
        this.idLoaiThucUong = idLoaiThucUong;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public int getIdLoaiThucUong() {
        return idLoaiThucUong;
    }

    public void setIdLoaiThucUong(int idLoaiThucUong) {
        this.idLoaiThucUong = idLoaiThucUong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
