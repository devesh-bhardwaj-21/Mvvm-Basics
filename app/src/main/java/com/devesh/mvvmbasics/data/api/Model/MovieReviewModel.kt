package com.devesh.mvvmbasics.data.api.Model

import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import com.google.gson.annotations.SerializedName

data class MovieReviewModel(
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("has_more")
    val hasMore: Boolean = false,
    @SerializedName("results")
    val results: List<ResultsItem>?,
    @SerializedName("num_results")
    val numResults: Int = 0,
    @SerializedName("status")
    val status: String = ""
)