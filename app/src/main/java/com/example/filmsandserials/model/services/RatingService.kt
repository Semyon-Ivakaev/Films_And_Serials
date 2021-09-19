package com.example.filmsandserials.model.services

import com.example.filmsandserials.model.data_connections.RatingResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RatingService {
    @GET("3/{data_type}/top_rated?api_key=c30931d27347b1937173c48adc4aa322")
    fun getFilmRatingService(
        @Path("data_type") type: String,
        @Query("language") language: String
    ): Call<RatingResponse>
}

object RatingServiceApiImpl{
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val ratingResponseApiService = retrofit.create(RatingService::class.java)


}