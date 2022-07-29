package com.example.bootcampprojeto1.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bootcampprojeto1.databinding.MatchItemBinding
import com.example.bootcampprojeto1.domain.Partida
import com.example.bootcampprojeto1.ui.DetailActivity

class PartidasAdapter(private val matchesList: List<Partida>): RecyclerView.Adapter<PartidasAdapter.ViewHolder>() {

    fun getPartidas(): List<Partida> {
        return matchesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(matchesList[position])

    }

    override fun getItemCount(): Int = matchesList.size
    inner class ViewHolder(private val binding: MatchItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(partida: Partida) {
            Glide.with(binding.root.context).load(partida.mandante.imagem).circleCrop().into(binding.ivHomeTeam)
            Glide.with(binding.root.context).load(partida.visitante.imagem).circleCrop().into(binding.ivAwayTeam)
            binding.tvHomeTeamName.text = partida.mandante.nome
            binding.tvAwayTeamName.text = partida.visitante.nome
            if(partida.mandante.placar != null){
                binding.tvHomeTeamScore.text = partida.mandante.placar.toString()
            }
            if(partida.visitante.placar != null){
                binding.tvAwayTeamScore.text = partida.visitante.placar.toString()
            }
            itemView.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.Extras.MATCH, partida)
                binding.root.context.startActivity(intent)
            }



            Log.d("testandoapi", ""+partida.mandante.nome)


        }
    }

}