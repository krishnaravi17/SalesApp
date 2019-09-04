package com.tech.salesapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class AndroidUtils {


    public static boolean checkYourMobileDataConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = connectivityManager.getNetworkInfo(1).isConnected();
        boolean isConnected2 = connectivityManager.getNetworkInfo(0).isConnected();
        if (!isConnected && !isConnected2) {
            return false;
        }
        Log.d("DEBUG", "Data Connected : ");
        return true;
    }

 /*   private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }*/

  /*  public static boolean isMobileDataConnected(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(0).isConnected();
    }*/

  /*  public static String checkYourDataConnectionType(Context context) {
        String str = "";
        switch (((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "unknown";
        }
    }*/

  /*  public static boolean isWifiConnected(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.WIFI_SERVICE)).getNetworkInfo(1).isConnected();
    }*/
}
