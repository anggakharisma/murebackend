package com.murebackend.murebackend.Song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcSongRepository implements SongRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Song song) {
		return jdbcTemplate.update("INSERT INTO songs (title, year, artist_id, album_id, created_at) VALUES (?,?,?,?, NOW())",
				song.getTitle(), song.getYear(), song.getArtistId(), song.getAlbumId());
	}

	@Override
	public Song getSong(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM songs WHERE id = ?",
				BeanPropertyRowMapper.newInstance(Song.class), id);
	}

	@Override
	public List<Song> searchSong(String searchQuery) {
		return jdbcTemplate.query("SELECT * FROM songs WHERE name ILIKE ?",
				BeanPropertyRowMapper.newInstance(Song.class),
				new Object[] { searchQuery });
	}
}
