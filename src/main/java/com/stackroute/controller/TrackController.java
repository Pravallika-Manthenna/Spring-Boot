package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//used to mark it as request handler
@RestController
//used to map http requests to handler methods
@RequestMapping(value = "api/v1")
public class TrackController {

    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //used to handle POST requets
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        trackService.saveTrack(track);
        return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
    }

    //used to handle GET requets
    @GetMapping("track1")
    public ResponseEntity<?> getAllTracks() {

        trackService.getAllTracks();
        System.out.println("entered into get all tracks");
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    //used to handle PUT requets
    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track, @PathVariable int id) throws TrackAlreadyExistsException {
        trackService.updateTrack(track, id);
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
    }

    //    used to handle DELETE requets
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) throws TrackNotFoundException {
        trackService.deleteTrack(id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("trackByName")
    public ResponseEntity<?> trackByName(@RequestBody String name) throws TrackNotFoundException {

        trackService.trackByName(name);
        return new ResponseEntity<String>("track by name", HttpStatus.OK);

    }
}

