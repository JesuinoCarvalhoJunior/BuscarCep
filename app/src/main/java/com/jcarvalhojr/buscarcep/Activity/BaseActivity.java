package com.jcarvalhojr.buscarcep.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jcarvalhojr.buscacep.R;
import com.jcarvalhojr.buscarcep.Lib.LibBaseActivity;

/**
 * Created by Junior_Carvalho on 25/03/2016.
 */

// classe mae base para todas as outras
public class BaseActivity extends LibBaseActivity {

    protected DrawerLayout drawerLayout;

/*
    // Atualiza os dados do header do Navigation View
    // Adicionar no cabecalho - Imagem + Nome + Email
    public static void setNavViewValues(NavigationView navView, int nome, int email, int img) {
        View headerView = navView.getHeaderView(0);
        TextView tNome = (TextView) headerView.findViewById(R.id.tUserName);
        TextView tEmail = (TextView) headerView.findViewById(R.id.tUserEmail);
        ImageView imgView = (ImageView) headerView.findViewById(R.id.imgUserPhoto);
        tNome.setText(nome);
        tEmail.setText(email);
        imgView.setImageResource(img);
    }
*/

    /**
     * A configuracao do menu lateral é feito aqi na base activity para
     * que seja possivel reutilizar o codigo no projeto
     * <p>
     * configura nav drawer
     * <p>Metodo ativa a ToolBar</p>
     */

    protected void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            /*if (getSupportActionBar() != null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }*/
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
/*
    protected void setupNavDrawer() {
        final ActionBar actionBar = getSupportActionBar();
        // icone do menu do nav drawer

        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

     *//*     if (navigationView != null && drawerLayout != null) {
           // Atualiza a imagem e textos do header
            NavDrawerUtil.setHeaderValues(navigationView,
                    R.id.containerNavDrawerListViewHeader,
                    R.drawable.nav_drawer_header,
                    R.drawable.ic_logo_user,
                    R.string.nav_drawer_username,
                    R.string.nav_drawer_email);*//*

        if (navigationView != null && drawerLayout != null) {
            // Atualiza os dados do header do Navigation View
            setNavViewValues(navigationView,
                    R.string.nav_drawer_username,
                    R.string.nav_drawer_email,
                    R.drawable.jr);


            // trata evento de clique no menu
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            //seleciona linha
                            menuItem.setChecked(true);
                            // fecha o menu
                            drawerLayout.closeDrawers();
                            // trata o evento do menu
                            onNavDrawerItemSelected(menuItem);
                            return true;
                        }
                    });
        }
    }*/

   /* // trata evento nos itens do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        final Context context = this;
        switch (menuItem.getItemId()) {

            case R.id.nav_item_carros_todos:
                // mostrar as 3 tabs classico, esportivos e luxo
                //       replaceFragment(new CarroTabFragment());
                // nao implementa nada pois somente a main activity possui menu lateral
                toast("clicou em carros - todos os carros");
                break;
            case R.id.nav_item_carros_classicos:

                Intent intent = new Intent(getContext(), CarrosActivity.class);
                intent.putExtra("tipo", R.string.classicos);
                startActivity(intent);
                //replaceFragment(CarrosFragment.newInstance(R.string.classicos));
                //   toast("carros classicos");
                break;
            case R.id.nav_item_carros_esportivos:
                //  replaceFragment(CarrosFragment.newInstance(R.string.esportivos));

                Intent intentEsport = new Intent(getContext(), CarrosActivity.class);
                intentEsport.putExtra("tipo", R.string.esportivos);
                startActivity(intentEsport);

                toast("carros esportivos");
                break;
            case R.id.nav_item_carros_luxo:
                //   replaceFragment(CarrosFragment.newInstance(R.string.luxo));


                Intent intentLux = new Intent(getContext(), CarrosActivity.class);
                intentLux.putExtra("tipo", R.string.luxo);
                startActivity(intentLux);

                toast("carros luxo");
                break;
            case R.id.nav_item_site_livro:
                // fazer o mesmo conveito para este menu (ver codigo do livro)
                // startActivity(new Intent(getContext(), SiteLivroActivity.class));
                //replaceFragment(new SiteLivroFragment());
                // snack(drawerLayout, "site livro");

                break;
            case R.id.nav_item_settings:
                //toast("configuracoes");

                //chama activity conforme versao do android
                if (AndroidUtils.isAndroid3Honeycomb()) {
                    startActivity(new Intent(this, ConfiguracoesV11Activity.class));
                } else {
                    startActivity(new Intent(this, ConfiguracoesActivity.class));
                }

                break;

            case R.id.nav_item_sobreaparelho:
                //InfoHardsoftware();

                Intent i = new Intent(getContext(), Exibe_Perc_Bateria.class);
                startActivity(i);
                break;

            case R.id.nav_item_sairaplicacao:
                onBackPressed();
                break;

        }
    }*/

   /* // adiciona o fragment ao cenro da tela
    // esse metodo encapsula o codigo para adicionar um fragment dinamicamente ao layout
    //replace = substituir o fragment
    protected void replaceFragment(Fragment frag) {
        //  getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, "TAG").commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //trata cique no botao qe abre o menu
                // o botao fica no canto superior esquerdo
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }


    // abre o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);

        }
    }*/

/*

    private boolean InfoHardsoftware() {
        StringBuffer buf = new StringBuffer();
        buf.append("<b>VERSION.RELEASE</b><br />" + Build.VERSION.RELEASE + "<br />");
        buf.append("<b>VERSION.INCREMENTAL</b><br />" + Build.VERSION.INCREMENTAL + "<br />");
        buf.append("<b>VERSION.SDK_INT</b><br />" + Build.VERSION.SDK_INT + "<br />");
        buf.append("<b>VERSION.CODENAME</b><br />" + Build.VERSION.CODENAME + "<br />");
        buf.append("<b>BOARD</b><br />" + Build.BOARD + "<br />");
        buf.append("<b>BRAND</b><br />" + Build.BRAND + "<br />");
        buf.append("<b>DEVICE</b><br />" + Build.DEVICE + "<br />");
        buf.append("<b>FINGERPRINT</b><br />" + Build.FINGERPRINT + "<br />");
        buf.append("<b>HOST</b><br />" + Build.HOST + "<br />");
        buf.append("<b>ID</b><br />" + Build.ID + "<br />");
        buf.append("<b>PRODUTO</b><br />" + Build.MANUFACTURER + "<br />");
        buf.append("<b>MODELO</b><br />" + Build.MODEL + "<br />");
        buf.append("<b>BOOT</b><br />" + Build.BOOTLOADER + "<br />");
        buf.append("<b>HARDWARE</b><br />" + Build.HARDWARE + "<br />");
        buf.append("<b>SERIAL</b><br />" + Build.SERIAL + "<br />");

        SoftHardware.showStringDialog(this.getContext(), "System Info", Html.fromHtml(buf.toString()));
        return true;
    }
*/


/*@Override
    public void onBackPressed() {
    SoftHardware.alertDialog1(this.getContext(),"Alerta", "Deseja Sair?" );

}*/


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Alerta");
        alertDialogBuilder.setIcon(R.drawable.ic_group_collapse_04);

        alertDialogBuilder.setMessage("Deseja realmente sair?").setCancelable(false).setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sairAplicacao();
                    }
                })
                .setNegativeButton("Não",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void sairAplicacao() {
        //- ACTION_MAIN com a categoria CATEGORY_HOME = Lançamento da tela inicial.
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        //Se definido, esta atividade se tornará o começo de uma nova tarefa nesta pilha de histórico.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //    startActivity(intent);
        finish();
    }
}


