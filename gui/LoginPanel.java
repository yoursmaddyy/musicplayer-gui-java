package gui;

import auth.LoginSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    LoginSystem loginSystem;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, registerButton, resetPasswordButton;

    public LoginPanel(LoginSystem loginSystem) {
        this.loginSystem = loginSystem;

        // Set layout and background color
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255)); // Light blue background

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        resetPasswordButton = new JButton("Reset Password");

        // Style buttons
        customizeButton(loginButton);
        customizeButton(registerButton);
        customizeButton(resetPasswordButton);

        // Use GridBagLayout for better alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridy = 3;
        add(registerButton, gbc);

        gbc.gridy = 4;
        add(resetPasswordButton, gbc);

        // Add button listeners
        addListeners();
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }

    private void addListeners() {
        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (loginSystem.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
                }
            }
        });

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter Username:");
                String password = JOptionPane.showInputDialog("Enter Password:");
                String email = JOptionPane.showInputDialog("Enter Email:");
                String userID = String.valueOf(System.currentTimeMillis()); // Simple ID generation

                if (loginSystem.register(username, password, email)) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists. Try again.");
                }
            }
        });

        // Reset Password button action
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog("Enter Email:");
                String newPassword = JOptionPane.showInputDialog("Enter New Password:");

                if (loginSystem.resetPassword(email, newPassword)) {
                    JOptionPane.showMessageDialog(null, "Password reset successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Email not found.");
                }
            }
        });
    }
}
