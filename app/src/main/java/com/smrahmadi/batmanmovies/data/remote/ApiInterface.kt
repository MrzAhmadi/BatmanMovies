package com.smrahmadi.batmanmovies.data.remote

import com.smrahmadi.batmanmovies.data.model.api.movie.MovieResponse
import com.smrahmadi.batmanmovies.data.model.api.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET
    fun search(
        @Query("apikey") apikey: String,
        @Query("s") s: String
    ): Call<SearchResponse>

    @GET
    fun movie(
        @Query("apikey") apikey: String,
        @Query("i") i: String
    ): Call<MovieResponse>
}