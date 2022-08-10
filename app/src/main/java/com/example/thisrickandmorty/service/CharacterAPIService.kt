package com.example.thisrickandmorty.service

import com.example.thisrickandmorty.model.Character
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CharacterAPIService {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CharacterAPI::class.java)

    fun getData() : Single<List<Character>> {
        return api.getCharacter()
    }

}