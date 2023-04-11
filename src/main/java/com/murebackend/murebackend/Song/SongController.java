package com.murebackend.murebackend.Song;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.murebackend.murebackend.Utils.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/songs")
@Slf4j
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

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/image")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile, "images/song");
        String fileNameFull = fileCode + "-" + fileName;
        Map<String, Object> response = new HashMap<>();
        response.put("download_url", "/images/song/" + fileNameFull);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/audio-file")
    public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile multipartFile) {
        return new ResponseEntity<>("Ree", HttpStatus.OK);
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
