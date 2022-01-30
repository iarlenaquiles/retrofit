package com.aquiles.requisicoeshttp.api;

import com.aquiles.requisicoeshttp.model.Foto;
import com.aquiles.requisicoeshttp.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();
}
