package com.movieElo.movieelorating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MovieEloRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieEloRatingApplication.class, args);
	}

}
