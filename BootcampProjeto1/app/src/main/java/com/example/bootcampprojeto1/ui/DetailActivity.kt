package com.example.bootcampprojeto1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.bootcampprojeto1.databinding.ActivityDetailBinding
import com.example.bootcampprojeto1.domain.Partida

class DetailActivity : AppCompatActivity() {

    object Extras{
        const val MATCH = "EXTRA_MATCH"
    }
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Partida>(Extras.MATCH)?.let{
            Glide.with(this).load(it.local.imagem).into(binding.ivPlace)
            supportActionBar?.title = it.local.nome

            binding.tvDescription.text = it.descricao

            Glide.with(this).load(it.mandante.imagem).into(binding.ivHomeTeam)
            binding.tvHomeTeamName.text = it.mandante.nome
            Glide.with(this).load(it.visitante.imagem).into(binding.ivAwayTeam)
            binding.tvAwayTeamName.text = it.visitante.nome
            if(it.mandante.placar != null){
                binding.tvHomeTeamScore.text = it.mandante.placar.toString()
            }
            if(it.visitante.placar != null){
                binding.tvAwayTeamScore.text = it.visitante.placar.toString()
            }

            binding.rbHomeTeamStars.rating = it.mandante.forca.toFloat()
            binding.rbAwayTeamStars.rating = it.visitante.forca.toFloat()
        }
    }
}