package com.devesh.mvvmbasics.data.api

import com.devesh.mvvmbasics.data.api.Model.MovieReviewModel
import com.devesh.mvvmbasics.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movies/v2/reviews/{type}.json?api-key=$API_KEY")
    suspend fun getMovieReviews(
        @Path("type") type: String,
        @Query("offset") offset: Int,
        @Query("order") order: String,
    ): MovieReviewModel
}