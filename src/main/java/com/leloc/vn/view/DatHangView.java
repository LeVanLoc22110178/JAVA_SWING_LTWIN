package com.leloc.vn.view;

import com.leloc.vn.controller.DatHangController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DatHangView extends JPanel { // Thay JFrame thành JPanel

    private JComboBox<String> comboBoxBan;
    private JComboBox<String> comboBoxThucUong;
    private JComboBox<String> comboBoxTaiKhoan;
    private JButton btnThem;
    private JButton btnXacNhan;
    private JTextField txtSoLuong;
    private JTable table;
    private DefaultTableModel tableModel;
    private DatHangController datHangController;

    public DatHangView() {
        datHangController = new DatHangController();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(6, 2)); // Adjusted the layout to add more components

        // Initialize ComboBoxes
        comboBoxBan = new JComboBox<>();
        comboBoxThucUong = new JComboBox<>();
        comboBoxTaiKhoan = new JComboBox<>();
        txtSoLuong = new JTextField();
        btnThem = new JButton("Thêm Vào Giỏ Hàng");
        btnXacNhan = new JButton("Xác Nhận Tạo Đơn Hàng");

        // Initialize the table and its model
        String[] columnNames = {"Bàn", "Thức Uống", "Tài Khoản", "Số Lượng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add table inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Populate ComboBoxes
        populateComboBoxBan();
        populateComboBoxThucUong();
        populateComboBoxTaiKhoan();

        // Add components to the panel (not the frame)
        add(new JLabel("Chọn Bàn:"));
        add(comboBoxBan);
        add(new JLabel("Chọn Thức Uống:"));
        add(comboBoxThucUong);
        add(new JLabel("Chọn Tài Khoản:"));
        add(comboBoxTaiKhoan);
        add(new JLabel("Số Lượng:"));
        add(txtSoLuong);
        add(btnThem);

        // Add table to the panel
        add(scrollPane);

        // Add the confirmation button
        add(new JLabel("")); // Empty space
        add(btnXacNhan);

        // Set button actions
        btnThem.addActionListener(e -> addToCart());
        btnXacNhan.addActionListener(e -> confirmOrder()); // Handle order confirmation
    }

    // Populate ComboBox with Ban data
    private void populateComboBoxBan() {
        List<String> danhSachBan = datHangController.getAllTenBan();
        for (String tenBan : danhSachBan) {
            comboBoxBan.addItem(tenBan);
        }
    }

    // Populate ComboBox with ThucUong data
    private void populateComboBoxThucUong() {
        List<String> danhSachThucUong = datHangController.getAllTenThucUong();
        for (String tenThucUong : danhSachThucUong) {
            comboBoxThucUong.addItem(tenThucUong);
        }
    }

    // Populate ComboBox with TaiKhoan data
    private void populateComboBoxTaiKhoan() {
        List<String> danhSachTaiKhoan = datHangController.getAllTenTaiKhoan();
        for (String tenTaiKhoan : danhSachTaiKhoan) {
            comboBoxTaiKhoan.addItem(tenTaiKhoan);
        }
    }

    // Add the selected information to the cart table
    private void addToCart() {
        String tenBan = (String) comboBoxBan.getSelectedItem();
        String tenThucUong = (String) comboBoxThucUong.getSelectedItem();
        String tenTaiKhoan = (String) comboBoxTaiKhoan.getSelectedItem();
        String soLuong = txtSoLuong.getText();

        // Validate if quantity is valid
        if (soLuong.isEmpty() || !soLuong.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ.");
            return;
        }

        // Add a new row to the table with the selected information
        Object[] row = {tenBan, tenThucUong, tenTaiKhoan, soLuong};
        tableModel.addRow(row);

        // Optionally, reset the fields after adding to cart
        txtSoLuong.setText("");
    }

    // Confirm the order (will trigger the logic for creating the order)
    private void confirmOrder() {
        int rowCount = tableModel.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng.");
            return;
        }

        // Lấy thông tin từ giao diện
        String tenBan = (String) comboBoxBan.getSelectedItem();
        String tenTaiKhoan = (String) comboBoxTaiKhoan.getSelectedItem();

        // Duyệt qua tất cả các dòng trong bảng và lấy các thông tin sản phẩm
        List<Object[]> cartItems = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            String tenThucUong = (String) tableModel.getValueAt(i, 1);
            String soLuongStr = (String) tableModel.getValueAt(i, 3);
            cartItems.add(new Object[] {tenBan, tenThucUong, tenTaiKhoan, soLuongStr});
        }

        // Gọi phương thức createOrder để xử lý đơn hàng
        boolean isSuccess = datHangController.createOrder(tenBan, tenTaiKhoan, cartItems);
        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Đơn hàng đã được xác nhận!");
            tableModel.setRowCount(0);  // Xóa giỏ hàng sau khi xác nhận đơn hàng
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi tạo đơn hàng.");
        }
    }
}
