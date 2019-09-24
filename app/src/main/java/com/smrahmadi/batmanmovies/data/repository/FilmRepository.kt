package com.smrahmadi.batmanmovies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smrahmadi.batmanmovies.App
import com.smrahmadi.batmanmovies.data.model.api.movie.MovieResponse
import com.smrahmadi.batmanmovies.data.model.api.search.SearchResponse
import com.smrahmadi.batmanmovies.data.remote.DataWrapper
import com.smrahmadi.batmanmovies.data.remote.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmRepository {

    companion object {
        private const val TAG = "FilmRepository"

        private const val apikey = "3e974fca"
        private const val s = "batman"
    }

    fun search(): LiveData<DataWrapper<SearchResponse>> {
        val liveData: MutableLiveData<DataWrapper<SearchResponse>> = MutableLiveData()

        App.apiInterface.search(apikey, s)
            .enqueue(object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.v(
                        TAG,
                        "search onFailure: ${t.message}"
                    )
                    liveData.postValue(
                        DataWrapper(
                            t,
                            null
                        )
                    )
                }

                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.v(
                            TAG,
                            "search isSuccessful: ${response.body().toString()}"
                        )
                        liveData.postValue(
                            DataWrapper(
                                null,
                                response.body()
                            )
                        )
                    } else {
                        val data = ErrorUtils.searchResponseHandler(response)
                        Log.v(
                            TAG,
                            "search !isSuccessful: ${data?.error}"
                        )
                        liveData.postValue(
                            DataWrapper(
                                null,
                                data
                            )
                        )
                    }
                }
            })

        return liveData
    }

    fun movie(i: String): LiveData<DataWrapper<MovieResponse>> {
        val liveData: MutableLiveData<DataWrapper<MovieResponse>> = MutableLiveData()

        App.apiInterface.movie(apikey, i)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.v(
                        TAG,
                        "movie onFailure: ${t.message}"
                    )
                    liveData.postValue(
                        DataWrapper(
                            t,
                            null
                        )
                    )
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.v(
                            TAG,
                            "search isSuccessful: ${response.body().toString()}"
                        )
                        liveData.postValue(
                            DataWrapper(
                                null,
                                response.body()
                            )
                        )
                    } else {
                        val data = ErrorUtils.movieResponseHandler(response)
                        Log.v(
                            TAG,
                            "search !isSuccessful: ${data?.error}"
                        )
                        liveData.postValue(
                            DataWrapper(
                                null,
                                data
                            )
                        )
                    }
                }
            })

        return liveData
    }


}