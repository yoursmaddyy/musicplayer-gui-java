package player;

public class Song {
    private String songID;
    private String title;
    private String artist;
    private String filePath;

    // Constructor
    public Song(String songID, String title, String artist, String filePath) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
    }

    // Getters and Setters
    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
