package com.example.dao;

import com.example.model.ThucUong;
import com.example.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThucUongDAO {
    private Connection conn;

    public ThucUongDAO() {
        // Sử dụng DBConnection để lấy kết nối
        conn = DBConnection.getConnection();
    }

    // Insert ThucUong (có ảnh)
    public boolean insertThucUong(String ten, int idLoaiThucUong, float gia, int trangThai, byte[] anh) {
        String sql = "{call InsertThucUong(?, ?, ?, ?, ?)}"; // Gọi stored procedure
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, ten);
            stmt.setInt(2, idLoaiThucUong);
            stmt.setFloat(3, gia);
            stmt.setInt(4, trangThai);
            if (anh != null) {
                stmt.setBytes(5, anh); // Nếu có ảnh thì thêm vào
            } else {
                stmt.setNull(5, Types.BINARY); // Nếu không có ảnh thì để null
            }
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update ThucUong (có ảnh)
    public boolean updateThucUong(int idThucUong, String ten, int idLoaiThucUong, float gia, int trangThai, byte[] anh) {
        String sql = "{call UpdateThucUong(?, ?, ?, ?, ?, ?)}"; // Gọi stored procedure
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, idThucUong);
            stmt.setString(2, ten);
            stmt.setInt(3, idLoaiThucUong);
            stmt.setFloat(4, gia);
            stmt.setInt(5, trangThai);
            if (anh != null) {
                stmt.setBytes(6, anh);
            } else {
                stmt.setNull(6, Types.BINARY);
            }
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete ThucUong
    public boolean deleteThucUong(int idThucUong) {
        String sql = "{call DeleteThucUong(?)}"; // Gọi stored procedure
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, idThucUong);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ThucUong> getAllThucUong() {
        List<ThucUong> thucUongs = new ArrayList<>();
        String sql = "{call GetThucUong()}"; // Gọi stored procedure
        try (CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idThucUong = rs.getInt("id_thucUong");
                String ten = rs.getString("Ten");
                int idLoaiThucUong = rs.getInt("id_loaiThucUong");
                float gia = rs.getFloat("Gia");
                int trangThai = rs.getInt("TrangThai");
                Blob anh = rs.getBlob("Anh");
                thucUongs.add(new ThucUong(idThucUong, ten, idLoaiThucUong, gia, trangThai, anh));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thucUongs;
    }

}
