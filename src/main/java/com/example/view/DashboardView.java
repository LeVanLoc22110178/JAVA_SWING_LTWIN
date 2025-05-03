package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public DashboardView() {
        setTitle("Dashboard");
        setSize(1000, 600); // Increased width for sidebar + content
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Thiết lập CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Thêm các View vào cardPanel
        JPanel dashboardContent = createDashboardContent();
        cardPanel.add(dashboardContent, "Dashboard");

        // Thêm các views khác vào cardPanel
        cardPanel.add(new BanView(), "BanView");
        cardPanel.add(new DatHangView(), "DatHangView");
        cardPanel.add(new HoaDonView(), "HoaDonView");
        cardPanel.add(new LoaiThucUongView(), "LoaiThucUongView");
        cardPanel.add(new NhanVienView(), "NhanVienView");
        cardPanel.add(new ThucUongView(), "ThucUongView");
        cardPanel.add(new TaiKhoanView(), "TaiKhoanView");

        // Create the sidebar panel
        JPanel sidebarPanel = createSidebar();

        // Set up layout with BorderLayout (sidebar on the left, content on the right)
        setLayout(new BorderLayout());
        add(sidebarPanel, BorderLayout.WEST);  // Add sidebar to the left
        add(cardPanel, BorderLayout.CENTER);  // Add content (views) to the center

        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));  // Arrange buttons vertically
        sidebar.setPreferredSize(new Dimension(250, 0));  // Set width of sidebar
        sidebar.setBackground(new Color(40, 40, 40));  // Set dark background color

        // Create buttons for each view
        JButton btnBanView = createSidebarButton("Bàn", "BanView");
        JButton btnDatHangView = createSidebarButton("Đặt Hàng", "DatHangView");
        JButton btnHoaDonView = createSidebarButton("Hóa Đơn", "HoaDonView");
        JButton btnLoaiThucUongView = createSidebarButton("Loại Thức Uống", "LoaiThucUongView");
        JButton btnNhanVienView = createSidebarButton("Nhân Viên", "NhanVienView");
        JButton btnThucUongView = createSidebarButton("Thức Uống", "ThucUongView");
        JButton btnTaiKhoanView = createSidebarButton("Tài Khoản", "TaiKhoanView");

        // Add buttons to the sidebar
        sidebar.add(Box.createVerticalStrut(10));  // Add spacing at the top
        sidebar.add(btnBanView);
        sidebar.add(Box.createVerticalStrut(10));  // Add spacing between buttons
        sidebar.add(btnDatHangView);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnHoaDonView);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnLoaiThucUongView);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnNhanVienView);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnThucUongView);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnTaiKhoanView);
        sidebar.add(Box.createVerticalStrut(10));  // Add spacing at the bottom

        return sidebar;
    }

    private JButton createSidebarButton(String text, String viewName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));  // Change the font style to bold and bigger
        button.setBackground(new Color(60, 60, 60));  // Dark background for buttons
        button.setForeground(Color.WHITE);  // White text
        button.setFocusPainted(false);  // Remove focus highlight
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));  // Add padding to buttons
        button.setPreferredSize(new Dimension(220, 50));  // Increase button size for better visibility

        // Center-align text in button
        button.setHorizontalAlignment(SwingConstants.CENTER);

        // Change button background on hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 100, 100));  // Lighter background when hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));  // Original background when not hovering
            }
        });

        // Add action listener to change the view
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, viewName); // Switch to the corresponding view
            }
        });

        return button;
    }


    private JPanel createDashboardContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Content to display on the Dashboard view
        panel.add(new JLabel("Welcome to the Dashboard", JLabel.CENTER), BorderLayout.CENTER);

        return panel;
    }


}
