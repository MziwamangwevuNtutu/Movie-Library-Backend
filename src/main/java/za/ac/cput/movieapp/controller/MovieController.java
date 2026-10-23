package za.ac.cput.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.movieapp.domain.Movie;
import za.ac.cput.movieapp.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    // New Endpoint: Fetch from OMDb and Save to DB
    // Usage: GET http://localhost:8080/movies/api/movies/search?title=Inception
    @GetMapping("/search")
    public Movie search(@RequestParam String title) {
        return service.searchAndSaveFromOMDb(title);
    }

    @GetMapping
    public List<Movie> listAll() {
        return service.getAllMovies();
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {

        return service.saveMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        return service.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        service.deleteMovie(id);
    }
}
