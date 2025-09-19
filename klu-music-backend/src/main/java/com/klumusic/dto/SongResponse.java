package com.klumusic.dto;

import java.time.LocalDateTime;

public class SongResponse {
    private Long id;
    private String title;
    private String artistName;
    private String albumTitle;
    private String coverUrl;
    private Integer durationSeconds;
    private String durationFormatted;
    private Long playCount;
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getAlbumTitle() { return albumTitle; }
    public void setAlbumTitle(String albumTitle) { this.albumTitle = albumTitle; }

    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }

    public Integer getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Integer durationSeconds) { this.durationSeconds = durationSeconds; }

    public String getDurationFormatted() { return durationFormatted; }
    public void setDurationFormatted(String durationFormatted) { this.durationFormatted = durationFormatted; }

    public Long getPlayCount() { return playCount; }
    public void setPlayCount(Long playCount) { this.playCount = playCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
