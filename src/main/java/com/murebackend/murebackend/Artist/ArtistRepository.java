package com.murebackend.murebackend.Artist;

import java.util.List;

public interface ArtistRepository {
	int save(Artist artist);
	int update(Artist artist);
	List<Artist> getAllArtist();
	List<Artist> findArtistByName(String name);
	Artist findById(Long id);
	int delete(Long id);
}
