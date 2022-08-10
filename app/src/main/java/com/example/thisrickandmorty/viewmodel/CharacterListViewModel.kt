package com.example.thisrickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thisrickandmorty.model.Character
import com.example.thisrickandmorty.service.CharacterAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {
    val characters = MutableLiveData<List<Character>>()
    val characterErrorMessage= MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()

    private val characterAPIService = CharacterAPIService()
    private val disposable = CompositeDisposable()




    fun refreshData() {
        getDataInternet()

    }

    private fun getDataInternet(){
        characterLoading.value=true

        disposable.add(
            characterAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Character>>(){
                    override fun onSuccess(t: List<Character>) {
                        characters.value= t
                        characterErrorMessage.value=false
                        characterLoading.value=false
                    }

                    override fun onError(e: Throwable) {
                        characterErrorMessage.value=true
                        characterLoading.value=false
                    }

                  }))

                }


        }

















