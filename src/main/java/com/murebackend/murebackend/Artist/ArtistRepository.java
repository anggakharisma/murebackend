package com.murebackend.murebackend.Artist;

import java.util.List;

import com.murebackend.murebackend.Album.Album;
import com.murebackend.murebackend.Song.Song;

public interface ArtistRepository {
	int save(Artist artist);
	int update(Artist artist);
	List<Artist> getAllArtist();
	List<Artist> findArtistByName(String name);
	Artist findById(Long id);
	List<Album> getAlbums(Long id);
	List<Song> getSongs(Long id);
	int delete(Long id);
}
