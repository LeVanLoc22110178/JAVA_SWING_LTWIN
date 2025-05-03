package com.example.controller;

import com.example.dao.HoaDonDAO;
import com.example.model.HoaDon;
import com.example.view.HoaDonView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HoaDonController {
    private HoaDonDAO hoaDonDAO;
    private HoaDonView view;

    public HoaDonController(HoaDonView view) {
        this.hoaDonDAO = new HoaDonDAO();
        this.view = view;
    }

    // Load invoices and update the JTable
    public void loadInvoices() {
        List<HoaDon> hoaDonList = hoaDonDAO.getAllHoaDon();
        DefaultTableModel model = (DefaultTableModel) view.getDgvInvoices().getModel();
        model.setRowCount(0); // Clear existing data
        for (HoaDon hoaDon : hoaDonList) {
            model.addRow(new Object[]{hoaDon.getIdHoaDon(), hoaDon.getIdBan(), hoaDon.getTenDangNhap(), hoaDon.getTongTien(), hoaDon.getTrangThai()});
        }
    }

    // Add a new invoice
    public void addInvoice() {
        String username = view.getTxtUsername().getText();
        String tableID = view.getTxtTableID().getText();
        String totalAmount = view.getTxtTotalAmount().getText();
        String status = view.getTxtStatus().getText();

        // Validate input
        if (username.isEmpty() || tableID.isEmpty() || totalAmount.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }

        try {
            int tableIDInt = Integer.parseInt(tableID);
            float totalAmountFloat = Float.parseFloat(totalAmount);
            int statusInt = Integer.parseInt(status);

            int invoiceID = hoaDonDAO.insertHoaDon(tableIDInt, username, statusInt);
            if (invoiceID != -1) {
                JOptionPane.showMessageDialog(view, "Invoice added successfully!");
                loadInvoices(); // Refresh table data
                clearInputs();
            } else {
                JOptionPane.showMessageDialog(view, "Error adding invoice.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid input. Please enter valid numbers for Table ID, Total Amount, and Status.");
        }
    }

    // Update an existing invoice
    public void updateInvoice() {
        String invoiceID = view.getTxtInvoiceID().getText();
        String totalAmount = view.getTxtTotalAmount().getText();
        String status = view.getTxtStatus().getText();

        if (invoiceID.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select an invoice to update.");
            return;
        }

        try {
            int invoiceIDInt = Integer.parseInt(invoiceID);
            float totalAmountFloat = Float.parseFloat(totalAmount);
            int statusInt = Integer.parseInt(status);

            boolean success = hoaDonDAO.updateHoaDon(invoiceIDInt, totalAmountFloat, statusInt);
            if (success) {
                JOptionPane.showMessageDialog(view, "Invoice updated successfully!");
                loadInvoices(); // Refresh table data
                clearInputs();
            } else {
                JOptionPane.showMessageDialog(view, "Error updating invoice.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid input. Please enter valid numbers for Total Amount and Status.");
        }
    }

    // Delete an invoice
    public void deleteInvoice() {
        String invoiceID = view.getTxtInvoiceID().getText();

        if (invoiceID.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select an invoice to delete.");
            return;
        }

        try {
            int invoiceIDInt = Integer.parseInt(invoiceID);

            int response = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this invoice?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                boolean success = hoaDonDAO.deleteHoaDon(invoiceIDInt);
                if (success) {
                    JOptionPane.showMessageDialog(view, "Invoice deleted successfully!");
                    loadInvoices(); // Refresh table data
                    clearInputs();
                } else {
                    JOptionPane.showMessageDialog(view, "Error deleting invoice.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid Invoice ID.");
        }
    }

    // Clear input fields
    private void clearInputs() {
        view.getTxtInvoiceID().setText("");
        view.getTxtTableID().setText("");
        view.getTxtUsername().setText("");
        view.getTxtTotalAmount().setText("");
        view.getTxtStatus().setText("");
    }
}
