package com.icesi.discografia.model;

import java.util.HashSet;
import java.util.Set;

public class Track {

    private Long id;
    private String title;
    private String genre;
    private int duration;
    private String albumTitle;
    private Set<Artist> artists = new HashSet<>();

    public Track() {}

    public Track(String title, String genre, int duration, String albumTitle) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.albumTitle = albumTitle;
    }

    /** Sincroniza ambos lados de la relación many-to-many */
    public void addArtist(Artist artist) {
        this.artists.add(artist);
        if (!artist.getTracks().contains(this)) {
            artist.getTracks().add(this);
        }
    }

    public void removeArtist(Artist artist) {
        this.artists.remove(artist);
        artist.getTracks().remove(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getAlbumTitle() { return albumTitle; }
    public void setAlbumTitle(String albumTitle) { this.albumTitle = albumTitle; }
    public Set<Artist> getArtists() { return artists; }
    public void setArtists(Set<Artist> artists) { this.artists = artists; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track other = (Track) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }
}
