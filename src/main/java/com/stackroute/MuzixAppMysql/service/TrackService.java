package com.stackroute.MuzixAppMysql.service;

import com.stackroute.MuzixAppMysql.domain.Track;
import com.stackroute.MuzixAppMysql.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixAppMysql.exceptions.TrackNotFoundException;
import java.util.List;


//track service interface
//method declarations are done in this
public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

  //  public List<Track> getTracksByName(String name); //using mongodb so tracks by name methods are not supported

    public Track updateTrack(Track track, int id) throws TrackNotFoundException;

    public Track deleteTrack(int id) throws TrackNotFoundException;

  //  public List<Track> searchTracks(String searchString); //search is also not supported in mongodb
}
