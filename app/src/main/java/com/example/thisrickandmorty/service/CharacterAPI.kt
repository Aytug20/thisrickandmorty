package com.example.thisrickandmorty.service


import com.example.thisrickandmorty.model.Character
import io.reactivex.Single
import retrofit2.http.GET

interface CharacterAPI {
    @GET("character")
    fun getCharacter(): Single<List<Character>>

}