package com.murebackend.murebackend.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcSongRepository implements SongRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Song song) {
		return jdbcTemplate.update("INSERT INTO artists (name, year) VALUES (?,?)",
                song.getName(), song.getYear());
    }

    @Override
    public Song getSong(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?",
					BeanPropertyRowMapper.newInstance(Song.class), id);
    }

    @Override
    public List<Song> searchSong(String searchQuery) {
        return jdbcTemplate.query("SELECT * FROM songs WHERE name ILIKE ?",
				new Object[]{searchQuery},
				BeanPropertyRowMapper.newInstance(Song.class));
    }
}
