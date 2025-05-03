package com.leloc.vn.view;

import com.leloc.vn.controller.LoaiThucUongController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoaiThucUongView extends JPanel {
    private JTable dgvCategories;
    private JTextField txtCategoryID;
    private JTextField txtCategoryName;
    private JTextField txtStatus;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private LoaiThucUongController controller;

    public LoaiThucUongView() {
        // Initialize controller
        controller = new LoaiThucUongController(this);

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create panel with GridBagLayout for better control
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gridBagLayout);

        // Define GridBagConstraints for each component
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Category Name:"), gbc);

        txtCategoryName = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCategoryName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Status:"), gbc);

        txtStatus = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtStatus, gbc);

        txtCategoryID = new JTextField(20);
        txtCategoryID.setVisible(false);
        gbc.gridx = 1;
        panel.add(txtCategoryID, gbc);

        btnAdd = new JButton("Add Category");
        btnUpdate = new JButton("Update Category");
        btnDelete = new JButton("Delete Category");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnAdd, gbc);

        gbc.gridy = 3;
        panel.add(btnUpdate, gbc);

        gbc.gridy = 4;
        panel.add(btnDelete, gbc);

        add(panel, BorderLayout.NORTH);

        // JTable for displaying categories
        dgvCategories = new JTable(new DefaultTableModel(new Object[]{"Category ID", "Category Name", "Status"}, 0));
        JScrollPane scrollPane = new JScrollPane(dgvCategories);
        add(scrollPane, BorderLayout.CENTER);

        // Add button listeners
        btnAdd.addActionListener(e -> controller.addLoaiThucUong());
        btnUpdate.addActionListener(e -> controller.updateLoaiThucUong());
        btnDelete.addActionListener(e -> controller.deleteLoaiThucUong());

        // Table selection listener
        dgvCategories.getSelectionModel().addListSelectionListener(e -> {
            if (dgvCategories.getSelectedRow() != -1) {
                txtCategoryID.setText(dgvCategories.getValueAt(dgvCategories.getSelectedRow(), 0).toString());
                txtCategoryName.setText(dgvCategories.getValueAt(dgvCategories.getSelectedRow(), 1).toString());
                txtStatus.setText(dgvCategories.getValueAt(dgvCategories.getSelectedRow(), 2).toString());
            }
        });

        // Load categories initially
        controller.loadCategories();
    }

    // Getter methods for UI components
    public JTable getDgvCategories() {
        return dgvCategories;
    }

    public JTextField getTxtCategoryID() {
        return txtCategoryID;
    }

    public JTextField getTxtCategoryName() {
        return txtCategoryName;
    }

    public JTextField getTxtStatus() {
        return txtStatus;
    }
}
