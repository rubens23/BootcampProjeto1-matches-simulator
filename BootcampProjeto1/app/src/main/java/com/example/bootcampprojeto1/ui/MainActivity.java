package com.example.bootcampprojeto1.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bootcampprojeto1.R;
import com.example.bootcampprojeto1.data.api.MatchesApi;
import com.example.bootcampprojeto1.databinding.ActivityMainBinding;
import com.example.bootcampprojeto1.domain.Partida;
import com.example.bootcampprojeto1.ui.adapters.PartidasAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MatchesApi matchesApi;
    private PartidasAdapter adapter = new PartidasAdapter(Collections.emptyList());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFAB();


    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MatchesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        matchesApi = retrofit.create(MatchesApi.class);

    }

    private void setupFAB() {
        binding.fabSimulate.setOnClickListener(v->{
            v.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    Random random = new Random();
                    for (int i = 0; i < adapter.getItemCount(); i++) {
                        Partida partida = adapter.getPartidas().get(i);
                        partida.getMandante().setPlacar(random.nextInt(partida.getMandante().getForca() + 1));
                        partida.getVisitante().setPlacar(random.nextInt(partida.getVisitante().getForca() + 1));
                        //notifica ao adapter que o item da lista foi alterado
                        adapter.notifyItemChanged(i);
                    }

                }

            });
        });
    }

    private void setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void setupMatchesList() {
        binding.rvMatches.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.rvMatches.setAdapter(adapter);
        findMatchesFromApi();
    }

    private void findMatchesFromApi() {
        binding.srlMatches.setRefreshing(true);
        matchesApi.getMatches().enqueue(new Callback<List<Partida>>() {
            @Override
            public void onResponse(Call<List<Partida>> call, Response<List<Partida>> response) {
                Log.d("onresponse", "to no onresponse"+response.code());
                if(response.isSuccessful()){
                    List<Partida> partidas = response.body();
                    adapter = new PartidasAdapter(partidas);
                    binding.rvMatches.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.rvMatches.setAdapter(adapter);

                }else{
                    showErrorMessage();
                }
                binding.srlMatches.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Partida>> call, Throwable t) {
                Log.d("onfailure", ""+t.getMessage().toString());
                binding.srlMatches.setRefreshing(false);

            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(this, R.string.error_api, Toast.LENGTH_LONG).show();
    }
}

/*

 */

