package com.heydar.simplemcv.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.heydar.simplemcv.controller.UserController
import com.heydar.simplemcv.databinding.ActivitySignupBinding
import com.heydar.simplemcv.model.network.responce.BaseResult
import com.heydar.simplemcv.model.network.responce.LoginResponse
import com.heydar.simplemcv.view.interfaces.ISignupView

class SignupActivity : AppCompatActivity() , ISignupView {
    private lateinit var controller: UserController
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = UserController(this)

        binding.btnRegistry.setOnClickListener {
            val fullname = binding.etFullName.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val params : HashMap<String , Any> = HashMap()
            params["username"] = username
            params["password"] = password
            params["email"] = email
            params["fullname"] = fullname
            controller.registerUser(params)
        }
    }

    override fun getRegisterUser(result: BaseResult<LoginResponse>?, message: String?) {
        if (result?.result?.success == true) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {

            Toast.makeText(this, message ?: result?.result?.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.onDestroy()
    }
}