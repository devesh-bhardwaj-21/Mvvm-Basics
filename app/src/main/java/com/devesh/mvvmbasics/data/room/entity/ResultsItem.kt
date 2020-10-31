package com.devesh.mvvmbasics.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devesh.mvvmbasics.util.movieReviewTableName
import com.google.gson.annotations.SerializedName

@Entity(tableName = movieReviewTableName)
data class ResultsItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded @SerializedName("multimedia") val multimedia: Multimedia,
    @ColumnInfo @SerializedName("date_updated") val dateUpdated: String = "",
    @ColumnInfo @SerializedName("display_title") val displayTitle: String = "",
    @ColumnInfo @SerializedName("publication_date") val publicationDate: String = "",
    @ColumnInfo @SerializedName("summary_short") val summaryShort: String = "",
    @ColumnInfo @SerializedName("critics_pick") val criticsPick: Int = 0,
    @ColumnInfo @SerializedName("byline") val byline: String = "",
    @ColumnInfo @SerializedName("headline") val headline: String = "",
    @ColumnInfo @SerializedName("mpaa_rating") val mpaaRating: String = "",
    @ColumnInfo @SerializedName("opening_date") val openingDate: String? = null
)