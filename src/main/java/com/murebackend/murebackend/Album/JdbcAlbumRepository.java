package com.murebackend.murebackend.Album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.murebackend.murebackend.Song.Song;

@Repository
public class JdbcAlbumRepository implements AlbumRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Album album) {
		return jdbcTemplate.update("INSERT INTO albums (title, year, created_at) VALUES (?,?, NOW())",
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
		return jdbcTemplate.query("SELECT * FROM albums WHERE title ILIKE ?",
				BeanPropertyRowMapper.newInstance(Album.class),
				searchQuery);
	}

	@Override
	public Album findById(Long id) {
		return null;
	}

	@Override
	public List<Song> getSongs(Long albumId) {
		return jdbcTemplate.query(
				"SELECT songs.title FROM album_song LEFT JOIN songs ON song_id = songs.id " +
						"WHERE album_id = ?",
				BeanPropertyRowMapper.newInstance(Song.class),
				albumId);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int addSong(Long albumId, Long songId) {
		return jdbcTemplate.update("INSERT INTO album_song (album_id, song_id) VALUES (?, ?)",
				albumId, songId);
	}

}
