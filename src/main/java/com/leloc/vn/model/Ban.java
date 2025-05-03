package com.leloc.vn.model;

public class Ban {
    private int idBan;
    private String ten;
    private int trangThai;

    public Ban() {}

    public Ban(int idBan, String ten, int trangThai) {
        this.idBan = idBan;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
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
