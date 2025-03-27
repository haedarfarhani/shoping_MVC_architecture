package com.heydar.simplemcv.view.interfaces;

import java.util.HashMap;

public interface UserView {
    void loginUser(String name, String email);
    void logoutUser();
    void registerUser(HashMap<String , Object> user);
    void errorUser(String message);
    void getUser(String message);
}
