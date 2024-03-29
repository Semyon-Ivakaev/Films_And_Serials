package com.example.filmsandserials.model.services

import android.util.Log
import com.example.filmsandserials.data.Actor
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.data_connections.ActorResponse
import com.example.filmsandserials.model.data_connections.FilmResponse
import com.example.filmsandserials.model.data_connections.SerialResponse
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

    @GET("3/{data_type}/{top_type}?api_key=c30931d27347b1937173c48adc4aa322")
    fun getTopSerialService(
        @Path("data_type") type: String,
        @Path("top_type") topType: String,
        @Query("language") language: String
    ): Call<SerialResponse>

    @GET("3/search/movie?api_key=c30931d27347b1937173c48adc4aa322&page=1&include_adult=false")
    fun searchMovies(
        @Query("query") request: String?,
        @Query("language") language: String
    ): Call<FilmResponse>

    @GET("3/search/tv?api_key=c30931d27347b1937173c48adc4aa322&page=1&include_adult=false")
    fun searchSerials(
        @Query("query") request: String?,
        @Query("language") language: String
    ): Call<SerialResponse>

    @GET("/3/person/{id}?api_key=c30931d27347b1937173c48adc4aa322&language=en-US")
    fun getActorDetailInfo(
        @Path("id") id: Int
    ): Call<ActorResponse>
}

object ApiServiceApiImpl{
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

    suspend fun getTopService(type: String, top_type: String, lang: String): List<Film>? {
        Log.v("App", "getFilmRatingService with param: $type")
        when (type) {
            "movie" -> return withContext(Dispatchers.Default) {
                ratingResponseApiService.getTopFilmService(type, top_type, lang).execute()
                    .body()?.results?.map { result ->
                        Film(
                            result.id,
                            result.title,
                            result.original_title,
                            result.overview,
                            result.poster_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                            result.backdrop_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                            result.popularity,
                            result.vote_average,
                            result.genre_ids
                        )
                    }
            }
            else -> return withContext(Dispatchers.Default) {
                ratingResponseApiService.getTopSerialService(type, top_type, lang).execute()
                    .body()?.results?.map { result ->
                        Film(
                            result.id,
                            result.name,
                            result.original_name,
                            result.overview,
                            result.poster_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                            result.backdrop_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                            result.popularity,
                            result.vote_average,
                            result.genre_ids
                        )
                    }
            }
        }
    }

    suspend fun searchMovies(searchRequest: String?, lang: String): List<Film>? {
        return withContext(Dispatchers.Default) {
            ratingResponseApiService.searchMovies(searchRequest, lang).execute()
                .body()?.results?.map {
                    result ->
                    Film(
                        result.id,
                        result.title,
                        result.original_title,
                        result.overview,
                        result.poster_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                        result.backdrop_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                        result.popularity,
                        result.vote_average,
                        result.genre_ids
                    )
                }
        }
    }

    suspend fun searchSerials(searchRequest: String?, lang: String): List<Film>? {
        return withContext(Dispatchers.Default) {
            ratingResponseApiService.searchSerials(searchRequest, lang).execute()
                .body()?.results?.map {
                        result ->
                    Film(
                        result.id,
                        result.name,
                        result.original_name,
                        result.overview,
                        result.poster_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                        result.backdrop_path ?: "/1nJ6V9ryJhfIho0f4z2nmcQLKIf.jpg",
                        result.popularity,
                        result.vote_average,
                        result.genre_ids
                    )
                }
        }
    }

    suspend fun getActorDetailInfo(list: List<Int>): List<Actor> {
        val result: ArrayList<Actor> = ArrayList()
        Log.v("AppVerbose", list.toString())
        for (el in list.indices) {
            withContext(Dispatchers.Default) {
                ratingResponseApiService.getActorDetailInfo(list[el]).execute().body()?.apply {
                    Log.v("AppVerbose", "$id + $name")
                    result.add(Actor(id, name, profile_path, biography))
                }
            }
        }

        Log.v("AppVerbose", "RESULT: $result")
        return result
    }
}