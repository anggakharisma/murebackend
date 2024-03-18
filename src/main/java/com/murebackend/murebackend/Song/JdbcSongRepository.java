package com.murebackend.murebackend.Song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
		return jdbcTemplate.update(
				"INSERT INTO songs (title, year, artist_id, album_id, created_at) VALUES (?,?,?,?, NOW())",
				song.getTitle(), song.getYear(), song.getArtistId(), song.getAlbumId());
	}

	@Override
	public Song getSong(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM songs WHERE id = ?",
				BeanPropertyRowMapper.newInstance(Song.class), id);
	}

	@Override
	public Page<Song> findSong(String searchQuery, Pageable pageable) {
		String countQuery = "SELECT COUNT(*) FROM songs";
		int total = jdbcTemplate.queryForObject(countQuery, Integer.class);

		String query = "SELECT * FROM songs WHERE title ILIKE ? ORDER BY title LIMIT ? OFFSET ?";
		List<Song> songs = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Song.class),
				new Object[] { searchQuery, pageable.getPageSize(), pageable.getOffset() });

		return new PageImpl<>(songs, pageable, total);
	}
}
