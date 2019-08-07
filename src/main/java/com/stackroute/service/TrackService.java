package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.exceptions.TrackNotSavedException;

import java.util.List;

//track service interface
//method declarations are done in this
public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException, TrackNotSavedException;

    public List<Track> getAllTracks();

    public List<Track> getTracksByName(String name);

    public Track updateTrack(Track track, int id) throws TrackNotFoundException;

    public Track deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> searchTracks(String searchString);
}
