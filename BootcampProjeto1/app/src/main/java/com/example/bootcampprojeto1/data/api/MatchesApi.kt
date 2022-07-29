package com.example.bootcampprojeto1.data.api

import com.example.bootcampprojeto1.domain.Partida
import retrofit2.Call
import retrofit2.http.GET

interface MatchesApi {

    @GET(END_POINT)
    fun getMatches(): Call<List<Partida>>

    companion object{
        const val END_POINT = "matches.json"
        const val BASE_URL = "https://rubens23.github.io/matches-simulator-api/"
    }
}