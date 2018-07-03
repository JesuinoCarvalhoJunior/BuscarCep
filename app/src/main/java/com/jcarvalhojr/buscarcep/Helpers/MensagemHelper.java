package com.jcarvalhojr.buscarcep.Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.jcarvalhojr.buscarcep.MainActivity;


/**
 * Created by Junior_Carvalho on 27/06/2018.
 */
public class MensagemHelper extends Activity {

    protected static final String TAG = "StackTrace";
    public static ProgressDialog dialog = null;
    static Context ct;
    private TextView versaoinfo;

    /* Lê a versão do app */
    public static String getVersionName(Activity activity) {
        PackageManager pm = activity.getPackageManager();
        String packageName = activity.getPackageName();
        String versionName;
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            versionName = info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            versionName = "N/A";
        }
        return versionName;
    }


    public static void showMessageOKCancel(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }


    public static void alertDialog(final Context context, final int title, final int mensagem) {
        try {
            android.app.AlertDialog dialog = new android.app.AlertDialog
                    .Builder(context)
                    .setTitle(title)
                    .setMessage(mensagem)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .create();
            dialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public static void alertDialog(final Context context,
                                   final String title,
                                   final String mensagem) {
        try {
            android.app.AlertDialog dialog = new android.app.AlertDialog
                    .Builder(context)
                    .setTitle(title)
                    .setMessage(mensagem)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .create();
            dialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


    }






}