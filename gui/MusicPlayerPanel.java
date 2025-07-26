package gui;

import player.MusicPlayer;
import player.Song;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MusicPlayerPanel extends JPanel {
    private MusicPlayer musicPlayer;
    private JButton playButton, pauseButton, stopButton, loadSongButton, volumeUpButton, volumeDownButton, fastForwardButton, rewindButton;
    private JLabel nowPlayingLabel;

    public MusicPlayerPanel(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Now Playing Label
        nowPlayingLabel = new JLabel("Now Playing: None", SwingConstants.CENTER);
        nowPlayingLabel.setForeground(Color.WHITE);
        nowPlayingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nowPlayingLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(nowPlayingLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4, 10, 10));
        buttonPanel.setBackground(new Color(45, 45, 45));

        // Initialize buttons
        playButton = createStyledButton("Play");
        pauseButton = createStyledButton("Pause");
        stopButton = createStyledButton("Stop");
        loadSongButton = createStyledButton("Load Song");
        volumeUpButton = createStyledButton("Volume Up");
        volumeDownButton = createStyledButton("Volume Down");
        fastForwardButton = createStyledButton("Fast Forward");
        rewindButton = createStyledButton("Rewind");

        // Add buttons to panel
        buttonPanel.add(loadSongButton);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(volumeUpButton);
        buttonPanel.add(volumeDownButton);
        buttonPanel.add(fastForwardButton);
        buttonPanel.add(rewindButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add button listeners
        addListeners();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180)); // Steel blue color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237)); // Light blue hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Original color
            }
        });

        return button;
    }

    private void addListeners() {
        // Load Song button action
        loadSongButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                String songName = fileChooser.getSelectedFile().getName();
                Song song = new Song("1", songName, "Unknown Artist", filePath);
                musicPlayer.playSong(song);
                nowPlayingLabel.setText("Now Playing: " + songName);
            }
        });

        // Play button action
        playButton.addActionListener(e -> musicPlayer.resumeSong());

        // Pause button action
        pauseButton.addActionListener(e -> musicPlayer.pauseSong());

        // Stop button action
        stopButton.addActionListener(e -> {
            musicPlayer.stopSong();
            nowPlayingLabel.setText("Now Playing: None");
        });

        // Volume Up button action
        volumeUpButton.addActionListener(e -> musicPlayer.setVolume(musicPlayer.getCurrentVolume() + 2.0f));

        // Volume Down button action
        volumeDownButton.addActionListener(e -> musicPlayer.setVolume(musicPlayer.getCurrentVolume() - 2.0f));

        // Fast Forward button action
        fastForwardButton.addActionListener(e -> musicPlayer.fastForward(10));

        rewindButton.addActionListener(e -> musicPlayer.rewind(10)); 
    }
}
