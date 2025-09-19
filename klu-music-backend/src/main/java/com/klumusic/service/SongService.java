package com.klumusic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.klumusic.entity.Song;

/**
 * Service interface for managing songs.
 */
public interface SongService {

    /**
     * Retrieves all songs with pagination.
     * @param pageable Pagination information.
     * @return Page of Songs.
     */
    Page<Song> getAllSongs(Pageable pageable);

    /**
     * Gets a song by its unique ID.
     * @param id The song ID.
     * @return Song entity.
     * @throws RuntimeException if song not found.
     */
    Song getSongById(Long id);

    /**
     * Searches songs by given query string (e.g., title or artist name).
     * @param query Search keyword.
     * @return List of matching Songs.
     */
    List<Song> searchSongs(String query);

    /**
     * Retrieves trending songs limited by count.
     * @param limit Maximum number of songs to return.
     * @return List of trending Songs.
     */
    List<Song> getTrendingSongs(int limit);

    /**
     * Increments the play count of a song.
     * @param songId The song ID.
     */
    void incrementPlayCount(Long songId);

    /**
     * Adds a new song.
     * @param song Song entity to add.
     * @return Saved song entity with generated ID.
     */
    Song addSong(Song song);
    
    /**
     * Updates an existing song.
     * @param id The ID of the song to update.
     * @param song Updated song data.
     * @return Updated song entity.
     * @throws RuntimeException if song not found.
     */
    Song updateSong(Long id, Song song);

    /**
     * Deletes a song by ID.
     * @param id The ID of the song to delete.
     * @throws RuntimeException if song not found.
     */
    void deleteSong(Long id);
}
