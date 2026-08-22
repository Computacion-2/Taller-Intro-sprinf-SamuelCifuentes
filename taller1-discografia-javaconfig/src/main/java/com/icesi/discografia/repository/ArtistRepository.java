package com.icesi.discografia.repository;

import com.icesi.discografia.model.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistRepository {
    List<Artist> findAll();
    Artist save(Artist artist);
    Optional<Artist> findById(Long id);
    Optional<Artist> findByName(String name);
    boolean deleteById(Long id);
}
