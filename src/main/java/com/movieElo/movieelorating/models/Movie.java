package com.movieElo.movieelorating.models;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double rating;

    @Column(name = "movie_url")
    private String movieUrl;

    public double getRating() {
        return rating;
    }

    public long getId() {
        return id;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public String getName() {
        return name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", movieUrl='" + movieUrl + '\'' +
                '}';
    }
}
