package com.stackroute.repository;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//using repository annotation
@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
    @Query("select track from Track track where firstName=?1")
    List<Track> trackByName(String name);  //Abstract method
}
