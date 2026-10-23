package za.ac.cput.movieapp.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import za.ac.cput.movieapp.domain.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieFactorryTest {

    @Test
    void createMovie() {
        Movie movie = MovieFactorry.createMovie(
                "Inception",
                "Christopher Nolan",
                "2010",
                "Sci-Fi",
                "8.8",
                "http://example.com/poster.jpg"
        );

        assertNotNull(movie);
        assertEquals("Inception", movie.getTitle());
        assertEquals("Christopher Nolan", movie.getDirector());
        assertEquals("2010", movie.getReleaseYear());
        assertEquals("Sci-Fi", movie.getGenre());
        assertEquals("8.8", movie.getUserRating());
        assertEquals("http://example.com/poster.jpg", movie.getPosterUrl());
    }
}