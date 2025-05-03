package com.example;

import com.example.view.LoginView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Đảm bảo giao diện người dùng được chạy trên Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Mở LoginView khi chương trình bắt đầu
                new LoginView();
            }
        });
    }
}
