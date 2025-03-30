package com.heydar.simplemcv.model.network.responce

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("sessionToken")
	val sessionToken: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
