package com.fundoohr.bridgeit.fundoohr.callback;

import com.fundoohr.bridgeit.fundoohr.model.LoginModel;

/**
 * Created by bridgeit on 6/1/17.
 */
public interface ILoginCallback {
    void loginResponse(LoginModel loginData,int statuscode);
}