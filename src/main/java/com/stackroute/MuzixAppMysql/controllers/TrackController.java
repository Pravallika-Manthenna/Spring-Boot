package com.stackroute.MuzixAppMysql.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MuzixAppMysql.domain.Result;
import com.stackroute.MuzixAppMysql.domain.Track;
import com.stackroute.MuzixAppMysql.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixAppMysql.exceptions.TrackNotFoundException;
import com.stackroute.MuzixAppMysql.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

//rest controller annotation is used which contains all the restfull operations
@RestController
@CrossOrigin(origins = "*")
@ControllerAdvice(basePackages = "com.stackroute.muzixapp")
@Slf4j
public class TrackController {

    TrackService trackService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TrackController(TrackService trackService)
    {
        this.trackService = trackService;
    }

        //logic for save method using post method
        @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
      logger.info("entered into save track method");
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<Track>(track, HttpStatus.CREATED);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

       //logic for save all tracks method using post method
    @PostMapping("alltracks")
    public ResponseEntity<?> saveAllTrack(@RequestBody List<Track> trackList) throws TrackAlreadyExistsException
    {
        List<Track> savedTrackList = new ArrayList<Track>();
        for (Track track:trackList) {
            Track track1 = trackService.saveTrack(track);
            savedTrackList.add(track1);
        }
        return new ResponseEntity<List<Track>>(savedTrackList, HttpStatus.CREATED);
    }

    //get track by name is not supported in mongodb
//    @GetMapping("trackByName")
//    public ResponseEntity<?> getTrackByName(@RequestParam String name) throws TrackNotFoundException
//    {
//        return new ResponseEntity<List<Track>>(trackService.getTracksByName(name), HttpStatus.OK);
//    }

    //logic for get all tracks method using get method
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        }
        catch (Exception exception) {

            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    //logic for update tracks method using put method
     @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track, @PathVariable int id) {
         ResponseEntity responseEntity;
         try {
             trackService.updateTrack(track, id);
             responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
         } catch (Exception exception1) {
             responseEntity = new ResponseEntity<String>(exception1.getMessage(), HttpStatus.CONFLICT);
         }
         return responseEntity;
     }

    //logic for delete tracks method using delete method
      @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            Track track = trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
    }
        return responseEntity;
    }


    //search tracks method is not supported in mongodb

//        @GetMapping("searchTracks")
//    public ResponseEntity<?> searchTracks(@RequestParam("searchString") String searchString)
//    {
//        ResponseEntity responseEntity;
//        try{
//            trackService.searchTracks(searchString);
//           responseEntity= new ResponseEntity<List<Track>>(trackService.searchTracks(searchString),HttpStatus.OK);
//        }catch (Exception exception){
//            responseEntity = new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;
//    }

     //logic for get last fm tracks method using get method
    @GetMapping("getLastFmTracks")
    public ResponseEntity<?> getLastFmTracks(@RequestParam String url) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = objectMapper.readValue(string, Result.class);
        List<Track> trackList = result.results.trackmatches.track;
        List<Track> savedTrackList = new ArrayList<>();
        for (Track track:trackList) {
            Track track1 = trackService.saveTrack(track);
            savedTrackList.add(track1);
        }
        return new ResponseEntity<>(savedTrackList,HttpStatus.OK);
    }
}
