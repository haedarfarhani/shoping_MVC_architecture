package com.heydar.simplemcv.controller;

import com.heydar.simplemcv.model.database.entity.UserEntity;
import com.heydar.simplemcv.model.network.responce.UserInfoResponse;

import java.util.HashMap;

public interface IUserController {
    void loginUser(HashMap<String , Object> params);
    void logoutUser();
    void registerUser(HashMap<String , Object> user);
    void getUser();
    void onDestroy();
    Boolean getSessionTokenFromSharedPref();
    void saveUserDB(UserInfoResponse user);
}
