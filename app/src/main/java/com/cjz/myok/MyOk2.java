package com.cjz.myok;

import android.util.Log;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class MyOk2 {

    public MyOk2(String uri, String args){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, args);
        Request request = new Request.Builder()
                .url("http://192.168.1.112:8085/"+uri)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            if (jsonObject.getString("status").equals("200")){
                ok(jsonObject);
            }else {
                ok(null);
                Log.d("error", jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void ok(JSONObject jsonObject);
}
