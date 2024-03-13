package com.murebackend.murebackend.Song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongRepository {
    int save(Song song);
    Song getSong(Long id);
    Page<Song> findSong(String searchQuery, Pageable pageable);
}
