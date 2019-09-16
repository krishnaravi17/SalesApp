package com.tech.salesapp.Thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.tech.salesapp.Network.CallWebAPI;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

public class LoginThread extends AsyncTask<Void, Long, Object> {

    Context context;
    String username, password;

    public LoginThread(Context context, String username, String password) {

        this.context = context;
        this.username = username;
        this.password = password;

    }


    @Override
    protected Object doInBackground(Void... voids) {
        Object obj = null;
        try {

            obj = CallWebAPI.callWebApi("v1/Login", "?username=" + username + "&password=" + password);

        } catch (ClientProtocolException e) {
            obj=e.toString();

        } catch (IOException e2) {
            obj=e2.toString();

        } catch (Exception e3) {
            obj=e3.toString();

        }

        return obj;
    }

    @Override
    protected void onPostExecute(Object obj) {
        //super.onPostExecute(obj);
        if (obj != null) {
            Toast.makeText(context, "worked!!!" + obj.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
