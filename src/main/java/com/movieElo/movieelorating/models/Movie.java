package com.movieElo.movieelorating.models;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int rating;

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }
}
