package com.heydar.simplemcv.model.repository;

import com.heydar.simplemcv.model.network.responce.BaseResult;
import com.heydar.simplemcv.model.network.responce.LoginResponse;
import com.heydar.simplemcv.model.network.responce.UserInfoResponse;
import com.heydar.simplemcv.model.network.retrofit.ApiService;
import com.heydar.simplemcv.model.network.retrofit.RetrofitService;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class UserRepository {
    ApiService apiService = RetrofitService.getInstance().createService(ApiService.class);

    public Single<BaseResult<LoginResponse>> getLogin(HashMap<String , Object> params)  {
        return apiService.getLogin(params);
    }

    public Single<BaseResult<LoginResponse>> getRegisterUser(HashMap<String , Object> params)  {
        return apiService.getRegisterUser(params);
    }
    public Single<BaseResult<UserInfoResponse>> getUserInfo()  {
        return apiService.getUserInfo();
    }
    public Single<BaseResult<Object>> getLogoutUser()  {
        return apiService.getLogoutUser();
    }
}
