package com.example.cocktailgenie.datahandlers;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//Singleton for Volley
public class MySingleton extends Application {


    private static MySingleton singletonInstance;
    public static final String TAG = MySingleton.class.getSimpleName();
    private RequestQueue requestQueue;

    public void onCreate() {
        super.onCreate();
        singletonInstance = this;
    }

    public static synchronized MySingleton getInstance(){
        return singletonInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(requestQueue != null){
            requestQueue.cancelAll(tag);
        }
    }

}
