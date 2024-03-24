package com.murebackend.murebackend.Song;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcSongRepository implements SongRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcSongRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Long save(Song song) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO songs (title, year, created_at) VALUES (?,?, NOW())", new String[] { "id" });

			ps.setString(1, song.getTitle());
			ps.setInt(2, song.getYear());

			return ps;

		}, keyHolder);

		return keyHolder.getKey().longValue();

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

		String query = "SELECT * FROM songs WHERE title ILIKE concat('%', ?, '%') ORDER BY title LIMIT ? OFFSET ?";
		List<Song> songs = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Song.class),
				new Object[] { searchQuery, pageable.getPageSize(), pageable.getOffset() });

		return new PageImpl<>(songs, pageable, total);
	}

	@Override
	public void saveSongArtist(Long songId, Long artistId) {
		log.info("{} {}", artistId, songId);
		// jdbcTemplate.update(
		// "INSERT INTO album_song (album_id, song_id, created_at) VALUES (?, ?,
		// NOW())",
		// songId, artistId);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public void updateSong(Song song) {
		// !TODO: Auto-generated method stub
	}

	@Override
	public void updateImage(Song song) {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE songs SET image_path = ? WHERE id = ?");

			ps.setString(1, song.getImagePath());
			ps.setFloat(2, song.getId());

			return ps;
		});
	}

	@Override
	public void updateAudioFile(Song song) {
		// !TODO: Auto-generated method stub
	}
}
