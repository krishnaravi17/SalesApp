package com.tech.salesapp.Network;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CallWebAPI {
    private static final String url = "http://192.168.0.101:7001/JavaAPIDemo/api/";
    private static final String urlForFile = "http://localhost70/programmingPoint/photo";
    private static String response = "";

    public static Object callWebApi(String controllerName, String argument) throws ClientProtocolException, IOException {
        Object result = null;
        HttpClient httpClient = new DefaultHttpClient();
        String completeUrl = url + controllerName + /*"/" + */ argument;
        HttpGet getRequest = new HttpGet(completeUrl);
        ResponseHandler<String> handler = new BasicResponseHandler();
        result = httpClient.execute(getRequest, handler);
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    public static Object callWebApiWithoutArgument(String controllerName) throws ClientProtocolException, IOException {
        Object result = null;
        HttpClient httpClient = new DefaultHttpClient();
        String completeUrl = url + controllerName + "/";
        HttpGet getRequest = new HttpGet(completeUrl);
        ResponseHandler<String> handler = new BasicResponseHandler();
        result = httpClient.execute(getRequest, handler);
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    public static Object callWebApiWithQueryString(String controllerName, String queryString) throws ClientProtocolException, IOException {
        Object result = null;
        HttpClient httpClient = new DefaultHttpClient();
        String completeUrl = url + controllerName + "?" + queryString;
        HttpGet getRequest = new HttpGet(completeUrl);
        ResponseHandler<String> handler = new BasicResponseHandler();
        result = httpClient.execute(getRequest, handler);
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    public static Object callWebApiPostMethod(String controllerName, String jsonStringofList) throws ClientProtocolException, IOException {
        Object result = null;
        String completeUrl = url + controllerName + "/";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(completeUrl);
        StringEntity stringEntity = new StringEntity(jsonStringofList);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Accept-Encoding", "gzip");
        ResponseHandler<String> handler = new BasicResponseHandler();
        result = client.execute(httpPost, handler);
        return result;
    }

    public static Object callWebApiPutMethod(String controller, String jsonList, int id) throws UnsupportedEncodingException, ClientProtocolException, IOException {
        Object result = null;
        String completeUrl = url + controller + "/?id=" + id;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(completeUrl);
        StringEntity stringEntity = new StringEntity(jsonList);
        httpPut.setEntity(stringEntity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        ResponseHandler handler=new BasicResponseHandler();
        result=httpClient.execute(httpPut,handler);
        return result;
    }

    public static Object callWebApiPostMethodforFile(String controller,String filename,String userName,String fileFor) throws ClientProtocolException,IOException {
        Object result=null;
        String completeUrl=urlForFile+controller+"/";
        DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(completeUrl);
        httpPost.setHeader("UserName", userName);
        httpPost.setHeader("fileName", filename);
        httpPost.setHeader("fileFor", fileFor);
        ResponseHandler<String> responseHandler=new BasicResponseHandler();
        result=defaultHttpClient.execute(httpPost,responseHandler);
        return result;
    }

}
