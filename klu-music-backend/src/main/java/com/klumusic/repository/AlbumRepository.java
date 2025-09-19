package com.klumusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klumusic.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistId(Long artistId);
    
    @Query("SELECT a FROM Album a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Album> findByTitleContainingIgnoreCase(@Param("title") String title);
    
    @Query("SELECT a FROM Album a ORDER BY a.createdAt DESC")
    List<Album> findAllOrderByCreatedAtDesc();
}

