package com.tech.salesapp.Network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Json {

    /*---------------To Json--------------*/
    public static  String createJsonObject(Json json){
        Gson gson=new Gson();
        Type type=new TypeToken<Json>(){}.getType();
        return gson.toJson(json,type);
    }

    public static String createJsonStringList(List<Json> lst){
        Gson gson=new Gson();
        Type type=new TypeToken<List<Json>>(){
        }.getType();
        return gson.toJson(lst,type);
    }
    /*-------------From Json-----------------*/
    public static Json getjsonObject(String serverjson){
        Gson gson=new Gson();
        Type type=new TypeToken<Json>(){}.getType();
        return gson.fromJson(serverjson,type);
    }
    public static List<Json> getListDataFromJson(String serverJson){
        Gson gson=new Gson();
        Type type=new TypeToken<List<Json>>(){}.getType();
        return gson.fromJson(serverJson,type);
    }

}


// List lstBanks = ((RegistrationFourthPart) RegistrationFourthPart.getListOfBankDetailsFromServerJson(str).get(0)).getLstBanks();
// List listOfTehsilFromServerJson = G08_Tehsil.getListOfTehsilFromServerJson(str);
// List<EL03_ErrorLog> deviceExceptionDataListFromJson = EL03_ErrorLog.getDeviceExceptionDataListFromJson((String) obj);