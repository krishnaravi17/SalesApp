package com.tech.salesapp.Exception;

import android.util.Log;

public class ExceptionLog {
    public static void catchException(Exception ex) {
        Log.e("CiboSupply EXCEPTION", ex + "");
    }
}
