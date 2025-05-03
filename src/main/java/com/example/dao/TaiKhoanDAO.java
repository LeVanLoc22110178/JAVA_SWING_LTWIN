package com.example.dao;

import com.example.model.TaiKhoan;
import com.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    private Connection connection;

    public TaiKhoanDAO() {
        this.connection = DBConnection.getConnection();
    }

    // InsertTaiKhoan
    public boolean insertTaiKhoan(String tenDangNhap, String matKhau, String loai) {
        String sql = "EXEC InsertTaiKhoan ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            stmt.setString(3, loai);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // UpdateTaiKhoan
    public boolean updateTaiKhoan(String tenDangNhap, String matKhau, String loai, int trangThai) {
        String sql = "EXEC UpdateTaiKhoan ?, ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            stmt.setString(3, loai);
            stmt.setInt(4, trangThai);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DeleteTaiKhoan
    public boolean deleteTaiKhoan(String tenDangNhap) {
        String sql = "EXEC DeleteTaiKhoan ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // CheckLogin
    public boolean checkLogin(String tenDangNhap, String matKhau) {
        String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ? AND TrangThai = 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // GetAllTaiKhoan
    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        String sql = "EXEC GetTaiKhoan";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TaiKhoan taiKhoan = new TaiKhoan(
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("Loai"),
                        rs.getInt("TrangThai")
                );
                taiKhoans.add(taiKhoan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return taiKhoans;
    }
}
