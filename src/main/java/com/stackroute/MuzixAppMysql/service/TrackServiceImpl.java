package com.stackroute.MuzixAppMysql.service;

import com.stackroute.MuzixAppMysql.domain.Track;
import com.stackroute.MuzixAppMysql.domain.TrackDTO;
import com.stackroute.MuzixAppMysql.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixAppMysql.exceptions.TrackNotFoundException;
import com.stackroute.MuzixAppMysql.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//service annotation is used to make this class as a service class
@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    TrackMapper trackMapper;

      //autowired annotation is used
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository , TrackMapper trackMapper)
    {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
    }

        //implementation of save track method
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(trackRepository.existsById(track.getId()))
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        TrackDTO trackDTO=trackMapper.trackTOTrackDTO(track);
        TrackDTO savedTrackDto = trackRepository.save(trackDTO);
        Track savedTrack = trackMapper.trackDTOTOTrack(savedTrackDto);

        if(savedTrack == null)
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }


    //implementation of get all tracks method
    @Override
    @Cacheable("tracks")
    public List<Track> getAllTracks() {
         List<TrackDTO> trackDTOList = trackRepository.findAll();
         List<Track> trackList=trackMapper.trackDTOListToTrackList(trackDTOList);
         return trackList;
    }

    //track by name is not supported in mongodb

//    @Override
//    public List<Track> getTracksByName(String name) {
//
//        return trackRepository.getTrackByName(name);
//
//    }

    //implementation of update tracks method
    public Track updateTrack(Track track, int id) throws TrackNotFoundException
    {
      Optional<TrackDTO> trackDTOOptional=trackRepository.findById(id);

      if(!trackDTOOptional.isPresent())
      {
        throw new TrackNotFoundException("Track Not Found");
      }

        TrackDTO trackDTO1 = trackMapper.trackTOTrackDTO(track);

        trackDTO1.setId(id);

        TrackDTO savedTrackDto = trackRepository.save(trackDTO1);

        return trackMapper.trackDTOTOTrack(savedTrackDto);

    }

        //implementation of delete track method
        public Track deleteTrack(int id) throws TrackNotFoundException
        {
          Optional<TrackDTO> trackDTO = trackRepository.findById(id);

          if(!trackDTO.isPresent())
          {
            throw new TrackNotFoundException("Track Not Found");
          }
          trackRepository.delete(trackDTO.get());

          return trackMapper.trackDTOTOTrack(trackDTO.get());

        }
    //serach tracks is not supported in mongodb

//    @Override
//    public List<Track> searchTracks(String searchString) {
//
//        return trackRepository.searchTracks(searchString);
//    }
}
