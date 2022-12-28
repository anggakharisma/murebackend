package com.murebackend.murebackend.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murebackend.murebackend.Song.Song;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
	@Autowired
	AlbumRepository albumRepository;

	@GetMapping("/")
	public ResponseEntity<?> getAlbums() {
		return new ResponseEntity<>(albumRepository.getAlbums(), HttpStatus.OK);
	}

	@GetMapping("/{searchQuery}")
	public ResponseEntity<?> findAlbumByName(@PathVariable("searchQuery") String searchQuery) {
		List<Album> albums = albumRepository.findAlbumByName(searchQuery);
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@GetMapping("/{id}/songs")
	public ResponseEntity<?> getSongs(@PathVariable("id") Long id) {
		List<Song> songs = albumRepository.getSongs(id)	;
		return new ResponseEntity<>(songs, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> addAlbum(@Valid @RequestBody Album album) {
		try {
			Map<String, Object> response = new HashMap<>();
			albumRepository.save(album);

			response.put("message", album.getTitle() + " added");

			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", e.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
		}
	}
}
