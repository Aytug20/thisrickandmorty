package com.example.thisrickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thisrickandmorty.R
import com.example.thisrickandmorty.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*


class CharacterListFragment : Fragment() {

    private lateinit var viewModel: CharacterListViewModel
    private val recyclerCharacterAdapter = CharacterRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel  = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        viewModel.refreshData()



        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerCharacterAdapter

        observeLiveData()





    }

    fun observeLiveData(){

        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let{

                recyclerView.visibility = View.VISIBLE
                recyclerCharacterAdapter.characterListGüncelle(characters)


            }

        })

        viewModel.characterErrorMessage.observe(viewLifecycleOwner, Observer {  hata ->
            hata?.let {
                if (it) {
                    character_hata_mesaj.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE

                } else {
                    character_hata_mesaj.visibility =View.GONE

                }
            }

        })

        viewModel.characterLoading.observe(viewLifecycleOwner,Observer{ yukleniyor ->
            yukleniyor?.let{
                if (it){
                recyclerView.visibility = View.GONE
                character_hata_mesaj.visibility = View.GONE
                character_yükleniyor.visibility = View.VISIBLE
            } else{
                character_yükleniyor.visibility = View.GONE
                }


        }

        })

    }
}