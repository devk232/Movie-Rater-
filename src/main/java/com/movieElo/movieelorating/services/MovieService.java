package com.movieElo.movieelorating.services;

import com.movieElo.movieelorating.models.Movie;
import com.movieElo.movieelorating.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        List<Movie> list =  movieRepository.findAll();
        return list;
    }
    public MovieService () {}
}
