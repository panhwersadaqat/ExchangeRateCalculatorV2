package com.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequest (
    var code :String = "",
    var device_token :String = "",
)
