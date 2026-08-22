package com.icesi.discografia.service;

import com.icesi.discografia.model.Track;
import java.util.List;

public interface TrackService {
    List<Track> findAll();
    Track create(Track track, List<Long> artistIds);
    boolean deleteById(Long id);
}
