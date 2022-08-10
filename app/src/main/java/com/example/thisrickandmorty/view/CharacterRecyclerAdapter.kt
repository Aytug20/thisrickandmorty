package com.example.thisrickandmorty.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thisrickandmorty.R
import com.example.thisrickandmorty.model.Character
import kotlinx.android.synthetic.main.character_recycler_row.view.*


class CharacterRecyclerAdapter(val characterList : ArrayList<Character>) :
    RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.character_recycler_row,parent,false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        //id
        holder.itemView.name.text = characterList.get(position).characterName
        holder.itemView.status.text = characterList.get(position).characterStatus
        holder.itemView.species.text = characterList.get(position).characterSpecies
        holder.itemView.gender.text = characterList.get(position).characterGender
        // image eklenecek

        holder.itemView.setOnClickListener{
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(0)
            Navigation.findNavController(it).navigate(action)

        }

    }

    fun characterListGüncelle(newCharacterList: List<Character>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

}