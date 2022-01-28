package com.sudip.nitvmovie;

public class NitvMovieModel {

    private String adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private int id;
    private int vote_count;
    private float vote_average;

    public NitvMovieModel(String adult, String backdrop_path, String original_language, String original_title, String overview, String poster_path, String release_date, int id, int vote_count, float vote_average) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
    }

    public NitvMovieModel(){

    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

}
