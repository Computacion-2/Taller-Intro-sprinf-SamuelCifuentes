package com.icesi.discografia.service;

import com.icesi.discografia.model.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> findAll();
    Artist create(Artist artist);
    Optional<Artist> findByNameWithTracks(String name);
    boolean deleteById(Long id);
}
