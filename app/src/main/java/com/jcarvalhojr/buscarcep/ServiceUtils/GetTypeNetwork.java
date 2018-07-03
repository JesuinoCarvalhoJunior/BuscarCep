package com.jcarvalhojr.buscarcep.ServiceUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */

/**
 * To get device consuming netowkr type is 2g,3g,4g
 *
 * @param context
 * @return "2g","3g","4g", Wifi as a String based on the network type
 */
public class GetTypeNetwork {

    public static String getTypeNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info == null || !info.isConnected())
            return "Sem conexão"; //sem conexão

        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WiFi";

        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : troque por 11
                    return "2G";
                // ~ 400-7000 kbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    /**
                     From this link https://goo.gl/R2HOjR ..NETWORK_TYPE_EVDO_0 & NETWORK_TYPE_EVDO_A
                     EV-DO is an evolution of the CDMA2000 (IS-2000) standard that supports high data rates.

                     Where CDMA2000 https://goo.gl/1y10WI .CDMA2000 is a family of 3G[1] mobile technology standards for sending voice,
                     data, and signaling data between mobile phones and cell sites.
                     */
                    // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : troque por 14
                    // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : troque por 12
                    // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : troque por 15
                    return "3G";
                //Log.d("Type", "3g");
                //For 3g HSDPA , HSPAP(HSPA+) are main  networktype which are under 3g Network
                //But from other constants also it will 3g like HSPA,HSDPA etc which are in 3g case.
                //Some cases are added after  testing(real) in device with 3g enable data
                //and speed also matters to decide 3g network type
                //http://goo.gl/bhtVT
                // ~ 10+ Mbps
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : troque por 13
                    return "4G";

                //Nenhuma especificação para o 4G, mas a partir wiki
                //I found(LTE (Long-Term Evolution, commonly marketed as 4G LTE))
                //https://goo.gl/9t7yrR
                default:
                    return "Tipo de Conexão não identificado";
            }
        }
        return "Tipo de Conexão não identificado";
    }
}