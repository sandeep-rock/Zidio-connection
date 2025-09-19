package com.klumusic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klumusic.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtistId(Long artistId);
    List<Song> findByAlbumId(Long albumId);
    
    @Query("SELECT s FROM Song s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(s.artist.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Song> searchSongs(@Param("query") String query);
    
    @Query("SELECT s FROM Song s ORDER BY s.playCount DESC")
    List<Song> findMostPlayedSongs(Pageable pageable);
    
    @Query("SELECT s FROM Song s ORDER BY s.createdAt DESC")
    List<Song> findRecentSongs(Pageable pageable);
    
    Page<Song> findAllByOrderByCreatedAtDesc(Pageable pageable);
	public static final Song saveAll = new Song();
}

