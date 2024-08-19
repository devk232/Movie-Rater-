package com.movieElo.movieelorating.services;

import com.movieElo.movieelorating.dto.response.ApiMovieResponse;
import com.movieElo.movieelorating.models.Movie;
import com.movieElo.movieelorating.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> getLeaderboard() {
        return movieRepository.findAllByOrderByRatingDesc();
    }

    public ApiMovieResponse[] fetchMoviesFromAPI() {

        //TODO: move to constants and handle exceptions and network failures.
        HttpHeaders headers = new HttpHeaders();
        final String apiUrl = "https://imdb-top-100-movies.p.rapidapi.com/";
        headers.set("x-rapidapi-host", "imdb-top-100-movies.p.rapidapi.com");
        headers.set("x-rapidapi-key", "🙃");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ApiMovieResponse[]> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                ApiMovieResponse[].class
        );
        if (responseEntity.getBody() != null) {
            saveMoviesToLocalDB(responseEntity.getBody());
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    public void saveMoviesToLocalDB(ApiMovieResponse[] list) {
        //TODO: handle all DB failures.
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (ApiMovieResponse apiMovieResponse : list) {
            Movie movie = new Movie();
            //TODO: move this to constants
            movie.setRating(1400);
            movie.setMovieUrl(apiMovieResponse.movieUrl);
            movie.setName(apiMovieResponse.title);
            movieArrayList.add(movie);
        }
        //TODO: delete and update first according to rating.
        //TODO: Find what is more feasible, saving one by one, or in a batch.
        movieRepository.saveAll(movieArrayList);
    }

    public List<Movie> getTwoMovies() {
        Random random = new Random();

        int randomNumber1 = random.nextInt(100) + 1;
        int randomNumber2 = random.nextInt(100) + 1;
        //TODO: handle if they are null
        Movie firstMovie = movieRepository.findById((long)randomNumber1).orElse(null);
        Movie secondMovie = movieRepository.findById((long)randomNumber2).orElse(null);

        return List.of(firstMovie, secondMovie);
    }

    public void updateRating(Movie winMovie, Movie movieLost) {
        double ratingOfWinningMovie = winMovie.getRating();
        double ratingOfLosingMovie = movieLost.getRating();
        double expectationOfA = calculateExpectedScore(ratingOfWinningMovie, ratingOfLosingMovie);
        ratingOfWinningMovie += 100 * (1 - expectationOfA);
        ratingOfLosingMovie -= 100 * (1 - expectationOfA);
        //TODO: add a check if the movie input are correct.
        winMovie.setRating(ratingOfWinningMovie);
        movieLost.setRating(ratingOfLosingMovie);
        // update in DB.
        movieRepository.save(winMovie);
        movieRepository.save(movieLost);
    }

    public static double calculateExpectedScore(double Ra, double Rb) {
        //TODO: check if the algorithm is working fine.
        return 1 / (1 + Math.pow(10, (Rb - Ra) / 400.0));
    }
}


