package com.jcarvalhojr.buscarcep;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Domain.Cep;
import com.jcarvalhojr.buscarcep.Fragments.AboutDialog;
import com.jcarvalhojr.buscarcep.Helpers.AlertUtils;
import com.jcarvalhojr.buscarcep.Helpers.MaskEditText;
import com.jcarvalhojr.buscarcep.Helpers.getVersionApp;
import com.jcarvalhojr.buscarcep.ServiceRetrofit.ServiceGetCep;
import com.jcarvalhojr.buscarcep.ServiceRetrofit.ServiceGetInstanceRetrofit;
import com.jcarvalhojr.buscarcep.ServiceUtils.ConnectivityServices;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jcarvalhojr.buscarcep.Helpers.ShowHideKeyboard.hideKeyboard;
import static com.jcarvalhojr.buscarcep.Helpers.ShowHideKeyboard.showKeyboard;


/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */

public class MainActivity extends AppCompatActivity {

    ServiceGetCep serviceGetCep;
    private GoogleMap mMap;
    private String TAG = "Rotacao";
    private EditText edtCep;
    private TextView txtRetornoCep;
    private Button btnBuscarCep;
    private TextWatcher cepMask;
    private FragmentManager fragmentManager;
    private AlertDialog progressCustomDialog;

    private Double lat;
    private Double lng;
    private String endereco;

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

                progressCustomDialog.show();
                if (ConnectivityServices.isNetworkAvailable(MainActivity.this)) {
                    fetchCallCep();
                } else {
                    progressCustomDialog.dismiss();
                    AlertUtils.alertDialog(MainActivity.this,
                            R.string.info_title_alertdialog,
                            R.string.error_conexao_indisponivel);
                }
            }
        });


    }


    private void fetchCallCep() {
        Call<Cep> call = serviceGetCep.getCep(MaskEditText.unmask(edtCep.getText().toString()));
        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {

                Cep cep = response.body();

                if (response.isSuccessful()) {

                    if (cep != null) {

                        fetchRetorno(cep);

                    } else {
                        progressCustomDialog.dismiss();
                        txtRetornoCep.setText("Cep não encontrado!");
                        Toast.makeText(getApplicationContext(), "Nenhuma correspondência encontrada!", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() != 200) {
                    progressCustomDialog.dismiss();
                    txtRetornoCep.setText("Erro " + response.code() + " encontrado!");
                    txtRetornoCep.setGravity(Gravity.CENTER);
                    hideKeyboard(MainActivity.this, edtCep);
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


    private void fetchRetorno(Cep cep) {

        txtRetornoCep.setGravity(Gravity.LEFT);
        txtRetornoCep.setText(cep.toString());
        txtRetornoCep.requestFocus();
        progressCustomDialog.dismiss();
        hideKeyboard(MainActivity.this, edtCep);

        lat = Double.valueOf(cep.getLat()).doubleValue();
        lng = Double.valueOf(cep.getLng()).doubleValue();
        endereco  = cep.getAddress();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Retorno", txtRetornoCep.getText().toString());
        txtRetornoCep.requestFocus();
        Log.i(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtRetornoCep.setText(savedInstanceState.getString("Retorno"));
        txtRetornoCep.requestFocus();
        Log.i(TAG, "onRestoreInstanceState()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            AboutDialog.showAbout(getSupportFragmentManager());
            return true;
        }

        if (id == R.id.action_mapa) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.putExtra("endereco", endereco);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    private void initialize() {
        edtCep = (EditText) findViewById(R.id.edtCep);
        txtRetornoCep = (TextView) findViewById(R.id.txtRetornoCep);
        btnBuscarCep = (Button) findViewById(R.id.btnBuscarCep);
        cepMask = MaskEditText.insert("##.###-###", edtCep);
        edtCep.addTextChangedListener(cepMask);
        createServiceGetCep();
        showKeyboard(MainActivity.this, edtCep);

        createCustomDialog();
    }


    public void createCustomDialog() {
        progressCustomDialog = new SpotsDialog.Builder()
                .setContext(MainActivity.this)
                .setTheme(R.style.CustomDialog)
                .build();
    }

    private void createServiceGetCep() {
        serviceGetCep = ServiceGetInstanceRetrofit.getInstanceRetrofit().create(ServiceGetCep.class);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Main activity onRestart.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Main activity onPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Main activity onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Main activity onDestroy.");
    }


}
