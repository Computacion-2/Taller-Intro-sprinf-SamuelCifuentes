package com.icesi.discografia.service;

import com.icesi.discografia.model.Artist;
import com.icesi.discografia.model.Track;
import com.icesi.discografia.repository.ArtistRepository;
import com.icesi.discografia.repository.TrackRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;

    public TrackServiceImpl(TrackRepository trackRepository, ArtistRepository artistRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public Track create(Track track, List<Long> artistIds) {
        if (artistIds == null || artistIds.isEmpty()) {
            throw new IllegalArgumentException("Se debe indicar al menos un artista");
        }
        List<Artist> artists = new ArrayList<>();
        for (Long artistId : artistIds) {
            Artist artist = artistRepository.findById(artistId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe artista con ID: " + artistId));
            artists.add(artist);
        }
        Track saved = trackRepository.save(track);
        // Usa el helper de Artist para mantener consistencia en ambos lados
        artists.forEach(a -> a.addTrack(saved));
        return saved;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Track> opt = trackRepository.findById(id);
        if (!opt.isPresent()) {
            return false;
        }
        Track track = opt.get();
        // Desvincula el track de todos sus artistas
        new ArrayList<Artist>(track.getArtists()).forEach(a -> a.getTracks().remove(track));
        track.getArtists().clear();
        return trackRepository.deleteById(id);
    }
}
