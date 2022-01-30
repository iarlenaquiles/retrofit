package com.aquiles.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aquiles.requisicoeshttp.api.CEPService;
import com.aquiles.requisicoeshttp.api.DataService;
import com.aquiles.requisicoeshttp.model.CEP;
import com.aquiles.requisicoeshttp.model.Foto;
import com.aquiles.requisicoeshttp.model.Postagem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;
    private Button btnReq;
    private Retrofit retrofit;
    private List<Foto> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReq = findViewById(R.id.btnReq);
        txtResultado = findViewById(R.id.txtResultado);

        retrofit = new Retrofit
                .Builder()
//                .baseUrl("https://viacep.com.br/ws/")
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recuperarCEPRetrofit();
//                recuperarListaRetrofit();
                salvarPostagem();
            }
        });

    }

    private void salvarPostagem() {
        Postagem postagem = new Postagem("1221", "titulo da pastagem", "corpo da postagem");

        DataService service = retrofit.create(DataService.class);
        Call<Postagem> call = service.salvarPostagem(postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()) {
                    Postagem resposta = response.body();
                    txtResultado.setText("codigo:" + response.code() + " id: " + resposta.getId() + " titulo: " + resposta.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void recuperarListaRetrofit() {
        DataService service = retrofit.create(DataService.class);

        Call<List<Foto>> call = service.recuperarFotos();
        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if (response.isSuccessful()) {
                    listaFotos = response.body();
                    for (int i = 0; i < listaFotos.size(); i++) {
                        Foto foto = listaFotos.get(i);
                        Log.d("resultado", "onResponse: " + foto.getId() + " / " + foto.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });

    }

    public void recuperarCEPRetrofit() {
        CEPService cepService = retrofit.create(CEPService.class);

        Call<CEP> call = cepService.recuperarCep("61655490");

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()) {
                    CEP cep = response.body();
                    txtResultado.setText(cep.getLogradouro() + "/ " + cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}