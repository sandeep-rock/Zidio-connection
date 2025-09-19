package com.klumusic.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klumusic.dto.SongResponse;
import com.klumusic.entity.Song;
import com.klumusic.service.SongService;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<Page<SongResponse>> getAllSongs(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {

        Page<Song> songs = songService.getAllSongs(PageRequest.of(page, size));
        Page<SongResponse> resp = songs.map(this::toDto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        return ResponseEntity.ok(toDto(song));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SongResponse>> search(@RequestParam String q) {
        List<Song> songs = songService.searchSongs(q);
        List<SongResponse> resp = songs.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<SongResponse>> trending(
        @RequestParam(defaultValue = "10") int limit) {

        List<Song> songs = songService.getTrendingSongs(limit);
        List<SongResponse> resp = songs.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Void> play(@PathVariable Long id) {
        songService.incrementPlayCount(id);
        return ResponseEntity.ok().build();
    }

    private SongResponse toDto(Song s) {
        SongResponse dto = new SongResponse();
        dto.setId(s.getId());
        dto.setTitle(s.getTitle());

        // Since Song entity has String fields, set artist and album accordingly:
        dto.setArtistName(s.getArtist());
        dto.setAlbumTitle(s.getAlbum());

        dto.setCoverUrl(s.getCoverUrl());
        dto.setDurationSeconds(s.getDurationSeconds());
        int mins = s.getDurationSeconds() / 60;
        int secs = s.getDurationSeconds() % 60;
        dto.setDurationFormatted(mins + ":" + String.format("%02d", secs));
        dto.setPlayCount(s.getPlayCount());
        dto.setCreatedAt(s.getCreatedAt());
        return dto;
    }
}
