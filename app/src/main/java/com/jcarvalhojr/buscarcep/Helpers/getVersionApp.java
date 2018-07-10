package com.jcarvalhojr.buscarcep.Helpers;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


/**
 * Created by Junior_Carvalho on 27/06/2018.
 */
public class getVersionApp extends Activity {


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







}