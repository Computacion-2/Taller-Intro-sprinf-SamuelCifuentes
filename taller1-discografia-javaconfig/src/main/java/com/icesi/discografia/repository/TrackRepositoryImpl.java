package com.icesi.discografia.repository;

import com.icesi.discografia.model.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TrackRepositoryImpl implements TrackRepository {

    private final Map<Long, Track> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public List<Track> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Track save(Track track) {
        if (track.getId() == null) {
            track.setId(idGen.getAndIncrement());
        }
        store.put(track.getId(), track);
        return track;
    }

    @Override
    public Optional<Track> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
