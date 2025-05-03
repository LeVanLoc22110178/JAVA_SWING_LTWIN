package com.example.dao;

import com.example.model.NhanVien;
import com.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    private Connection connection;

    // Constructor để lấy kết nối chung
    public NhanVienDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Method to insert a new employee
    public boolean insertNhanVien(String tenDangNhap, String hoTen, String gioiTinh, String dienThoai, String chucVu) {
        String sql = "EXEC InsertNhanVien ?, ?, ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, hoTen);
            stmt.setString(3, gioiTinh);
            stmt.setString(4, dienThoai);
            stmt.setString(5, chucVu);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to update an existing employee
    public boolean updateNhanVien(int idNhanVien, String hoTen, String gioiTinh, String dienThoai, String chucVu, int trangThai) {
        String sql = "EXEC UpdateNhanVien ?, ?, ?, ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idNhanVien);
            stmt.setString(2, hoTen);
            stmt.setString(3, gioiTinh);
            stmt.setString(4, dienThoai);
            stmt.setString(5, chucVu);
            stmt.setInt(6, trangThai);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to delete an employee
    public boolean deleteNhanVien(int idNhanVien) {
        String sql = "EXEC DeleteNhanVien ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idNhanVien);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to get all employees
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        String sql = "EXEC GetNhanVien";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getInt("id_nhanVien"),
                        rs.getString("TenDangNhap"),
                        rs.getString("HoTen"),
                        rs.getString("GioiTinh"),
                        rs.getString("DienThoai"),
                        rs.getString("ChucVu"),
                        rs.getInt("TrangThai")
                );
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nhanVienList;
    }
}
