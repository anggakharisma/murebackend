package com.murebackend.murebackend.Album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.murebackend.murebackend.Song.Song;

public class JdbcAlbumRepository implements AlbumRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Album album) {
		return jdbcTemplate.update("INSERT INTO songs (title, year, created_at) VALUES (?,?, NOW())",
				album.getTitle(), album.getYear());
	}

	@Override
	public int update(Album album) {
		return 0;
	}

	@Override
	public List<Album> getAlbums() {
		return jdbcTemplate.query("SELECT * FROM albums", BeanPropertyRowMapper.newInstance(Album.class));
	}

	@Override
	public List<Album> findAlbumByName(String searchQuery) {
		return jdbcTemplate.query("SELECT * FROM albums WHERE name ILIKE ?",
				BeanPropertyRowMapper.newInstance(Album.class),
				searchQuery);
	}

	@Override
	public Album findById(Long id) {
		return null;
	}

	@Override
	public List<Song> getSongs(Long id) {
		return jdbcTemplate.query("SELECT songs.title FROM album_song LEFT JOIN songs ON song_id = songs.id WHERE album_id = id", 
				BeanPropertyRowMapper.newInstance(Song.class),
				id);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

}
