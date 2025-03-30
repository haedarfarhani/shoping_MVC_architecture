package com.heydar.simplemcv.view.interfaces;

import com.heydar.simplemcv.model.network.responce.BaseResult;
import com.heydar.simplemcv.model.network.responce.UserInfoResponse;

public interface IUserBaseView {
    void getUserInfo(BaseResult<UserInfoResponse> userResponseBaseResult, String message);
}
