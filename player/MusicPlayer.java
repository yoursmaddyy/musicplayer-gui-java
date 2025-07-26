package player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip audioClip;
    private FloatControl volumeControl;
    private Song currentSong;

    public void playSong(Song song) {
        try {
            if (audioClip != null && audioClip.isRunning()) {
                audioClip.stop();
            }

            File audioFile = new File(song.getFilePath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);

            audioClip.start();
            currentSong = song;

            System.out.println("Playing: " + song.getTitle());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing song: " + e.getMessage());
        }
    }

    public void pauseSong() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            System.out.println("Paused: " + currentSong.getTitle());
        }
    }

    public void resumeSong() {
        if (audioClip != null && !audioClip.isRunning()) {
            audioClip.start();
            System.out.println("Resumed: " + currentSong.getTitle());
        }
    }

    public void stopSong() {
        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
            System.out.println("Stopped: " + currentSong.getTitle());
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float newVolume = Math.min(Math.max(volume, min), max); // Clamp volume within bounds
            volumeControl.setValue(newVolume);
            System.out.println("Volume set to: " + newVolume);
        }
    }

    public void fastForward(int seconds) {
        if (audioClip != null) {
            long currentPosition = audioClip.getMicrosecondPosition();
            long newPosition = currentPosition + (seconds * 1_000_000L);
            newPosition = Math.min(newPosition, audioClip.getMicrosecondLength()); // Clamp to track length
            audioClip.setMicrosecondPosition(newPosition);
            System.out.println("Fast-forwarded to: " + newPosition / 1_000_000 + " seconds");
        }
    }

    public void rewind(int seconds) {
        if (audioClip != null) {
            long currentPosition = audioClip.getMicrosecondPosition();
            long newPosition = currentPosition - (seconds * 1_000_000L);
            newPosition = Math.max(newPosition, 0); // Clamp to start
            audioClip.setMicrosecondPosition(newPosition);
            System.out.println("Rewinded to: " + newPosition / 1_000_000 + " seconds");
        }
    }
    
     public float getCurrentVolume() {
        if (volumeControl != null) {
            return volumeControl.getValue();
        }
        return 0.0f;
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}
