package com.stackroute.service;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.Track;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//used to identify the class as service class
@Service
@PropertySource("application.properties")
public class TrackServiceImpl implements TrackService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    @Value("${track.1.firstName}")
    String firstName1;
    @Value("${track.1.lastName}")
    String lastName1;
    @Value("${track.1.age}")
    int age1;
    @Value("${track.2.firstName}")
    String firstName2;
    @Value("${track.2.lastName}")
    String lastName2;
    @Value("${track.2.age}")
    int age2;

   private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;

    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("track Already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("track already exists");
        }

        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }


    public Track updateTrack(Track track, int id) throws TrackAlreadyExistsException {
        Optional<Track> trackOptional = trackRepository.findById(id);

        if (!trackOptional.isPresent())
            throw new TrackAlreadyExistsException("track id not found");


        track.setId(id);

        return trackRepository.save(track);

    }

    @Override
    public Track deleteTrack( int id) throws TrackNotFoundException {
        Optional<Track> trackOptional = trackRepository.findById(id);

        if (!trackOptional.isPresent())
            throw new TrackNotFoundException("track id not found");

        trackRepository.delete(trackOptional.get());
        return trackOptional.get();
    }

    @Override
    public List<Track> trackByName(String name)
    {
        return trackRepository.trackByName(name);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        trackRepository.save(new Track(1,"firstName1","lastName1",23));
        trackRepository.save(new Track(2,"firstName2","lastName2",32));

    }
}

