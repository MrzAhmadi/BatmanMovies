package com.smrahmadi.batmanmovies.data.remote

import com.smrahmadi.batmanmovies.data.model.api.movie.MovieResponse
import com.smrahmadi.batmanmovies.data.model.api.search.SearchResponse
import retrofit2.Response
import java.io.IOException

class ErrorUtils {

    companion object {
        fun searchResponseHandler(response: Response<*>): SearchResponse? {
            val converter = ApiClient.retrofit!!
                .responseBodyConverter<SearchResponse>(SearchResponse::class.java, arrayOfNulls(0))
            val error: SearchResponse?
            try {
                error = converter.convert(response.errorBody()!!)
            } catch (e: IOException) {
                return null
            }
            return error
        }

        fun movieResponseHandler(response: Response<*>): MovieResponse? {
            val converter = ApiClient.retrofit!!
                .responseBodyConverter<MovieResponse>(MovieResponse::class.java, arrayOfNulls(0))
            val error: MovieResponse?
            try {
                error = converter.convert(response.errorBody()!!)
            } catch (e: IOException) {
                return null
            }
            return error
        }
    }
}