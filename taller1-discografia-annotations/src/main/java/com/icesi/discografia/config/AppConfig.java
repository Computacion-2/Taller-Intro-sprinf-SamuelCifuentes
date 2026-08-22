package com.icesi.discografia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Versión 2 — Annotations: detecta beans automáticamente por @ComponentScan.
 * Las clases usan @Repository, @Service, @Component y @Autowired en el constructor.
 */
@Configuration
@ComponentScan("com.icesi.discografia")
public class AppConfig {
}
