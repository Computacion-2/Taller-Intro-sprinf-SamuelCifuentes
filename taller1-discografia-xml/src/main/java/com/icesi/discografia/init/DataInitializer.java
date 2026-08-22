package com.icesi.discografia.init;

import com.icesi.discografia.model.Artist;
import com.icesi.discografia.model.Track;
import com.icesi.discografia.service.ArtistService;
import com.icesi.discografia.service.TrackService;
import java.util.Collections;

/**
 * Versión XML: sin anotaciones. Spring llama a init() vía init-method en applicationContext.xml.
 */
public class DataInitializer {

    private final ArtistService artistService;
    private final TrackService trackService;

    public DataInitializer(ArtistService artistService, TrackService trackService) {
        this.artistService = artistService;
        this.trackService = trackService;
    }

    public void init() {
        Artist bjork     = artistService.create(new Artist("Björk", "Islandesa"));
        Artist kendrick  = artistService.create(new Artist("Kendrick Lamar", "Estadounidense"));
        Artist rosalia   = artistService.create(new Artist("Rosalía", "Española"));
        Artist radiohead = artistService.create(new Artist("Radiohead", "Británica"));
        Artist lauryn    = artistService.create(new Artist("Lauryn Hill", "Estadounidense"));
        Artist caetano   = artistService.create(new Artist("Caetano Veloso", "Brasileña"));
        Artist nina      = artistService.create(new Artist("Nina Simone", "Estadounidense"));
        Artist tame      = artistService.create(new Artist("Tame Impala", "Australiana"));
        Artist badBunny  = artistService.create(new Artist("Bad Bunny", "Puertorriqueña"));
        Artist erykah    = artistService.create(new Artist("Erykah Badu", "Estadounidense"));

        seedTracks(bjork.getId(),
                new Track("Human Behaviour",      "Art Pop",          250, "Debut"),
                new Track("Venus as a Boy",        "Art Pop",          200, "Debut"),
                new Track("Army of Me",            "Industrial",       233, "Post"),
                new Track("Hyperballad",           "Electronic",       320, "Post"),
                new Track("Jóga",                  "Art Pop",          306, "Homogenic"));

        seedTracks(kendrick.getId(),
                new Track("Money Trees",           "Hip-Hop",          387, "good kid, m.A.A.d city"),
                new Track("Swimming Pools (Drank)","Hip-Hop",          314, "good kid, m.A.A.d city"),
                new Track("HUMBLE.",               "Hip-Hop",          177, "DAMN."),
                new Track("DNA.",                  "Hip-Hop",          185, "DAMN."),
                new Track("Alright",               "Hip-Hop Soul",     219, "To Pimp a Butterfly"));

        seedTracks(rosalia.getId(),
                new Track("Malamente",             "Flamenco",         214, "Los Ángeles"),
                new Track("Di Mi Nombre",          "Flamenco Pop",     232, "El Mal Querer"),
                new Track("Con Altura",            "Reggaeton",        215, "Con Altura"),
                new Track("Bizcochito",            "Urban Latino",     125, "MOTOMAMI"),
                new Track("Saoko",                 "Electronic Pop",   175, "MOTOMAMI"));

        seedTracks(radiohead.getId(),
                new Track("Creep",                        "Alternative Rock", 239, "Pablo Honey"),
                new Track("Karma Police",                 "Alternative Rock", 264, "OK Computer"),
                new Track("Paranoid Android",             "Art Rock",         386, "OK Computer"),
                new Track("Exit Music (For a Film)",      "Alternative Rock", 244, "OK Computer"),
                new Track("Idioteque",                    "Electronic",       308, "Kid A"));

        seedTracks(lauryn.getId(),
                new Track("Killing Me Softly",            "R&B",              299, "The Score"),
                new Track("Doo Wop (That Thing)",         "Hip-Hop Soul",     239, "The Miseducation of Lauryn Hill"),
                new Track("Ex-Factor",                    "Neo Soul",         330, "The Miseducation of Lauryn Hill"),
                new Track("Everything Is Everything",     "Hip-Hop",          273, "The Miseducation of Lauryn Hill"),
                new Track("To Zion",                      "Gospel Soul",      389, "The Miseducation of Lauryn Hill"));

        seedTracks(caetano.getId(),
                new Track("Sozinho",                      "MPB",              243, "Livro"),
                new Track("You Don't Know Me",            "Bossa Nova",       210, "A Foreign Sound"),
                new Track("Cucurrucucú Paloma",           "Folk",             342, "Fina Estampa"),
                new Track("O Leãozinho",                  "MPB",              195, "Bicho"),
                new Track("Sampa",                        "MPB",              267, "Cinema Transcendental"));

        seedTracks(nina.getId(),
                new Track("Feeling Good",                 "Jazz",             170, "I Put a Spell on You"),
                new Track("I Put a Spell on You",         "Blues",            207, "I Put a Spell on You"),
                new Track("My Baby Just Cares for Me",    "Jazz",             213, "Little Girl Blue"),
                new Track("Sinnerman",                    "Gospel Jazz",      599, "Pastel Blues"),
                new Track("Ne Me Quitte Pas",             "French Chanson",   243, "I Put a Spell on You"));

        seedTracks(tame.getId(),
                new Track("Let It Happen",                "Psychedelic Pop",  467, "Currents"),
                new Track("The Less I Know the Better",   "Psychedelic Rock", 216, "Currents"),
                new Track("Borderline",                   "Synth Pop",        223, "The Slow Rush"),
                new Track("Elephant",                     "Psychedelic Rock", 202, "Lonerism"),
                new Track("Eventually",                   "Psychedelic Pop",  310, "Currents"));

        seedTracks(badBunny.getId(),
                new Track("Mía",                          "Latin Trap",       242, "X 100PRE"),
                new Track("DÁKITI",                       "Reggaeton",        220, "El Último Tour Del Mundo"),
                new Track("Yonaguni",                     "R&B Trap",         227, "El Último Tour Del Mundo"),
                new Track("Me Porto Bonito",              "Dembow",           175, "Un Verano Sin Ti"),
                new Track("Tití Me Preguntó",             "Dembow",           213, "Un Verano Sin Ti"));

        seedTracks(erykah.getId(),
                new Track("On & On",                      "Neo Soul",         296, "Baduizm"),
                new Track("Next Lifetime",                "Neo Soul",         276, "Baduizm"),
                new Track("Tyrone",                       "Neo Soul",         291, "Baduizm Live"),
                new Track("Bag Lady",                     "Neo Soul",         311, "Mama's Gun"),
                new Track("Window Seat",                  "Neo Soul",         296, "New Amerykah Part Two"));
    }

    private void seedTracks(Long artistId, Track... tracks) {
        for (Track t : tracks) {
            trackService.create(t, Collections.singletonList(artistId));
        }
    }
}
