package com.example.thisrickandmorty.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val characterId:String?,
    @SerializedName("name")
    val characterName:String?,
    @SerializedName("status")
    val characterStatus:String?,
    @SerializedName("species")
    val characterSpecies:String?,
    @SerializedName("gender")
    val characterGender:String?,
    @SerializedName("image")
    val characterImage:String?
)

data class CharacterResponse(
    @SerializedName("results") val result: List<Character>
)

