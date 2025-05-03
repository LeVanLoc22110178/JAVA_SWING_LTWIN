package com.example.view;

import com.example.model.ThucUong;
import com.example.controller.ThucUongController;
import com.example.dao.ThucUongDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class ThucUongView extends JPanel {
    private ThucUongController controller;
    private JTable table;
    private JTextField txtName, txtPrice, txtCategoryID, txtStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnChooseImage;
    private JLabel lblImage;
    private byte[] selectedImage = null; // Để lưu ảnh đã chọn
    private int selectedThucUongId = -1; // Biến lưu ID của thức uống đã chọn

    public ThucUongView() {
        controller = new ThucUongController(this, new ThucUongDAO());

        setSize(800, 600);  // Điều chỉnh kích thước cửa sổ

        setLayout(new BorderLayout());

        // Tạo một panel chính
        JPanel panelInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Tạo các label và text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInput.add(new JLabel("Tên Thức Uống:"), gbc);
        txtName = new JTextField(20); // Tăng kích thước TextField
        gbc.gridx = 1;
        panelInput.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInput.add(new JLabel("Giá Thức Uống:"), gbc);
        txtPrice = new JTextField(20); // Tăng kích thước TextField
        gbc.gridx = 1;
        panelInput.add(txtPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelInput.add(new JLabel("Category ID:"), gbc);
        txtCategoryID = new JTextField(20); // Tăng kích thước TextField
        gbc.gridx = 1;
        panelInput.add(txtCategoryID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelInput.add(new JLabel("Trạng Thái:"), gbc);
        txtStatus = new JTextField(20); // Tăng kích thước TextField
        gbc.gridx = 1;
        panelInput.add(txtStatus, gbc);

        // Thêm nút chọn ảnh
        btnChooseImage = new JButton("Chọn Ảnh");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelInput.add(btnChooseImage, gbc);

        // Thêm label để hiển thị ảnh
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(150, 150)); // Tăng kích thước label hiển thị ảnh
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 5;
        panelInput.add(lblImage, gbc);

        // Panel button
        JPanel panelButtons = new JPanel();
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Cập nhật");
        btnDelete = new JButton("Xóa");

        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm panel vào frame
        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        // Handle actions
        btnAdd.addActionListener(e -> addThucUong());
        btnUpdate.addActionListener(e -> updateThucUong());
        btnDelete.addActionListener(e -> deleteThucUong());
        btnChooseImage.addActionListener(e -> chooseImage());

        loadData();
    }

    // Hiển thị danh sách thức uống
    public void displayThucUong(List<ThucUong> thucUongs) {
        String[] columnNames = {"ID", "Tên", "Danh Mục", "Giá", "Trạng Thái"};
        Object[][] data = new Object[thucUongs.size()][5];

        for (int i = 0; i < thucUongs.size(); i++) {
            ThucUong thucUong = thucUongs.get(i);
            data[i][0] = thucUong.getIdThucUong();
            data[i][1] = thucUong.getTen();
            data[i][2] = thucUong.getIdLoaiThucUong();
            data[i][3] = thucUong.getGia();
            data[i][4] = thucUong.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);

        // Thêm sự kiện chọn hàng trong bảng để điền dữ liệu vào TextField
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedThucUongId = (int) table.getValueAt(selectedRow, 0); // Lưu ID của thức uống đã chọn
                    ThucUong selectedThucUong = thucUongs.stream()
                            .filter(thucUong -> thucUong.getIdThucUong() == selectedThucUongId)
                            .findFirst()
                            .orElse(null);

                    if (selectedThucUong != null) {
                        // Điền dữ liệu vào các TextField
                        txtName.setText(selectedThucUong.getTen());
                        txtPrice.setText(String.valueOf(selectedThucUong.getGia()));
                        txtCategoryID.setText(String.valueOf(selectedThucUong.getIdLoaiThucUong()));
                        txtStatus.setText(selectedThucUong.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng");

                        // Hiển thị ảnh của sản phẩm nếu có
                        if (selectedThucUong.getAnh() != null) {
                            Blob blob = selectedThucUong.getAnh();
                            try {
                                InputStream is = blob.getBinaryStream();
                                BufferedImage img = ImageIO.read(is);
                                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                                lblImage.setIcon(imageIcon);  // Hiển thị ảnh trong JLabel
                            } catch (IOException | SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    // Thêm thức uống
    private void addThucUong() {
        String ten = txtName.getText();
        float gia = Float.parseFloat(txtPrice.getText());
        int idLoaiThucUong = Integer.parseInt(txtCategoryID.getText());
        int trangThai = txtStatus.getText().equals("Còn hàng") ? 1 : 0;

        // Gọi controller để thêm thức uống, có ảnh đã chọn
        controller.addThucUong(ten, idLoaiThucUong, gia, trangThai, selectedImage);
    }

    // Cập nhật thức uống
    private void updateThucUong() {
        if (selectedThucUongId == -1) {
            showMessage("Vui lòng chọn một thức uống để sửa.");
            return;
        }

        String ten = txtName.getText();
        float gia = Float.parseFloat(txtPrice.getText());
        int idLoaiThucUong = Integer.parseInt(txtCategoryID.getText());
        int trangThai = txtStatus.getText().equals("Còn hàng") ? 1 : 0;

        // Cập nhật thức uống với ID đã chọn
        controller.updateThucUong(selectedThucUongId, ten, idLoaiThucUong, gia, trangThai, selectedImage);
    }

    // Xóa thức uống
    private void deleteThucUong() {
        if (selectedThucUongId == -1) {
            showMessage("Vui lòng chọn một thức uống để xóa.");
            return;
        }

        // Xóa thức uống với ID đã chọn
        controller.deleteThucUong(selectedThucUongId);
    }

    // Tải dữ liệu thức uống
    private void loadData() {
        controller.loadThucUong();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Lớp xử lý sự kiện chọn ảnh
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Hiển thị ảnh đã chọn
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                lblImage.setIcon(imageIcon);

                // Chuyển ảnh thành byte array
                FileInputStream fis = new FileInputStream(selectedFile);
                selectedImage = fis.readAllBytes();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
