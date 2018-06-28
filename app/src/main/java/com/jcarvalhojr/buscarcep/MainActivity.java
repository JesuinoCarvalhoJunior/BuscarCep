package com.jcarvalhojr.buscarcep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Domain.Cep;
import com.jcarvalhojr.buscarcep.Helpers.Mask;
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
    private String TAG = "Rotação";

    private EditText edtCep;
    private TextView txtRetornoCep;
    private Button btnBuscarCep;
    private TextWatcher cepMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialize();


        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edtCep.getText())) {
                    edtCep.setError("Informe o CEP");
                    edtCep.setFocusable(true);
                    return;
                }

                Call<Cep> call = serviceGetCep.getCep(Mask.unmask(edtCep.getText().toString()));
                call.enqueue(new Callback<Cep>() {
                    @Override
                    public void onResponse(Call<Cep> call, Response<Cep> response) {

                        response.message().replace("}", "");
                        Cep cep = response.body();

                        if (response.isSuccessful()) {

                            if (cep != null) {
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

                        call.cancel();
                    }
                });
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Retorno", txtRetornoCep.getText().toString());
        Log.i(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtRetornoCep.setText(savedInstanceState.getString("Retorno"));
        Log.i(TAG, "onRestoreInstanceState()");
    }


    private void initialize() {
        edtCep = (EditText) findViewById(R.id.edtCep);
        txtRetornoCep = (TextView) findViewById(R.id.txtRetornoCep);
        btnBuscarCep = (Button) findViewById(R.id.btnBuscarCep);
        cepMask = Mask.insert("##.###-###", edtCep);
        edtCep.addTextChangedListener(cepMask);
        createServiceGetCep();
    }


    private void createServiceGetCep() {
        serviceGetCep = ServiceGetInstanceRetrofit.getInstanceRetrofit().create(ServiceGetCep.class);
    }
}
