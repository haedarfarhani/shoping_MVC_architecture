package com.heydar.simplemcv.view.interfaces;

import com.heydar.simplemcv.model.database.entity.UserEntity;
import com.heydar.simplemcv.model.network.responce.UserInfoResponse;

public interface ISplashView extends IUserBaseView {
    void getSessionTokenFromSharedPref(Boolean hasToken);
    void saveUserDB();
}
