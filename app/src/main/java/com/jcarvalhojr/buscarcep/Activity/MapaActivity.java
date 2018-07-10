package com.jcarvalhojr.buscarcep.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Domain.Cep;
import com.jcarvalhojr.buscarcep.Fragments.MapaFragment;

import org.parceler.Parcels;

public class MapaActivity extends BaseActivity {

    private Cep cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        setUpToolBar();

        cep = Parcels.unwrap(getIntent().getParcelableExtra("cep"));

        getSupportActionBar().setTitle(cep.getCep().toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // adiciona o fragment no layout da activity
            MapaFragment mapaFragment = new MapaFragment();
            mapaFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragLayout, mapaFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // volta para CarroActivity

        /*   switch (item.getItemId()) {
         case android.R.id.home:
              *//*  Intent intent = NavUtils.getParentActivityIntent(getActivity());
               intent.putExtra("cep", Parcels.wrap(cep));
                NavUtils.navigateUpTo(getActivity(), intent);*//*


                Intent intent = new Intent(MapaActivity.this, MainActivity.class);
                //intent.putExtra("endereco", endereco);
                startActivity(intent);
                finish();

                return true;*/

        if (item.getItemId() == android.R.id.home) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
