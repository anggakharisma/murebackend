package com.murebackend.murebackend.Song;

import java.util.List;

public interface SongRepository {
    int save(Song song);
    Song getSong(Long id);
    List<Song> searchSong(String searchQuery);
}
