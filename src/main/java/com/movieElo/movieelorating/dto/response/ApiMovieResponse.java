package com.movieElo.movieelorating.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO: how setters/getters are used internally?
public class ApiMovieResponse {
    @JsonProperty("title")
    public String title;

    @JsonProperty("big_image")
    public String movieUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String iconUrl) {
        this.movieUrl = iconUrl;
    }

}
