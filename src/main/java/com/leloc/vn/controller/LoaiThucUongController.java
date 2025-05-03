package com.leloc.vn.controller;

import com.leloc.vn.dao.LoaiThucUongDAO;
import com.leloc.vn.model.LoaiThucUong;
import com.leloc.vn.view.LoaiThucUongView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LoaiThucUongController {
    private LoaiThucUongView view;
    private LoaiThucUongDAO loaiThucUongDAO;

    public LoaiThucUongController(LoaiThucUongView view) {
        this.view = view;
        this.loaiThucUongDAO = new LoaiThucUongDAO();
    }

    // Load categories and update JTable
    public void loadCategories() {
        List<LoaiThucUong> categories = loaiThucUongDAO.getAllLoaiThucUong();
        DefaultTableModel model = (DefaultTableModel) view.getDgvCategories().getModel();
        model.setRowCount(0); // Clear existing data
        for (LoaiThucUong category : categories) {
            model.addRow(new Object[]{category.getIdLoaiThucUong(), category.getTen(), category.getTrangThai()});
        }
    }

    // Add new category
    public void addLoaiThucUong() {
        String categoryName = view.getTxtCategoryName().getText();
        String status = view.getTxtStatus().getText();

        if (categoryName.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }

        try {
            int statusInt = Integer.parseInt(status);

            boolean success = loaiThucUongDAO.insertLoaiThucUong(categoryName, statusInt);
            if (success) {
                JOptionPane.showMessageDialog(view, "Category added successfully!");
                loadCategories(); // Refresh table
                clearInputs();
            } else {
                JOptionPane.showMessageDialog(view, "Error adding category.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Status must be a valid number.");
        }
    }

    // Update selected category
    public void updateLoaiThucUong() {
        String categoryID = view.getTxtCategoryID().getText();
        String categoryName = view.getTxtCategoryName().getText();
        String status = view.getTxtStatus().getText();

        if (categoryID.isEmpty() || categoryName.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }

        try {
            int categoryIDInt = Integer.parseInt(categoryID);
            int statusInt = Integer.parseInt(status);

            boolean success = loaiThucUongDAO.updateLoaiThucUong(categoryIDInt, categoryName, statusInt);
            if (success) {
                JOptionPane.showMessageDialog(view, "Category updated successfully!");
                loadCategories(); // Refresh table
                clearInputs();
            } else {
                JOptionPane.showMessageDialog(view, "Error updating category.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid input for Category ID or Status.");
        }
    }

    // Delete selected category
    public void deleteLoaiThucUong() {
        String categoryID = view.getTxtCategoryID().getText();

        if (categoryID.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a category to delete.");
            return;
        }

        try {
            int categoryIDInt = Integer.parseInt(categoryID);

            int response = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this category?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                boolean success = loaiThucUongDAO.deleteLoaiThucUong(categoryIDInt);
                if (success) {
                    JOptionPane.showMessageDialog(view, "Category deleted successfully!");
                    loadCategories(); // Refresh table
                    clearInputs();
                } else {
                    JOptionPane.showMessageDialog(view, "Error deleting category.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid Category ID.");
        }
    }

    // Clear input fields
    private void clearInputs() {
        view.getTxtCategoryID().setText("");
        view.getTxtCategoryName().setText("");
        view.getTxtStatus().setText("");
    }
}
