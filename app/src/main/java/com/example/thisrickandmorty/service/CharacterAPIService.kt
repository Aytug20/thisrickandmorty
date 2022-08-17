package com.example.thisrickandmorty.service

import com.example.thisrickandmorty.model.Character
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharacterAPIService {

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()


    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(CharacterAPI::class.java)

    fun getData() : Single<List<Character>> {
        return api.getCharacter()
    }

}