package com.leloc.vn.dao;

import com.leloc.vn.model.HoaDon;
import com.leloc.vn.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    private Connection connection;

    // Constructor để lấy kết nối chung
    public HoaDonDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Insert hóa đơn mới
    public int insertHoaDon(int idBan, String tenDangNhap, int trangThai) {
        String sql = "EXEC InsertHoaDon ?, ?, ?, ?";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idBan);
            stmt.setString(2, tenDangNhap);
            stmt.setInt(3, trangThai);
            stmt.registerOutParameter(4, Types.INTEGER);  // Lấy ID hóa đơn vừa được tạo
            stmt.executeUpdate();
            return stmt.getInt(4); // Lấy ID hóa đơn
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Update hóa đơn
    public boolean updateHoaDon(int idHoaDon, float tongTien, int trangThai) {
        String sql = "EXEC UpdateHoaDon ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idHoaDon);
            stmt.setFloat(2, tongTien);
            stmt.setInt(3, trangThai);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete hóa đơn
    public boolean deleteHoaDon(int idHoaDon) {
        String sql = "EXEC DeleteHoaDon ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idHoaDon);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả hóa đơn
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String sql = "EXEC GetHoaDon";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon(
                        rs.getInt("id_hoaDon"),
                        rs.getDate("NgayLap"),
                        rs.getFloat("TongTien"),
                        rs.getInt("TrangThai"),
                        rs.getInt("id_ban"),
                        rs.getString("TenDangNhap")
                );
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }
}
