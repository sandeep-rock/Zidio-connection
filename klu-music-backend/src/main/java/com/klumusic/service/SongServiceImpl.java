package com.klumusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.klumusic.entity.Song;
import com.klumusic.repository.SongRepository;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    // Your existing methods...

    @Override
    public Page<Song> getAllSongs(Pageable pageable) {
        return songRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Song not found"));
    }

    @Override
    public List<Song> searchSongs(String query) {
        return songRepository.searchSongs(query);
    }

    @Override
    public List<Song> getTrendingSongs(int limit) {
        return songRepository.findMostPlayedSongs(Pageable.ofSize(limit));
    }

    @Override
    public void incrementPlayCount(Long songId) {
        Song song = getSongById(songId);
        song.setPlayCount(song.getPlayCount() + 1);
        songRepository.save(song);
    }

    @Override
    public Song addSong(Song song) {
        if (song.getPlayCount() == null) {
            song.setPlayCount(0L);
        }
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(Long id, Song updatedSong) {
        Song song = getSongById(id);
        song.setTitle(updatedSong.getTitle());
        song.setArtist(updatedSong.getArtist());
        song.setAlbum(updatedSong.getAlbum());
        song.setDurationSeconds(updatedSong.getDurationSeconds());
        song.setCoverUrl(updatedSong.getCoverUrl());
        song.setStreamUrl(updatedSong.getStreamUrl());
        song.setFilePath(updatedSong.getFilePath());
        song.setType(updatedSong.getType());
        song.setPlayCount(updatedSong.getPlayCount());
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Long id) {
        Song song = getSongById(id);
        songRepository.delete(song);
    }

    // Add your method here
    public Song addOfflineSong() {
        Song song = new Song();
        song.setTitle("Hungry Cheetah");
        song.setArtist("Artist Name");
        song.setDurationSeconds(235);
        song.setCoverUrl("/covers/hungry-cheetah.jpg");
        song.setAlbum("Album Name");
        song.setFilePath("/audio/Hungry-Cheetah.mp3");
        song.setType("OFFLINE");
        song.setPlayCount(0L);

        return songRepository.save(song);
    }
}
