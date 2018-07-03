package com.jcarvalhojr.buscarcep.ServiceUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.jcarvalhojr.buscarcep.Helpers.MensagemHelper.alertDialog;


/**
 * Created by JCarvalhoJr on 04/03/2018.
 */

public class ConnectivityServices {

    protected static final String TAG = "StackTrace";
    private static AlertDialog progressDialog;

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            alertDialog(context, e.getClass().getSimpleName(), e.getMessage());
        }
        return false;
    }


}
