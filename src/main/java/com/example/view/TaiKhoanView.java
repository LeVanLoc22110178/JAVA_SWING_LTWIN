package com.example.view;

import com.example.controller.TaiKhoanController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class TaiKhoanView extends JPanel {
    private JTextField txtUsername, txtPassword, txtAccountType, txtStatus;
    private JButton btnAdd, btnUpdate, btnDelete;
    private JTable dgvAccounts;
    private TaiKhoanController controller;

    public TaiKhoanView() {

        setSize(800, 500);

        setLayout(new BorderLayout());

        // Initialize controller
        controller = new TaiKhoanController(this);

        // Input fields
        JPanel panelInput = new JPanel(new GridLayout(4, 2));
        panelInput.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panelInput.add(txtUsername);

        panelInput.add(new JLabel("Password:"));
        txtPassword = new JTextField();
        panelInput.add(txtPassword);

        panelInput.add(new JLabel("Account Type:"));
        txtAccountType = new JTextField();
        panelInput.add(txtAccountType);

        panelInput.add(new JLabel("Status:"));
        txtStatus = new JTextField();
        panelInput.add(txtStatus);

        // Buttons
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add Account");
        btnAdd.addActionListener(e -> controller.addAccount());
        panelButtons.add(btnAdd);

        btnUpdate = new JButton("Update Account");
        btnUpdate.addActionListener(e -> controller.updateAccount());
        panelButtons.add(btnUpdate);

        btnDelete = new JButton("Delete Account");
        btnDelete.addActionListener(e -> controller.deleteAccount());
        panelButtons.add(btnDelete);

        // DataGrid for accounts
        dgvAccounts = new JTable();
        JScrollPane scrollPane = new JScrollPane(dgvAccounts);

        // Layout setup
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.add(panelInput);
        panelTop.add(panelButtons);
        panelTop.add(scrollPane);

        add(panelTop, BorderLayout.CENTER);

        // Automatically load accounts when the view is loaded
        loadAccounts();

        // Add selection listener to the table to populate textfields when a row is selected
        dgvAccounts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dgvAccounts.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get values from the selected row and set them to text fields
                        txtUsername.setText(dgvAccounts.getValueAt(selectedRow, 0).toString());
                        txtPassword.setText(dgvAccounts.getValueAt(selectedRow, 1).toString());
                        txtAccountType.setText(dgvAccounts.getValueAt(selectedRow, 2).toString());
                        txtStatus.setText(dgvAccounts.getValueAt(selectedRow, 3).toString());
                    }
                }
            }
        });
    }

    // Method to load accounts into the table
    public void loadAccounts() {
        controller.loadAccounts();
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtAccountType() {
        return txtAccountType;
    }

    public JTextField getTxtStatus() {
        return txtStatus;
    }

    public JTable getDgvAccounts() {
        return dgvAccounts;
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public void setAccountsTableModel(javax.swing.table.TableModel model) {
        dgvAccounts.setModel(model);
    }


}
