package com.murebackend.murebackend.Album;

import java.util.List;

import com.murebackend.murebackend.Song.Song;

public interface AlbumRepository {
	int save(Album album);
	int update(Album album);
	List<Album> getAlbums();
	List<Album> findAlbumByName(String searchQuery);
	Album findById(Long id);
	List<Song> getSongs(Long id);
	int delete(Long id);
}
