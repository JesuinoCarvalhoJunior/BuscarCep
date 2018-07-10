package com.jcarvalhojr.buscarcep.Activity;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Helpers.AlertUtils;
import com.jcarvalhojr.buscarcep.ServiceUtils.PermissionUtils;

/**
 * Splash para listar as permissões.
 */
public class SplashActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Valida lista de permissões.
        String permissions[] = getResources().getStringArray(R.array.array_permission);

        boolean ok = PermissionUtils.validate(this, 0, permissions);


        if (ok) {
            progressDialog = new ProgressDialog(SplashActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Aguarde...");
            progressDialog.show(); // show progress dialog
            // Tudo OK, pode entrar.
            startActivity(new Intent(this, MainActivity.class));
            progressDialog.dismiss();
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for (int result : grantResults) {

            if (result == PackageManager.PERMISSION_DENIED) {
                // Negou a permissão. Mostra alerta e fecha.
                AlertUtils.alert(SplashActivity.this, R.string.app_name, R.string.msg_alerta_permissao, R.string.ok, new Runnable() {
                    @Override
                    public void run() {
                        // Negou permissão. Sai do app.
                        finish();
                    }

                });
                return;

            }
        }

        progressDialog = new ProgressDialog(SplashActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show(); // show progress dialog
        // Permissões concedidas, pode entrar.
        startActivity(new Intent(this, MainActivity.class));
        progressDialog.dismiss();
        finish();
    }
}