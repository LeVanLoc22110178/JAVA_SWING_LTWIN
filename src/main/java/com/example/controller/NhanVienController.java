package com.example.controller;

import com.example.dao.NhanVienDAO;
import com.example.model.NhanVien;
import com.example.view.NhanVienView;

import javax.swing.*;
import java.util.List;

public class NhanVienController {
    private NhanVienView view;
    private NhanVienDAO nhanVienDAO;

    public NhanVienController(NhanVienView view) {
        this.view = view;
        this.nhanVienDAO = new NhanVienDAO();
    }

    // Load employees into the table
    public void loadEmployees() {
        List<NhanVien> nhanVienList = nhanVienDAO.getAllNhanVien();
        String[][] data = new String[nhanVienList.size()][7];
        for (int i = 0; i < nhanVienList.size(); i++) {
            NhanVien nv = nhanVienList.get(i);
            data[i][0] = String.valueOf(nv.getIdNhanVien());
            data[i][1] = nv.getTenDangNhap();
            data[i][2] = nv.getHoTen();
            data[i][3] = nv.getGioiTinh();
            data[i][4] = nv.getDienThoai();
            data[i][5] = nv.getChucVu();
            data[i][6] = String.valueOf(nv.getTrangThai());
        }

        String[] columns = {"Employee ID", "Username", "Full Name", "Gender", "Phone", "Position", "Status"};
        view.setEmployeeTableModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Add employee
    public void addEmployee() {
        String username = view.getTxtUsername().getText();
        String fullName = view.getTxtFullName().getText();
        String gender = view.getTxtGender().getText();
        String phone = view.getTxtPhone().getText();
        String position = view.getTxtPosition().getText();

        if (username.isEmpty() || fullName.isEmpty() || gender.isEmpty() || phone.isEmpty() || position.isEmpty()) {
            view.showMessage("Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = nhanVienDAO.insertNhanVien(username, fullName, gender, phone, position);
        if (success) {
            view.showMessage("Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployees();
        } else {
            view.showMessage("Error adding employee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update employee
    public void updateEmployee() {
        int employeeID = Integer.parseInt(view.getTxtEmployeeID().getText());
        String fullName = view.getTxtFullName().getText();
        String gender = view.getTxtGender().getText();
        String phone = view.getTxtPhone().getText();
        String position = view.getTxtPosition().getText();
        int status = Integer.parseInt(view.getTxtStatus().getText());

        if (fullName.isEmpty() || gender.isEmpty() || phone.isEmpty() || position.isEmpty()) {
            view.showMessage("Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = nhanVienDAO.updateNhanVien(employeeID, fullName, gender, phone, position, status);
        if (success) {
            view.showMessage("Employee updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployees();
        } else {
            view.showMessage("Error updating employee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete employee
    public void deleteEmployee() {
        int employeeID = Integer.parseInt(view.getTxtEmployeeID().getText());

        int confirmation = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = nhanVienDAO.deleteNhanVien(employeeID);
            if (success) {
                view.showMessage("Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadEmployees();
            } else {
                view.showMessage("Error deleting employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
