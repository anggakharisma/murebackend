package com.murebackend.murebackend.Song;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    SongRepository songRepository;

    @PostMapping("/")
    public ResponseEntity<?> addSong(@Valid @RequestBody Song song) {
        try {
            Map<String, Object> response = new HashMap<>();
            songRepository.save(song);

            response.put("message", song.getTitle() + " added");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch(Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSong(@PathVariable("id") Long songId) {
        try {
            return new ResponseEntity<>(songRepository.getSong(songId), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Song not found");

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put(e.getMessage(), "Song not found");

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}
