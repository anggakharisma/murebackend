package com.murebackend.murebackend.Song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongRepository {
	Long save(Song song);

	void saveSongArtist(Long songId, Long artistId);

	void updateSong(Song song);

	void updateImage(Song song);

	void updateAudioFile(Song song);

	Song getSong(Long id);

	Page<Song> findSong(String searchQuery, Pageable pageable);
}
