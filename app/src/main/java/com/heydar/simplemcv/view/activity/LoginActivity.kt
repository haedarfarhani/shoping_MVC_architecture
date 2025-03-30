package com.heydar.simplemcv.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.heydar.simplemcv.controller.IUserController
import com.heydar.simplemcv.controller.UserController
import com.heydar.simplemcv.databinding.ActivityLoginBinding
import com.heydar.simplemcv.model.network.responce.BaseResult
import com.heydar.simplemcv.model.network.responce.LoginResponse
import com.heydar.simplemcv.utils.AppSharedPref
import com.heydar.simplemcv.view.interfaces.ILoginView

class LoginActivity : AppCompatActivity(), ILoginView {

    private lateinit var controller: IUserController
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = UserController(this)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val params : HashMap<String , Any> = HashMap()
            params["email"] = username
            params["password"] = password
            controller.loginUser(params)
        }
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }

    override fun getSessionTokenFromLogin(result: BaseResult<LoginResponse>?, message: String?) {
        if (result?.result?.success == true) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            Log.d("TAG23", "getUserInfo: ${result.result.data?.sessionToken}")
            AppSharedPref.setToken(result.result.data?.sessionToken ?: "")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, message ?: result?.result?.message ?: "An error occurred.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.onDestroy()
    }
}