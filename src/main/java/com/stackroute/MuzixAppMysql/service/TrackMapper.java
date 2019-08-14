package com.stackroute.MuzixAppMysql.service;

import com.stackroute.MuzixAppMysql.domain.Track;
import com.stackroute.MuzixAppMysql.domain.TrackDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TrackMapper {
  Track trackDTOTOTrack(TrackDTO trackDTO);
  TrackDTO trackTOTrackDTO(Track track);
  List<Track> trackDTOListToTrackList(List<TrackDTO> trackDTOList);

}
