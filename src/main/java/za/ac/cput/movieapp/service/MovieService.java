package za.ac.cput.movieapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.movieapp.domain.Movie;
import za.ac.cput.movieapp.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

//    public Movie saveMovie(Movie movie) {
//        return repository.save(movie);
//    }
public Movie saveMovie(Movie movie) {
    // Check if THIS specific version of the movie exists
    Optional<Movie> existing = repository.findByTitleAndReleaseYear(movie.getTitle(), movie.getReleaseYear());

    if (existing.isPresent()) {
        return existing.get(); // Return the one we already have
    }

    return repository.save(movie);
}

    // NEW METHOD: Fetches from OMDb and saves to MySQL
//    public Movie searchAndSaveFromOMDb(String title) {
//        String url = "http://www.omdbapi.com/?t=" + title + "&apikey=" + apiKey;
//        Movie omdbMovie = restTemplate.getForObject(url, Movie.class);
//
//        if (omdbMovie != null && omdbMovie.getTitle() != null) {
//            return repository.save(omdbMovie);
//        }
//        return null;
//    }
//    public Movie searchAndSaveFromOMDb(String title) {
//        // 1. Search for the basic info
//        String searchUrl = "http://www.omdbapi.com/?t=" + title + "&apikey=" + apiKey;
//        Movie omdbMovie = restTemplate.getForObject(searchUrl, Movie.class);
//
//        // This 't=' search actually returns full details including Ratings!
//        // If you are using 's=' in React, you are only getting partial data.
//        // Ensure your React 'handleAddToLibrary' sends the 'imdbID' to a
//        // backend endpoint that fetches by ID for full data.
//
//        if (omdbMovie != null && omdbMovie.getTitle() != null) {
//            return repository.save(omdbMovie);
//        }
//        return null;
//    }
    public Movie searchAndSaveFromOMDb(String title) {
        // Fetch from OMDb first to get the imdbID
        String url = "http://www.omdbapi.com/?t=" + title + "&apikey=" + apiKey;
        Movie omdbMovie = restTemplate.getForObject(url, Movie.class);

        if (omdbMovie != null) {
            // Check if we already have this specific imdbID in our DB
            Optional<Movie> existing = repository.findByImdbID(omdbMovie.getImdbID());
            if (existing.isPresent()) {
                return existing.get();
            }
            return repository.save(omdbMovie);
        }
        return null;
    }

    public Movie updateMovie(Long id, Movie movie) {
        if (repository.existsById(id)) {
            movie.setId(id);
            return repository.save(movie);
        }
        return null;
    }

    public void deleteMovie(Long id) {
        repository.deleteById(id);
    }
}