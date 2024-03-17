package com.murebackend.murebackend.Album;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.murebackend.murebackend.Song.Song;

public interface AlbumRepository {
	int save(Album album);
	void update(Album album, Long id);
	int addSong(Long albumId, Long songId);
	Page<Album> getAlbums(Pageable pageable);
	List<Album> findAlbumByName(String searchQuery);
	Album findById(Long id);
	List<Song> getSongs(Long albumId);
	int delete(Long id);
}
