package com.movieElo.movieelorating.controllers;

import com.movieElo.movieelorating.dto.request.CompareRequest;
import com.movieElo.movieelorating.dto.response.ApiMovieResponse;
import com.movieElo.movieelorating.models.Movie;
import com.movieElo.movieelorating.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/compare")
    public List<Movie> getTwoMovies() {
        return movieService.getTwoMovies();
    }

    //TODO: protect this endpoint so that only admin can call this.
    // learn to write airflow DAGs to update DB accordingly.
    @GetMapping("/fetch-from-api")
    public ApiMovieResponse[] fetchMoviesFromAPI() {
        return movieService.fetchMoviesFromAPI();
    }

    @PostMapping("/update-rating")
    public String updateMovieRating(@RequestBody CompareRequest request) {
        //TODO: handle case when objects are null or missing.
        System.out.println(request.toString());
        System.out.println(request.movie1.toString());
        System.out.println(request.movie2.toString());
        System.out.println(request.wonId);
        if (request.wonId == request.movie1.getId()) {
            movieService.updateRating(request.movie1, request.movie2);
        } else if (request.wonId == request.movie2.getId()) {
            movieService.updateRating(request.movie2, request.movie1);
        } else {
           return  "Invalid wonId";
        }
        return "ok";
    }

    @GetMapping("/leaderboard")
    public List<Movie> showRatedMovies() {
        return movieService.getLeaderboard();
    }
}
