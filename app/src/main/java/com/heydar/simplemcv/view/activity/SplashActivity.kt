package com.heydar.simplemcv.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.heydar.simplemcv.R
import com.heydar.simplemcv.controller.IUserController
import com.heydar.simplemcv.controller.UserController
import com.heydar.simplemcv.databinding.ActivitySignupBinding
import com.heydar.simplemcv.databinding.ActivitySplashBinding
import com.heydar.simplemcv.model.database.entity.UserEntity
import com.heydar.simplemcv.model.network.responce.BaseResult
import com.heydar.simplemcv.model.network.responce.UserInfoResponse
import com.heydar.simplemcv.utils.AppSharedPref
import com.heydar.simplemcv.view.interfaces.ISplashView

class SplashActivity : AppCompatActivity(), ISplashView {
    private lateinit var controller: IUserController
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = UserController(this)
        controller.getUser()
    }

    override fun getUserInfo(userResponseBaseResult: BaseResult<UserInfoResponse>?, message: String?) {
        if (userResponseBaseResult != null) {
            if (userResponseBaseResult.result?.success == true) {
                if (controller.getSessionTokenFromSharedPref()) {
                    val user = userResponseBaseResult.result.data
                    if (user != null) {
                        controller.saveUserDB(user)
                    }
                }
            } else {
                navigateToLogin()
            }
        }
    }

    override fun getSessionTokenFromSharedPref(hasToken: Boolean?) {

    }

    override fun saveUserDB() {
        navigateToMain()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        controller.onDestroy()
    }
}