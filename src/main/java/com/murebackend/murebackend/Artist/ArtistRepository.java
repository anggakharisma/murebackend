package com.murebackend.murebackend.Artist;

import java.util.List;

public interface ArtistRepository {
	public int save(Artist artist);
	public int update(Artist artist);
	List<Artist> getAllArtist();
	List<Artist> findArtistByName(String name);
	Artist findById(Long id);
	public int deleteById(Long id);
}
