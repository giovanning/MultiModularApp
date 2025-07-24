package com.projetos.filmei.login.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val fullName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("photo")
    val photo: String?,
)
