package com.murebackend.murebackend.Album;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.murebackend.murebackend.Song.Song;
import com.murebackend.murebackend.Utils.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/albums")
@Slf4j
public class AlbumController {
	@Autowired
	AlbumRepository albumRepository;

	@GetMapping("/")
	public ResponseEntity<?> getAlbums(PagedResourcesAssembler<Album> assembler, Pageable pageable,
			@RequestParam("q") Optional<String> q) {
		try {

			Page<Album> albumsCollections = albumRepository.findAlbumByName(pageable, q.orElse(""));

			return new ResponseEntity<>(assembler.toModel(albumsCollections), HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Something went wrong");
			log.error("getAlbums: " + e.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAlbum(@PathVariable("id") Long id) {
		Album album = albumRepository.findById(id);
		return new ResponseEntity<>(album, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateSong(@Valid @RequestBody Album albumReqest, @PathVariable("id") Long id) {
		try {
			Album album = albumRepository.findById(id);
			albumRepository.update(albumReqest, id);

			Map<String, Object> response = new HashMap<>();
			response.put("message", "album updated");

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IncorrectResultSizeDataAccessException e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Album not found");

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", e.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}/songs")
	public ResponseEntity<?> getSongs(@PathVariable("id") Long id) {
		List<Song> songs = albumRepository.getSongs(id);
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

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile multipartFile)
			throws IOException {
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		String fileFullName = FileUploadUtil.saveFile(fileName, multipartFile, "images/song");
		Map<String, Object> response = new HashMap<>();
		response.put("path", "/images/albums/" + fileFullName);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
