package com.icesi.discografia.service;

import com.icesi.discografia.model.Artist;
import com.icesi.discografia.model.Track;
import com.icesi.discografia.repository.ArtistRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Optional<Artist> findByNameWithTracks(String name) {
        return artistRepository.findByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Artist> opt = artistRepository.findById(id);
        if (!opt.isPresent()) {
            return false;
        }
        Artist artist = opt.get();
        new ArrayList<Track>(artist.getTracks()).forEach(t -> t.getArtists().remove(artist));
        artist.getTracks().clear();
        return artistRepository.deleteById(id);
    }
}
