package za.ac.cput.movieapp.factory;

import za.ac.cput.movieapp.domain.Movie;
public class MovieFactorry { // Note: You might want to rename to MovieFactory
    public static Movie createMovie(String title, String director, String releaseYear, String genre, String userRating, String posterUrl) {
        return new Movie.MovieBuilder()
                .setTitle(title)
                .setDirector(director)
                .setReleaseYear(releaseYear)
                .setGenre(genre)
                .setUserRating(userRating)
                .setPosterUrl(posterUrl)
                .build();
    }
}