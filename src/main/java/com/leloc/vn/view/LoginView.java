package com.leloc.vn.view;

import com.leloc.vn.controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;
    private LoginController loginController;

    public LoginView() {
        loginController = new LoginController();

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout for login form
        setLayout(new GridLayout(3, 2));

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Validate login
                if (loginController.login(username, password)) {
                    lblMessage.setText("Login successful!");
                    // Close the login window
                    dispose();

                    // Open the DashboardView
                    new DashboardView(); // Open DashboardView
                } else {
                    lblMessage.setText("Invalid username or password!");
                }
            }
        });

        // Add components to the frame
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(lblMessage);

        setVisible(true);
    }

}
