package com.murebackend.murebackend.Album;

import java.util.List;

import com.murebackend.murebackend.Song.Song;

public interface AlbumRepository {
	int save(Album album);
	int update(Album album);
	List<Album> getAlbums();
	List<Album> findAlbumByName(String name);
	Album findById(Long id);
	List<Song> getAlbumSongs(Long id);
	int delete(Long id);
}
