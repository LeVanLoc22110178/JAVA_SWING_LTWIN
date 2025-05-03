package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=QuanLyQuanTraSua";
    private static final String USER = "sa"; // Tên người dùng mặc định của SQL Server, thay đổi nếu cần
    private static final String PASSWORD = "leloc123"; // Thay đổi mật khẩu tương ứng

    // Tạo một kết nối duy nhất (Singleton Pattern)
    private static Connection connection;

    // Phương thức để lấy kết nối
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Tải driver JDBC cho SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Kết nối đến SQL Server
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection established.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Connection failed!");
            }
        }
        return connection;
    }

    // Đảm bảo đóng kết nối khi không sử dụng
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Đặt lại kết nối
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
