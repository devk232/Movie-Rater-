package com.movieElo.movieelorating.dto.request;

import com.movieElo.movieelorating.models.Movie;

public class CompareRequest {

    public Movie movie1;

    public Movie movie2;

    public long wonId;

    public CompareRequest(Movie movie1, Movie movie2, long wonId) {
        this.movie1 = movie1;
        this.movie2 = movie2;
        this.wonId = wonId;
    }

    @Override
    public String toString() {
        return "CompareRequest{" +
                "movie1=" + movie1.toString() +
                ", movie2=" + movie2.toString() +
                ", wonId=" + wonId +
                '}';
    }
}
