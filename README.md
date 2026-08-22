# Taller 1 — Sistema de Gestión de Discografía

Sistema web para gestionar artistas musicales y sus tracks, implementado con **Java EE (Servlet + JSP)** y **Spring Framework** (sin Spring Boot) para inyección de dependencias.

Incluye **tres proyectos Maven independientes** con la misma funcionalidad pero diferente estrategia de DI de Spring.

---

## Proyectos

| Proyecto | Estrategia DI | Archivo clave |
|---|---|---|
| `taller1-discografia-xml` | XML puro con `<bean>` explícitos | `WEB-INF/applicationContext.xml` |
| `taller1-discografia-annotations` | `@ComponentScan` + `@Service`/`@Repository`/`@Autowired` | `config/AppConfig.java` (solo `@Configuration @ComponentScan`) |
| `taller1-discografia-javaconfig` | `@Configuration` con métodos `@Bean` explícitos | `config/AppConfig.java` (declara cada bean con `new`) |

---

## Requisitos

- JDK 11+
- Maven 3.6+
- Apache Tomcat 9.x

---

## Compilar

Desde la carpeta de cada proyecto:

```bash
cd taller1-discografia-xml
mvn clean package

cd ../taller1-discografia-annotations
mvn clean package

cd ../taller1-discografia-javaconfig
mvn clean package
```

Cada build genera un `.war` dentro de `target/`.

---

## Desplegar en Tomcat

1. Copiar el `.war` de `target/` a `<TOMCAT_HOME>/webapps/`.
2. Iniciar (o reiniciar) Tomcat.
3. Acceder desde el navegador:

| Versión | URL |
|---|---|
| XML | `http://localhost:8080/discografia-xml/` |
| Annotations | `http://localhost:8080/discografia-annotations/` |
| JavaConfig | `http://localhost:8080/discografia-javaconfig/` |

Al iniciar cualquiera de las tres apps ya estarán cargados **10 artistas y 50 tracks** (5 por artista).

---

## Diferencia clave de DI entre versiones

### Versión 1 — XML (`taller1-discografia-xml`)

- **Archivo clave:** `src/main/webapp/WEB-INF/applicationContext.xml`
- Beans declarados como `<bean id="..." class="..."/>` en XML.
- Dependencias inyectadas con `<constructor-arg ref="..."/>`.
- **Las clases de repositorio y servicio no tienen ninguna anotación Spring.**
- `DataInitializer` se inicializa con `init-method="init"` en el XML.
- `web.xml` carga el contexto vía `ContextLoaderListener` con `contextConfigLocation=/WEB-INF/applicationContext.xml`.

### Versión 2 — Annotations (`taller1-discografia-annotations`)

- **Archivo clave:** `src/main/java/com/icesi/discografia/config/AppConfig.java`
- `@Configuration @ComponentScan("com.icesi.discografia")` detecta todos los beans automáticamente.
- Las clases usan `@Repository`, `@Service`, `@Component` y `@Autowired` en el constructor.
- `DataInitializer` usa `@PostConstruct` para la inicialización automática.
- `web.xml` usa `AnnotationConfigWebApplicationContext` apuntando a `AppConfig`.

### Versión 3 — JavaConfig (`taller1-discografia-javaconfig`)

- **Archivo clave:** `src/main/java/com/icesi/discografia/config/AppConfig.java`
- `@Configuration` sin `@ComponentScan`. Cada bean se declara explícitamente con un método `@Bean`.
- **Las clases de negocio (repos, servicios, DataInitializer) no tienen anotaciones Spring.**
- `DataInitializer` se inicializa con `@Bean(initMethod = "init")` en `AppConfig`.
- `web.xml` usa `AnnotationConfigWebApplicationContext` apuntando a `AppConfig`.

---

## Flujos a probar (los 7 requeridos)

### 1. Listar artistas
- URL: `/artists`
- Se muestran los 10 artistas precargados con ID, nombre, nacionalidad y cantidad de tracks.

### 2. Crear artista
- URL: `/artists/create`
- Completar nombre y nacionalidad → "Crear Artista".
- Se redirige al listado con mensaje de éxito.

### 3. Buscar artista por nombre
- URL: `/artists/search`
- Ingresar un nombre exacto (ej: `Björk`, `Bad Bunny`, `Radiohead`) → "Buscar".
- Muestra todos los datos del artista y la tabla de sus 5 tracks.
- Si no existe, muestra mensaje de "no encontrado".

### 4. Eliminar artista
- URL: `/artists/delete`
- Ingresar el ID de un artista → "Eliminar".
- El artista se elimina y se desvincula de todos sus tracks (consistencia many-to-many).

### 5. Listar tracks
- URL: `/tracks`
- Tabla con los 50 tracks: título, género, duración, álbum y **nombres de todos los artistas asociados**.

### 6. Crear track con varios artistas
- URL: `/tracks/create`
- Completar campos y seleccionar uno o varios artistas con Ctrl+clic → "Crear Track".
- El nuevo track queda vinculado a los artistas seleccionados en ambos lados.

### 7. Eliminar track
- URL: `/tracks/delete`
- Ingresar el ID de un track → "Eliminar".
- El track se elimina y se desvincula de todos sus artistas.

---

## Estructura interna (igual en los 3 proyectos)

```
src/main/java/com/icesi/discografia/
├── model/          Artist.java, Track.java
├── repository/     ArtistRepository (interfaz + impl), TrackRepository (interfaz + impl)
├── service/        ArtistService (interfaz + impl), TrackService (interfaz + impl)
├── init/           DataInitializer.java
├── servlet/        7 servlets (ArtistList, ArtistCreate, ArtistSearch, ArtistDelete,
│                               TrackList, TrackCreate, TrackDelete)
└── config/         AppConfig.java  ← solo en versiones 2 y 3

src/main/webapp/
├── index.jsp
└── WEB-INF/
    ├── web.xml
    ├── applicationContext.xml   ← solo versión 1
    └── views/                   8 JSP de vistas
```
