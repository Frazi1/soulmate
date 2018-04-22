package com.soulmate

import com.soulmate.services.ImgurService
import com.soulmate.Image

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.imageio.ImageIO

class ImgurAPI
(
        private val clientId: String,

        private val clientSecret: String) {

    private val retrofit: Retrofit

    private val imgurService: ImgurService

    init {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val headersBuilder = request.headers().newBuilder()
            //if (accessToken == null)
                headersBuilder.add("Authorization", "Client-ID $clientId")
            //else
            //    headersBuilder.add("Authorization", "Bearer " + accessToken!!.getToken())
            chain.proceed(request.newBuilder().headers(headersBuilder.build()).build())
        }.build()
        retrofit = Retrofit.Builder().baseUrl("https://api.imgur.com/3/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()

        imgurService = retrofit.create(ImgurService::class.java!!)
    }


    @Throws(IOException::class)
    //@JvmOverloads
    fun uploadImage(inputStream: InputStream, album: String, type: String,
                    name: String, title: String, description: String): Image {
        val output = ByteArrayOutputStream(inputStream.available())
        val image = ImageIO.read(inputStream)
        ImageIO.write(image, "png", output)
        return uploadImage(Base64.getEncoder().encodeToString(output.toByteArray()), album, type, name, title,
                description)
    }


    @Throws(IOException::class)
    //@JvmOverloads
    fun uploadImage(image: String, album: String, type: String, name: String,
                    title: String, description: String): Image {
        return call(imgurService.upload(image, album, type, name, title, description))
    }

    /**
     * Gets the data of the specified call and handles any errors which may occur.
     *
     * @param call The call.
     * @param <T>  The data type.
     * @return The data.
     * @throws IOException If the call fails.
    </T> */
    @Throws(IOException::class)
    private fun <T> call(call: Call<T>): T {
        val response = call.execute()
        if (!response.isSuccessful)
            throw IOException("Request was unsuccessful with status: " + response.code()
                    + " and message: " + response.message())
        return response.body()!!//.getData()
    }
}
