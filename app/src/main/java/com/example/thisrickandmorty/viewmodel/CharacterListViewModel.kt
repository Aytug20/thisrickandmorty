package com.example.thisrickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thisrickandmorty.model.Character
import com.example.thisrickandmorty.model.CharacterResponse
import com.example.thisrickandmorty.service.CharacterAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {

    val characters = MutableLiveData<List<Character>>()
    val characterErrorMessage= MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()

    private val characterAPIService = CharacterAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        //getDataInternet()

        val rick = Character("1","rick","Alive","Human","Male","-")
        val morty = Character("2","morty","Alive","Human","Male","-")

        val characterList = arrayListOf<Character>(rick,morty)

        characters.value = characterList
        characterErrorMessage.value=false
        characterLoading.value=false
    }

    private fun getDataInternet(){
        characterLoading.value=true
        println("girdi")
        disposable.add(
            characterAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Character>>()
                {
                    override fun onSuccess(t: List<Character>) {
                        println(t)
                        characterErrorMessage.value=false
                        characterLoading.value=false
                    }
                    override fun onError(e: Throwable) {
                        println("errror***"+e.message)
                        characterErrorMessage.value=true
                        characterLoading.value=false
                    }
                }))
                }
        }



















