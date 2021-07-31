package com.example.memes.model

import com.google.gson.annotations.SerializedName

data class MemeResponseData(
    @SerializedName("postLink") val postLink: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("ups") val ups: String?
)
