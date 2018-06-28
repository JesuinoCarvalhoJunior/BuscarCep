package com.jcarvalhojr.buscarcep.ServiceRetrofit;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceGetCep {

    //https://cep.awesomeapi.com.br/:format/:cep

    @GET("cep")
    Cal<> getCep(@Path("cep") String cep);

}



