package za.ac.cput.movieapp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import za.ac.cput.movieapp.domain.Movie;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // You can add custom queries here, like searching by title
    List<Movie> findByTitleContainingIgnoreCase(String title);
    // This finds a movie only if BOTH the title and year match exactly
    Optional<Movie> findByTitleAndReleaseYear(String title, String releaseYear);
    Optional<Movie> findByImdbID(String imdbID);

}
