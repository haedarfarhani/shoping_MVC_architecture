package com.heydar.simplemcv.view.interfaces;

import com.heydar.simplemcv.model.network.responce.BaseResult;
import com.heydar.simplemcv.model.network.responce.LoginResponse;

public interface ISignupView {
    void getRegisterUser(BaseResult<LoginResponse> result , String message);
}
