package com.example.filmsandserials.model.services

import android.util.Log
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.data_connections.FilmResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RatingService {
    @GET("3/{data_type}/{top_type}?api_key=c30931d27347b1937173c48adc4aa322")
    fun getTopFilmService(
        @Path("data_type") type: String,
        @Path("top_type") topType: String,
        @Query("language") language: String
    ): Call<FilmResponse>
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

    private val ratingResponseApiService = retrofit.create(RatingService::class.java)

    suspend fun getTopFilmService(type: String, top_type: String, lang: String): List<Film>? {
        Log.v("App", "getFilmRatingService")
        return withContext(Dispatchers.Default) {
            ratingResponseApiService.getTopFilmService(type, top_type, lang).execute()
                .body()?.results?.map { result ->
                Film(
                    result.id,
                    result.title,
                    result.original_title,
                    result.overview,
                    result.poster_path,
                    result.backdrop_path,
                    result.popularity,
                    result.vote_average
                )
            }
        }
    }
}