package com.jcarvalhojr.buscarcep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Domain.Cep;
import com.jcarvalhojr.buscarcep.ServiceRetrofit.ServiceGetCep;
import com.jcarvalhojr.buscarcep.ServiceRetrofit.ServiceGetInstanceRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */

public class MainActivity extends AppCompatActivity {


    ServiceGetCep serviceGetCep;
    private EditText edtCep;
    private EditText txtRetornoCep;
    private Button btnBuscarCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Cep> call = serviceGetCep.getCep(edtCep.getText().toString());
                call.enqueue(new Callback<Cep>() {
                    @Override
                    public void onResponse(Call<Cep> call, Response<Cep> response) {
                        Cep cep = response.body();

                        if (response.isSuccessful()) {

                            if (!cep.getCep().isEmpty()) {
                                txtRetornoCep.setText(cep.toString());

                            } else {
                                Toast.makeText(getApplicationContext(), "Nenhuma correspondência encontrada!", Toast.LENGTH_SHORT).show();
                            }

                        } else if (response.code() != 200) {
                            Toast.makeText(getApplicationContext(), "Erro " + response.code() + " encontrado!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Cep> call, Throwable t) {
                        Log.e("Consulta", "Erro ao fazer a consulta: " + t.getMessage());
                        // Log error here since request failed
                        call.cancel();
                    }
                });

            }
        });


    }


    private void initialize() {

        final EditText edtCep = findViewById(R.id.edtCep);
        final TextView txtRetornoCep = findViewById(R.id.txtRetornoCep);
        final Button btnBuscarCep = findViewById(R.id.btnBuscarCep);
        createServiceGetCep();

    }


    private void createServiceGetCep() {

        serviceGetCep = ServiceGetInstanceRetrofit.getInstanceRetrofit().create(ServiceGetCep.class);
    }
}
