package com.icesi.discografia.repository;

import com.icesi.discografia.model.Track;
import java.util.List;
import java.util.Optional;

public interface TrackRepository {
    List<Track> findAll();
    Track save(Track track);
    Optional<Track> findById(Long id);
    boolean deleteById(Long id);
}
