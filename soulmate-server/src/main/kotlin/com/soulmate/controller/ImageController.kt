package com.soulmate.controller

import com.soulmate.mapping.toProfileImage
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.ImageService
import com.sun.deploy.net.HttpUtils
import dtos.ProfileImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
//import org.json.JSONException

import java.net.HttpURLConnection.HTTP_OK
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.io.OutputStreamWriter
import com.sun.imageio.plugins.common.ImageUtil
import retrofit2.Retrofit
import java.io.IOException

//import org.junit.experimental.results.ResultMatchers.isSuccessful
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import java.io.InputStreamReader
import java.io.BufferedReader
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import okhttp3.*
import org.hibernate.validator.constraints.URL


import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import org.json.JSONObject


@RestController
@RequestMapping(value = ["/image"])
class ImageController {

    @Autowired
    lateinit var imageService: ImageService

    private val IMGUR_CLIENT_ID = "8f9c692c6167351"
    private val clientSecret = "29b4c8181216e4c4e209bf309d4993d56939e66f"

    @PostMapping
    fun uploadProfileImage(authentication: Authentication, @RequestBody profileImageDto: ProfileImageDto) {
        val profileImage = profileImageDto.toProfileImage()
        val memberDetails = authentication.principal as MemberDetails
        profileImage.userAccount = memberDetails.member.userAccount
        imageService.save(profileImage)
    }


}