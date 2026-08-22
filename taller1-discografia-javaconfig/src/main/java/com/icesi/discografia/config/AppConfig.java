package com.icesi.discografia.config;

import com.icesi.discografia.init.DataInitializer;
import com.icesi.discografia.repository.ArtistRepository;
import com.icesi.discografia.repository.ArtistRepositoryImpl;
import com.icesi.discografia.repository.TrackRepository;
import com.icesi.discografia.repository.TrackRepositoryImpl;
import com.icesi.discografia.service.ArtistService;
import com.icesi.discografia.service.ArtistServiceImpl;
import com.icesi.discografia.service.TrackService;
import com.icesi.discografia.service.TrackServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Versión 3 — JavaConfig: cada bean se declara explícitamente con @Bean.
 * Sin @ComponentScan ni anotaciones de estereotipo en las clases de negocio.
 */
@Configuration
public class AppConfig {

    @Bean
    public ArtistRepository artistRepository() {
        return new ArtistRepositoryImpl();
    }

    @Bean
    public TrackRepository trackRepository() {
        return new TrackRepositoryImpl();
    }

    @Bean
    public ArtistService artistService() {
        return new ArtistServiceImpl(artistRepository());
    }

    @Bean
    public TrackService trackService() {
        return new TrackServiceImpl(trackRepository(), artistRepository());
    }

    /** initMethod="init" reemplaza @PostConstruct; DataInitializer no tiene anotaciones Spring */
    @Bean(initMethod = "init")
    public DataInitializer dataInitializer() {
        return new DataInitializer(artistService(), trackService());
    }
}
