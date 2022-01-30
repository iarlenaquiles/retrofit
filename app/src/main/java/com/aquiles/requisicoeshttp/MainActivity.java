package com.aquiles.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;
    private Button btnReq;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReq = findViewById(R.id.btnReq);
        txtResultado = findViewById(R.id.txtResultado);

        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://viacep.com.br/ws/01001000/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}