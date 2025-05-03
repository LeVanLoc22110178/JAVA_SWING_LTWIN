package com.leloc.vn.dao;

import com.leloc.vn.model.Ban;
import com.leloc.vn.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanDAO {

    private Connection connection; // Dùng chung kết nối

    // Constructor để lấy kết nối chung
    public BanDAO() {
        this.connection = DBConnection.getConnection(); // Lấy kết nối chung
    }

    // InsertBan
    public boolean insertBan(String ten, int trangThai) {
        String sql = "EXEC InsertBan ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ten);
            stmt.setInt(2, trangThai);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // UpdateBan
    public boolean updateBan(int idBan, String ten, int trangThai) {
        String sql = "EXEC UpdateBan ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBan);
            stmt.setString(2, ten);
            stmt.setInt(3, trangThai);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DeleteBan
    public boolean deleteBan(int idBan) {
        String sql = "EXEC DeleteBan ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBan);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // GetAllBan
    public List<Ban> getAllBan() {
        List<Ban> banList = new ArrayList<>();
        String sql = "EXEC GetBan";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ban ban = new Ban(
                        rs.getInt("id_ban"),
                        rs.getString("Ten"),
                        rs.getInt("TrangThai")
                );
                banList.add(ban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }

    // Đảm bảo đóng kết nối khi không cần thiết nữa (nếu dùng kết nối riêng lẻ trong các trường hợp khác)
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
