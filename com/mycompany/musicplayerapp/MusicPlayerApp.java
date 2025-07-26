package com.mycompany.musicplayerapp;

import javax.swing.SwingUtilities;
import gui.MainFrame;

public class MusicPlayerApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
