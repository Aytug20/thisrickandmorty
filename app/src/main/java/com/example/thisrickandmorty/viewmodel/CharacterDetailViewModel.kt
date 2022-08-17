package com.example.thisrickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thisrickandmorty.model.Character


class CharacterDetailViewModel : ViewModel() {
    val characterLiveData = MutableLiveData<Character>()


}