package com.example.view;

import com.example.controller.BanController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BanView extends JPanel { // Thay JFrame thành JPanel
    private JTable dgvTables;
    private JTextField txtTableName;
    private JTextField txtStatus;
    private JTextField txtTableID;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private BanController banController;

    public BanView() {
        setLayout(new BorderLayout()); // Sử dụng BorderLayout cho layout

        // Create the form components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Table Name:"));
        txtTableName = new JTextField();
        panel.add(txtTableName);

        panel.add(new JLabel("Status:"));
        txtStatus = new JTextField();
        panel.add(txtStatus);

        txtTableID = new JTextField();
        txtTableID.setVisible(false);
        panel.add(txtTableID);

        btnAdd = new JButton("Add Table");
        btnUpdate = new JButton("Update Table");
        btnDelete = new JButton("Delete Table");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);

        add(panel, BorderLayout.NORTH);

        // JTable for displaying table list
        dgvTables = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Status"}, 0));
        JScrollPane scrollPane = new JScrollPane(dgvTables);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize controller
        banController = new BanController(dgvTables, txtTableName, txtStatus, txtTableID);

        // Load tables initially
        banController.loadTables();

        // Add button listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                banController.addTable();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                banController.updateTable();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                banController.deleteTable();
            }
        });

        // Table selection listener
        dgvTables.getSelectionModel().addListSelectionListener(e -> banController.tableSelectionChanged());
    }
}
