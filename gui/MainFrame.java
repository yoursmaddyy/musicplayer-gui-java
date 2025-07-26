package gui;

import auth.LoginSystem;
import player.MusicPlayer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Music Player App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create systems
        LoginSystem loginSystem = new LoginSystem();
        MusicPlayer musicPlayer = new MusicPlayer();

        // Create panels
        LoginPanel loginPanel = new LoginPanel(loginSystem);
        MusicPlayerPanel musicPlayerPanel = new MusicPlayerPanel(musicPlayer);

        // Set initial panel
        setContentPane(loginPanel);

        // Simulate login success and switch to music player panel
        loginPanel.loginButton.addActionListener(e -> {
            if (loginSystem.login(
                loginPanel.usernameField.getText(),
                new String(loginPanel.passwordField.getPassword()))) {
                setContentPane(musicPlayerPanel);
                validate(); // Refresh frame
            }
        });
    }

    }

