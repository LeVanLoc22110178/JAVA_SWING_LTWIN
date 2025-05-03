package com.leloc.vn.controller;

import com.leloc.vn.dao.TaiKhoanDAO;
import com.leloc.vn.model.TaiKhoan;
import com.leloc.vn.view.TaiKhoanView;

import javax.swing.*;
import java.util.List;

public class TaiKhoanController {
    private TaiKhoanView view;
    private TaiKhoanDAO dbTaiKhoan;

    public TaiKhoanController(TaiKhoanView view) {
        this.view = view;
        this.dbTaiKhoan = new TaiKhoanDAO();
    }

    // Load accounts into the table
    public void loadAccounts() {
        List<TaiKhoan> accounts = dbTaiKhoan.getAllTaiKhoan();
        if (accounts != null && !accounts.isEmpty()) {
            String[][] data = new String[accounts.size()][4];
            for (int i = 0; i < accounts.size(); i++) {
                TaiKhoan account = accounts.get(i);
                data[i][0] = account.getTenDangNhap();
                data[i][1] = account.getMatKhau();
                data[i][2] = account.getLoai();
                data[i][3] = String.valueOf(account.getTrangThai());
            }
            String[] columns = {"Username", "Password", "Account Type", "Status"};
            view.setAccountsTableModel(new javax.swing.table.DefaultTableModel(data, columns));
        }
    }

    // Add account
    public void addAccount() {
        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        String accountType = view.getTxtAccountType().getText();

        if (username.isEmpty() || password.isEmpty() || accountType.isEmpty()) {
            view.showMessage("Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = dbTaiKhoan.insertTaiKhoan(username, password, accountType);
        if (success) {
            view.showMessage("Account added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadAccounts();
        } else {
            view.showMessage("Error adding account.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update account
    public void updateAccount() {
        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        String accountType = view.getTxtAccountType().getText();
        String statusText = view.getTxtStatus().getText();

        if (username.isEmpty() || password.isEmpty() || accountType.isEmpty() || statusText.isEmpty()) {
            view.showMessage("Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int status = Integer.parseInt(statusText);
        boolean success = dbTaiKhoan.updateTaiKhoan(username, password, accountType, status);
        if (success) {
            view.showMessage("Account updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadAccounts();
        } else {
            view.showMessage("Error updating account.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete account
    public void deleteAccount() {
        String username = view.getTxtUsername().getText();

        if (username.isEmpty()) {
            view.showMessage("Please select an account to delete.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this account?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = dbTaiKhoan.deleteTaiKhoan(username);
            if (success) {
                view.showMessage("Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadAccounts();
            } else {
                view.showMessage("Error deleting account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
