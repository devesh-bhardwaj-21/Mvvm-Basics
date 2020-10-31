package com.devesh.mvvmbasics.data.room.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Multimedia(
    @ColumnInfo  @SerializedName("src") val src: String = "",
    @ColumnInfo @SerializedName("width") val width: Int = 0,
    @ColumnInfo @SerializedName("type") val type: String = "",
    @ColumnInfo @SerializedName("height") val height: Int = 0
)