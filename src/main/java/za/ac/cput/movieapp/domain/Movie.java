package za.ac.cput.movieapp.domain;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("imdbID")
    @Column(unique = true) // This stops MySQL from ever allowing the same ID twice
    private String imdbID;

    @JsonProperty("Title") // Maps OMDb "Title" to your title
    private String title;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Year")
    private String releaseYear; // Changed to String because OMDb years can sometimes be ranges (e.g., "2010–2015")

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("imdbRating")
    private String userRating; // OMDb returns rating as a String

    @JsonProperty("Poster")
    private String posterUrl;
    public Movie() {
    }

    public Movie(MovieBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.director = builder.director;
        this.releaseYear = builder.releaseYear;
        this.genre = builder.genre;
        this.userRating = builder.userRating;
        this.posterUrl = builder.posterUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getImdbID() {
        return imdbID;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                "ImdbID='" + imdbID + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", userRating=" + userRating +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }

//    public void setTitle(String title) {
//    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public static class MovieBuilder {
        private Long id;
        private String imdbID;
        private String title;
        private String director;
        private String releaseYear;
        private String genre;
        private String userRating;
        private String posterUrl;


         public MovieBuilder() {

          }
          public MovieBuilder id(Long id) {
                this.id = id;
                return this;
            }

            public MovieBuilder setTitle(String title) {
                this.title = title;
                return this;
            }

            public MovieBuilder setDirector(String director) {
                this.director = director;
                return this;
            }

            public MovieBuilder setReleaseYear(String releaseYear) {
                this.releaseYear = releaseYear;
                return this;
            }

            public MovieBuilder setGenre(String genre) {
                this.genre = genre;
                return this;
            }

            public MovieBuilder setUserRating(String userRating) {
                this.userRating = userRating;
                return this;
            }

            public MovieBuilder setPosterUrl(String posterUrl) {
                this.posterUrl = posterUrl;
                return this;
          }
        public MovieBuilder set(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }
          public MovieBuilder imdbID(String imdbID) {
                this.imdbID = imdbID;
                return this;
          }

          public MovieBuilder Copy(Movie movie) {
                this.id = movie.id;
                this.imdbID = movie.imdbID;
                this.title = movie.title;
                this.director = movie.director;
                this.releaseYear = movie.releaseYear;
                this.genre = movie.genre;
                this.userRating = movie.userRating;
                this.posterUrl = movie.posterUrl;
                return this;

          }
                public Movie build() {
                    return new Movie(this);
                }
    }
}
