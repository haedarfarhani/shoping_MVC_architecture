package com.heydar.simplemcv.view.interfaces;

import com.heydar.simplemcv.model.network.responce.BaseResult;
import com.heydar.simplemcv.model.network.responce.LoginResponse;

public interface ILoginView {
    void getSessionTokenFromLogin(BaseResult<LoginResponse> result,  String message);
}
