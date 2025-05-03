package com.example.controller;

import com.example.dao.BanDAO;
import com.example.model.Ban;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BanController {
    private BanDAO banDAO;
    private JTable table;
    private JTextField txtTableName;
    private JTextField txtStatus;
    private JTextField txtTableID;

    public BanController(JTable table, JTextField txtTableName, JTextField txtStatus, JTextField txtTableID) {
        this.banDAO = new BanDAO();
        this.table = table;
        this.txtTableName = txtTableName;
        this.txtStatus = txtStatus;
        this.txtTableID = txtTableID;
    }

    // Load all tables into the JTable
    public void loadTables() {
        List<Ban> banList = banDAO.getAllBan();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data
        for (Ban ban : banList) {
            model.addRow(new Object[]{ban.getIdBan(), ban.getTen(), ban.getTrangThai()});
        }
    }

    // Add a new table
    public void addTable() {
        String name = txtTableName.getText();
        int status = Integer.parseInt(txtStatus.getText());
        String error = "";

        boolean success = banDAO.insertBan(name, status);
        if (success) {
            loadTables();
            JOptionPane.showMessageDialog(null, "Table added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
    }

    // Update an existing table
    public void updateTable() {
        int id = Integer.parseInt(txtTableID.getText());
        String name = txtTableName.getText();
        int status = Integer.parseInt(txtStatus.getText());
        String error = "";

        boolean success = banDAO.updateBan(id, name, status);
        if (success) {
            loadTables();
            JOptionPane.showMessageDialog(null, "Table updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
    }

    // Delete a table
    public void deleteTable() {
        int id = Integer.parseInt(txtTableID.getText());
        String error = "";

        boolean success = banDAO.deleteBan(id);
        if (success) {
            loadTables();
            JOptionPane.showMessageDialog(null, "Table deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
    }

    // Handle row selection in the table to populate the fields
    public void tableSelectionChanged() {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtTableID.setText(table.getValueAt(row, 0).toString());
            txtTableName.setText(table.getValueAt(row, 1).toString());
            txtStatus.setText(table.getValueAt(row, 2).toString());
        }
    }
}
