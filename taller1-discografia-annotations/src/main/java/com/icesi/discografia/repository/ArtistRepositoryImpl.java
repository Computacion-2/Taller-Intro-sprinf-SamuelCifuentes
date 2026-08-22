package com.icesi.discografia.repository;

import com.icesi.discografia.model.Artist;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository {

    private final Map<Long, Artist> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public List<Artist> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Artist save(Artist artist) {
        if (artist.getId() == null) {
            artist.setId(idGen.getAndIncrement());
        }
        store.put(artist.getId(), artist);
        return artist;
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Artist> findByName(String name) {
        return store.values().stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
