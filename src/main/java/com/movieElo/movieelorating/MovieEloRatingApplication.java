package com.movieElo.movieelorating;

import com.movieElo.movieelorating.repositories.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MovieEloRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieEloRatingApplication.class, args);
	}

}
