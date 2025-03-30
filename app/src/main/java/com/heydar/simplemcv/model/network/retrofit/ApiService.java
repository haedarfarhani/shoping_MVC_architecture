package com.heydar.simplemcv.model.network.retrofit;

import com.heydar.simplemcv.model.network.responce.BaseResult;
import com.heydar.simplemcv.model.network.responce.LoginResponse;
import com.heydar.simplemcv.model.network.responce.UserInfoResponse;
import java.util.HashMap;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("functions/loginWithCredentials")
    Single<BaseResult<LoginResponse>> getLogin(@Body HashMap<String , Object> params);

    @POST("functions/registerUser")
    Single<BaseResult<LoginResponse>> getRegisterUser(@Body HashMap<String , Object> params);

    @POST("functions/getUserInfo")
    Single<BaseResult<UserInfoResponse>> getUserInfo();

    @POST("functions/logoutUser")
    Single<BaseResult<Object>> getLogoutUser();
}
