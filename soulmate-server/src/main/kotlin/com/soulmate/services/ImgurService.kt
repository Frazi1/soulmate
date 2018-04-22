package com.soulmate.services

import com.soulmate.Image
import retrofit2.Call
import retrofit2.http.*

interface ImgurService {

    @POST("upload")
    abstract fun upload(@Query("image") image: String, @Query("album") album: String, @Query("type") type: String,
                        @Query("name") name: String, @Query("title") title: String,
                        @Query("description") description: String): Call<Image>
}