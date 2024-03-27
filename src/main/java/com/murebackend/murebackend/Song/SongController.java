package com.murebackend.murebackend.Song;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.murebackend.murebackend.Album.Album;
import com.murebackend.murebackend.Album.AlbumRepository;
import com.murebackend.murebackend.Config.JwtTokenUtil;
import com.murebackend.murebackend.Utils.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/songs")
@Slf4j
public class SongController {

    @Autowired
    SongRepository songRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("")
    public ResponseEntity<?> getSongs(Pageable pageable, PagedResourcesAssembler<Song> assembler) {
        try {
            Page<Song> songsCollections = songRepository.findSong("", pageable);
            return new ResponseEntity<>(assembler.toModel(songsCollections), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errResponse = new HashMap<>();
            errResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errResponse, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(HttpServletRequest request, @Valid @RequestBody SongRequest songRequest) {
        try {
            Map<String, Object> response = new HashMap<>();
            Long songId = songRepository.save(songRequest);
            final String requestTokenHeader = request.getHeader("Authorization");
            String jwtToken = requestTokenHeader.substring(7);
            String userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
            
            log.info("USERID: " + userId);

            songRepository.saveSongArtist(songId, Long.valueOf(userId));

            Album album = albumRepository.findById(songRequest.getAlbumId());
            songRepository.saveSongAlbums(songId, songRequest.getAlbumId());

            response.put("message", songRequest.getTitle() + " added");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Something went wrong");

            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{id}/image")
    public ResponseEntity<Map<String, Object>> uploadImage(@PathVariable("id") Long id,
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {
        Song song = songRepository.getSong(id);

        String fileName = org.springframework.util.StringUtils
                .cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String fileFullName = FileUploadUtil.saveFile(fileName, multipartFile, "images/song");
        String imagePath = "/images/song/" + fileFullName;

        log.info(imagePath);
        song.setImagePath(imagePath);
        songRepository.updateImage(song);

        Map<String, Object> response = new HashMap<>();
        response.put("path", imagePath);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("{id}/audio-file")
    public ResponseEntity<?> uploadAudio(@PathVariable("id") Long id, @RequestParam("file") MultipartFile multipartFile)
            throws IOException {

        Song song = songRepository.getSong(id);
        // !TODO: Run ffmpeg convert to HLS

        // ProcessBuilder pb = new ProcessBuilder(new String[]{ "bash", "-c", "ls
        // /home/dev" });
        // pb.redirectOutput(Redirect.INHERIT);
        // pb.redirectError(Redirect.INHERIT);

        // Process p = pb.start();

        String fileName = org.springframework.util.StringUtils
                .cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile, "songs/");
        String fileNameFull = fileCode + "_" + fileName;
        song.setAudioPath("/songs/" + fileNameFull);

        songRepository.updateAudioFile(song);

        Map<String, Object> response = new HashMap<>();
        response.put("path", "/songs/" + fileNameFull);

        return new ResponseEntity<>(response, HttpStatus.OK);
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
