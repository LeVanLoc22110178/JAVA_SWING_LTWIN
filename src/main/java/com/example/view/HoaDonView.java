package com.example.view;

import com.example.controller.HoaDonController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HoaDonView extends JPanel {
    private JTable dgvInvoices;
    private JTextField txtInvoiceID;
    private JTextField txtTableID;
    private JTextField txtUsername;
    private JTextField txtTotalAmount;
    private JTextField txtStatus;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private HoaDonController controller;

    public HoaDonView() {
        // Initialize controller
        controller = new HoaDonController(this);

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create panel with GridBagLayout
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gridBagLayout);

        // Define GridBagConstraints for each component
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Invoice ID:"), gbc);

        txtInvoiceID = new JTextField();
        txtInvoiceID.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtInvoiceID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Table ID:"), gbc);

        txtTableID = new JTextField();
        gbc.gridx = 1;
        panel.add(txtTableID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Username:"), gbc);

        txtUsername = new JTextField();
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Total Amount:"), gbc);

        txtTotalAmount = new JTextField();
        gbc.gridx = 1;
        panel.add(txtTotalAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Status:"), gbc);

        txtStatus = new JTextField();
        gbc.gridx = 1;
        panel.add(txtStatus, gbc);

        btnAdd = new JButton("Add Invoice");
        btnUpdate = new JButton("Update Invoice");
        btnDelete = new JButton("Delete Invoice");

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btnAdd, gbc);

        gbc.gridy = 6;
        panel.add(btnUpdate, gbc);

        gbc.gridy = 7;
        panel.add(btnDelete, gbc);

        add(panel, BorderLayout.NORTH);

        // JTable for displaying invoices
        dgvInvoices = new JTable(new DefaultTableModel(new Object[]{"Invoice ID", "Table ID", "Username", "Total Amount", "Status"}, 0));
        JScrollPane scrollPane = new JScrollPane(dgvInvoices);
        add(scrollPane, BorderLayout.CENTER);

        // Add button listeners
        btnAdd.addActionListener(e -> controller.addInvoice());
        btnUpdate.addActionListener(e -> controller.updateInvoice());
        btnDelete.addActionListener(e -> controller.deleteInvoice());

        // Table selection listener
        dgvInvoices.getSelectionModel().addListSelectionListener(e -> {
            if (dgvInvoices.getSelectedRow() != -1) {
                txtInvoiceID.setText(dgvInvoices.getValueAt(dgvInvoices.getSelectedRow(), 0).toString());
                txtTableID.setText(dgvInvoices.getValueAt(dgvInvoices.getSelectedRow(), 1).toString());
                txtUsername.setText(dgvInvoices.getValueAt(dgvInvoices.getSelectedRow(), 2).toString());
                txtTotalAmount.setText(dgvInvoices.getValueAt(dgvInvoices.getSelectedRow(), 3).toString());
                txtStatus.setText(dgvInvoices.getValueAt(dgvInvoices.getSelectedRow(), 4).toString());
            }
        });

        // Load invoices initially
        controller.loadInvoices();
    }

    public JTable getDgvInvoices() {
        return dgvInvoices;
    }

    public JTextField getTxtInvoiceID() {
        return txtInvoiceID;
    }

    public JTextField getTxtTableID() {
        return txtTableID;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtTotalAmount() {
        return txtTotalAmount;
    }

    public JTextField getTxtStatus() {
        return txtStatus;
    }
}
