package com.heydar.simplemcv.controller;

import android.util.Log;

import com.heydar.simplemcv.MyApplication;
import com.heydar.simplemcv.model.database.AppDatabase;
import com.heydar.simplemcv.model.database.entity.UserEntity;
import com.heydar.simplemcv.model.network.responce.UserInfoResponse;
import com.heydar.simplemcv.model.repository.UserRepository;
import com.heydar.simplemcv.utils.AppSharedPref;
import com.heydar.simplemcv.view.interfaces.ILoginView;
import com.heydar.simplemcv.view.interfaces.ISignupView;
import com.heydar.simplemcv.view.interfaces.ISplashView;
import com.heydar.simplemcv.view.interfaces.IUserBaseView;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserController implements IUserController {
    private final UserRepository repository = new UserRepository();
    private final CompositeDisposable disposable = new CompositeDisposable();
    private ILoginView iLoginView;
    private ISignupView iSignupView;
    private ISplashView iSplashView;
//    private IUserBaseView iUserBaseView;

//    public UserController(IUserBaseView iUserBaseView) {
//        this.iUserBaseView = iUserBaseView;
//    }

    public UserController(ISplashView iSplashView) {
        this.iSplashView = iSplashView;
    }

    public UserController(ISignupView iSignupView) {
        this.iSignupView = iSignupView;
    }

    public UserController(ILoginView userView) {
        this.iLoginView = userView;
    }

    @Override
    public void loginUser(HashMap<String, Object> params) {
        disposable.add(repository.getLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loginResponseBaseResult -> iLoginView.getSessionTokenFromLogin(loginResponseBaseResult, null),
                        throwable -> iLoginView.getSessionTokenFromLogin(null, throwable.getMessage())
                ));
    }

    @Override
    public void logoutUser() {
//        disposable.add(
//                repository.getLogoutUser()
//                        .subscribeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                loginResponseBaseResult -> userView.getSessionTokenFromLogin(),
//                                throwable -> userView.onError(throwable.getMessage())
//                        )
//        );
    }

    @Override
    public void registerUser(HashMap<String, Object> params) {
        disposable.add(repository.getRegisterUser(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loginResponseBaseResult -> {
                            if (iSignupView != null) iSignupView.getRegisterUser(loginResponseBaseResult, null);
                        },
                        throwable -> {
                            if (iSignupView != null) iSignupView.getRegisterUser(null, throwable.getMessage());
                        }
                ));
    }

    @Override
    public void getUser() {
        disposable.add(repository.getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loginResponseBaseResult -> {
                            if (iSplashView != null) {
                                iSplashView.getUserInfo(loginResponseBaseResult, null);
                            }
                        },
                        throwable -> {
                            if (iSplashView != null) {
                                iSplashView.getUserInfo(null, throwable.getMessage());
                            }
                        }
                ));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }

    @Override
    public Boolean getSessionTokenFromSharedPref() {
        Boolean hasToken = AppSharedPref.getToken() != null || !AppSharedPref.getToken().isEmpty();
        iSplashView.getSessionTokenFromSharedPref(hasToken);
        return hasToken;
    }

    @Override
    public void saveUserDB(UserInfoResponse user) {
        String userId = user.getUserId() != null ? user.getUserId() : "";
        UserEntity userEntity =  new UserEntity(userId, user.getUsername(), user.getEmail(),  user.getProfilePicture());
        disposable.add(MyApplication.appDatabase.userDao().insert(userEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        () -> iSplashView.saveUserDB(),
                        throwable -> Log.d("TAG", "saveUserDB: " + throwable.getMessage())
                ));
    }
}
