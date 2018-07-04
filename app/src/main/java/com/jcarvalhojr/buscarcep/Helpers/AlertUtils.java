package com.jcarvalhojr.buscarcep.Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;
import com.jcarvalhojr.buscacep.R;

/**
 * Created by Junior_Carvalho on 27/06/2018.
 */

public class AlertUtils {

    private static final String TAG = "StackTrace";
    private static ProgressDialog dialog = null;
    private static Context ct;
    private TextView versaoinfo;

    public AlertUtils() {

    }


    public static void alert(Activity activity, String title, String message) {
        alert(activity, title, message, 0, 0);
    }

    public static void alert(Activity activity, String message) {
        alert(activity, null, message, 0, 0);
    }

    public static void alert(Activity activity, String title, String message, int okButton, int icon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (icon > 0) {
            builder.setIcon(icon);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);

        String okString = okButton > 0 ? activity.getString(okButton) : "OK";

        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, okString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        dialog.show();
    }

    public static void alert(Context context, int title, int message, int okButton, final Runnable runnable) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        String okString = okButton > 0 ? context.getString(okButton) : "OK";
        // Add the buttons
        builder.setPositiveButton(okString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void show(Context context, int title, int message, final Runnable runnable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        AlertDialog dialog = builder.create();
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
