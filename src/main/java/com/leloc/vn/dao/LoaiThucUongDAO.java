package com.leloc.vn.dao;

import com.leloc.vn.model.LoaiThucUong;
import com.leloc.vn.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiThucUongDAO {

    private Connection connection;

    // Constructor để lấy kết nối chung
    public LoaiThucUongDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Thêm loại thức uống mới
    public boolean insertLoaiThucUong(String ten, int trangThai) {
        String sql = "EXEC InsertLoaiThucUong ?, ?";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, ten);
            stmt.setInt(2, trangThai);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật loại thức uống
    public boolean updateLoaiThucUong(int idLoaiThucUong, String ten, int trangThai) {
        String sql = "EXEC UpdateLoaiThucUong ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLoaiThucUong);
            stmt.setString(2, ten);
            stmt.setInt(3, trangThai);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa loại thức uống
    public boolean deleteLoaiThucUong(int idLoaiThucUong) {
        String sql = "EXEC DeleteLoaiThucUong ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLoaiThucUong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả loại thức uống
    public List<LoaiThucUong> getAllLoaiThucUong() {
        List<LoaiThucUong> loaiThucUongList = new ArrayList<>();
        String sql = "SELECT * FROM LoaiThucUong";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LoaiThucUong loaiThucUong = new LoaiThucUong(
                        rs.getInt("id_loaiThucUong"),
                        rs.getString("Ten"),
                        rs.getInt("TrangThai")
                );
                loaiThucUongList.add(loaiThucUong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiThucUongList;
    }
}
