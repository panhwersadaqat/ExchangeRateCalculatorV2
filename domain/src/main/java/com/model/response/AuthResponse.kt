package com.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse (
    var access_token: String? = ""
)