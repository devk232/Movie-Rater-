package com.movieElo.movieelorating.controllers;

import com.movieElo.movieelorating.models.Movie;
import com.movieElo.movieelorating.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/get")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
