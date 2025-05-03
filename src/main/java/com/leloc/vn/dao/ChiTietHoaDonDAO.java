package com.leloc.vn.dao;

import com.leloc.vn.model.ChiTietHoaDon;
import com.leloc.vn.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {

    private Connection connection;

    // Constructor để lấy kết nối chung
    public ChiTietHoaDonDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Method to insert a new item in the invoice
    public boolean insertChiTietHoaDon(int idHoaDon, int idThucUong, int soLuong, float donGia) {
        String sql = "EXEC InsertChiTietHoaDon ?, ?, ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idHoaDon);
            stmt.setInt(2, idThucUong);
            stmt.setInt(3, soLuong);
            stmt.setFloat(4, donGia);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to update the quantity of an item in the invoice
    public boolean updateChiTietHoaDon(int idChiTietHoaDon, int soLuong) {
        String sql = "EXEC UpdateChiTietHoaDon ?, ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idChiTietHoaDon);
            stmt.setInt(2, soLuong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to delete an item from the invoice
    public boolean deleteChiTietHoaDon(int idChiTietHoaDon) {
        String sql = "EXEC DeleteChiTietHoaDon ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idChiTietHoaDon);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to get all items in the invoice
    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        String sql = "EXEC GetChiTietHoaDon";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(
                        rs.getInt("id_chiTietHoaDon"),
                        rs.getInt("id_hoaDon"),
                        rs.getInt("id_thucUong"),
                        rs.getInt("SoLuong"),
                        rs.getFloat("DonGia")
                );
                chiTietHoaDonList.add(chiTietHoaDon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return chiTietHoaDonList;
    }
}
