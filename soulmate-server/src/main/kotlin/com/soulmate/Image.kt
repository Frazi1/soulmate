package com.soulmate

import com.google.gson.annotations.SerializedName

class Image {

    val id: String? = null
    val title: String? = null
    val description: String? = null
    @SerializedName("datetime")
    val dateTime: Long = 0
    val type: String? = null
    val isAnimated: Boolean = false
    val width: Int = 0
    val height: Int = 0
    val size: Int = 0
    val views: Int = 0
    val bandwidth: Long = 0
    @SerializedName("deletehash")
    val deleteHash: String? = null
    val name: String? = null
    val section: String? = null
    val link: String? = null
    val gifv: String? = null
    val mp4: String? = null
    val webm: String? = null
    val isLooping: Boolean = false
    val isFavorite: Boolean = false
    val isNsfw: Boolean = false
    val vote: String? = null

    override fun toString(): String {
        return "Image{" +
                "id='" + id + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", dateTime=" + dateTime +
                ", type='" + type + '\''.toString() +
                ", animated=" + isAnimated +
                ", width=" + width +
                ", height=" + height +
                ", size=" + size +
                ", views=" + views +
                ", bandwidth=" + bandwidth +
                ", deleteHash='" + deleteHash + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", section='" + section + '\''.toString() +
                ", link='" + link + '\''.toString() +
                ", gifv='" + gifv + '\''.toString() +
                ", mp4='" + mp4 + '\''.toString() +
                ", webm='" + webm + '\''.toString() +
                ", looping=" + isLooping +
                ", favorite=" + isFavorite +
                ", nsfw=" + isNsfw +
                ", vote='" + vote + '\''.toString() +
                '}'.toString()
    }
}
