package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

//declared all the methods in this interface
public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException,TrackNotSavedException;
    public List<Track> getAllTracks();
    public Track updateTrack(Track track,int id) throws TrackAlreadyExistsException;
    public Track deleteTrack(int id)throws TrackNotFoundException;
    public List<Track> trackByName(String name);
}
