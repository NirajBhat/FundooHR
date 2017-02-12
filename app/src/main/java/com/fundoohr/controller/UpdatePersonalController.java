package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 27/1/17.
 */
public class UpdatePersonalController {
    public void updateController(String token, RequestParams params,
                                 final PersonalDetailBInterface personalDetailBInterface){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        Log.i("updateContro", "updateController: "+token);
        asyncHttpClient.put("http://192.168.0.16:3000/updateEmployeePersonalData", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                personalDetailBInterface.personalViewModelData(responseBody);
                Log.i("UpdatePersonal", "onSuccess: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("UpdatePersonal", "onFailure: "+responseBody);
            }
        });
    }
}
