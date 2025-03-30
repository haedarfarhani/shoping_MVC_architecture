package com.heydar.simplemcv.model.network.responce

import com.google.gson.annotations.SerializedName


data class BaseResult<T>(
    @field:SerializedName("result")
    val result: Result<T>? = null,
)


data class Result<T>(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: T? = null,
    @field:SerializedName("errorCode")
    val errorCode: Int? = null
)