package com.leloc.vn.view;

import com.leloc.vn.controller.NhanVienController;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NhanVienView extends JPanel {
    private JTextField txtUsername, txtFullName, txtGender, txtPhone, txtPosition, txtStatus, txtEmployeeID;
    private JButton btnAdd, btnUpdate, btnDelete;
    private JTable dgvEmployees;
    private NhanVienController controller;

    public NhanVienView() {

        setSize(800, 500);

        setLayout(new BorderLayout());

        // Initialize controller
        controller = new NhanVienController(this);

        // Input fields panel (Using BoxLayout to make it look better)
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));

        panelInput.add(createInputPanel("Username:", txtUsername = new JTextField()));
        panelInput.add(createInputPanel("Full Name:", txtFullName = new JTextField()));
        panelInput.add(createInputPanel("Gender:", txtGender = new JTextField()));
        panelInput.add(createInputPanel("Phone:", txtPhone = new JTextField()));
        panelInput.add(createInputPanel("Position:", txtPosition = new JTextField()));
        panelInput.add(createInputPanel("Status:", txtStatus = new JTextField()));

        // Hidden employee ID (for update and delete)
        txtEmployeeID = new JTextField();
        txtEmployeeID.setVisible(false);
        panelInput.add(txtEmployeeID);

        // Buttons panel (horizontally aligned)
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        btnAdd = new JButton("Add Employee");
        btnAdd.addActionListener(e -> controller.addEmployee());
        panelButtons.add(btnAdd);

        btnUpdate = new JButton("Update Employee");
        btnUpdate.addActionListener(e -> controller.updateEmployee());
        panelButtons.add(btnUpdate);

        btnDelete = new JButton("Delete Employee");
        btnDelete.addActionListener(e -> controller.deleteEmployee());
        panelButtons.add(btnDelete);

        // DataGrid for employees
        dgvEmployees = new JTable();
        JScrollPane scrollPane = new JScrollPane(dgvEmployees);

        // Layout setup
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.add(panelInput);
        panelTop.add(panelButtons);
        panelTop.add(scrollPane);

        add(panelTop, BorderLayout.CENTER);

        // Automatically load employees when the view is loaded
        loadEmployees();

        // Add selection listener to the table
        dgvEmployees.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dgvEmployees.getSelectedRow();
                    if (selectedRow != -1) {
                        txtEmployeeID.setText(dgvEmployees.getValueAt(selectedRow, 0).toString());
                        txtUsername.setText(dgvEmployees.getValueAt(selectedRow, 1).toString());
                        txtFullName.setText(dgvEmployees.getValueAt(selectedRow, 2).toString());
                        txtGender.setText(dgvEmployees.getValueAt(selectedRow, 3).toString());
                        txtPhone.setText(dgvEmployees.getValueAt(selectedRow, 4).toString());
                        txtPosition.setText(dgvEmployees.getValueAt(selectedRow, 5).toString());
                        txtStatus.setText(dgvEmployees.getValueAt(selectedRow, 6).toString());
                    }
                }
            }
        });
    }

    // Method to create input fields with labels
    private JPanel createInputPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        panel.add(label);
        textField.setPreferredSize(new Dimension(200, 20)); // Set preferred size for text field
        panel.add(textField);
        return panel;
    }

    // Method to load employees into the table
    public void loadEmployees() {
        controller.loadEmployees();
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtFullName() {
        return txtFullName;
    }

    public JTextField getTxtGender() {
        return txtGender;
    }

    public JTextField getTxtPhone() {
        return txtPhone;
    }

    public JTextField getTxtPosition() {
        return txtPosition;
    }

    public JTextField getTxtStatus() {
        return txtStatus;
    }

    public JTextField getTxtEmployeeID() {
        return txtEmployeeID;
    }

    public JTable getDgvEmployees() {
        return dgvEmployees;
    }

    public void setEmployeeTableModel(javax.swing.table.TableModel model) {
        dgvEmployees.setModel(model);
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }


}
