package com.icesi.discografia.model;

import java.util.HashSet;
import java.util.Set;

public class Artist {

    private Long id;
    private String name;
    private String nationality;
    private Set<Track> tracks = new HashSet<>();

    public Artist() {}

    public Artist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    /** Sincroniza ambos lados de la relación many-to-many */
    public void addTrack(Track track) {
        this.tracks.add(track);
        if (!track.getArtists().contains(this)) {
            track.getArtists().add(this);
        }
    }

    public void removeTrack(Track track) {
        this.tracks.remove(track);
        track.getArtists().remove(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public Set<Track> getTracks() { return tracks; }
    public void setTracks(Set<Track> tracks) { this.tracks = tracks; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist other = (Artist) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }
}
