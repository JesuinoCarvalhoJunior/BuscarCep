package com.jcarvalhojr.buscarcep.ServiceRetrofit;

import com.jcarvalhojr.buscarcep.Domain.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */


public interface ServiceGetCep {

    //https://cep.awesomeapi.com.br/:format/:cep

    @GET("cep")
    Call<Cep> getCep(@Path("cep") String cep);

}



