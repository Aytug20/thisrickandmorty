package com.example.thisrickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thisrickandmorty.R
import com.example.thisrickandmorty.model.Character
import com.example.thisrickandmorty.viewmodel.CharacterDetailViewModel
import com.example.thisrickandmorty.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.character_recycler_row.*
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailViewModel

    private var characterId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel  = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)




        arguments?.let{
             characterId=CharacterDetailFragmentArgs.fromBundle(
                 it
             ).charactersId
            println(characterId)

        }
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer{ character->
            character?.let{
                charactersId.text=it.characterId
                charactersName.text=it.characterName
                charactersStatus.text=it.characterStatus
                charactersSpecies.text=it.characterSpecies
                charactersGender.text=it.characterGender



        }})
    }

}