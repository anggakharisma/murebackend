package com.murebackend.murebackend.Artist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcArtistRepository implements ArtistRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int save(Artist artist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Artist> getAllArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artist> findArtistByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Artist artist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Artist findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
