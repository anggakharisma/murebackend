package com.murebackend.murebackend.Artist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/artists")
@Slf4j
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllArtists() {
        return new ResponseEntity<>(artistRepository.getAllArtist(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveArtists (@Valid @RequestBody Artist artist) {
        Map<String, Object> response = new HashMap<>();
        artistRepository.save(new Artist(artist.getName(),  artist.getDescription()));

        response.put("message", "saved");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
