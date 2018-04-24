package com.soulmate.services.helpers

import org.imgscalr.Scalr
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class ImageCompressor {

    companion object {
        fun resize(byteImage: ByteArray, size: Int): ByteArray {
            val image: BufferedImage = ImageIO.read(byteImage.inputStream())
            val resizedImage: BufferedImage = Scalr.resize(image, size)
            val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()
            ImageIO.write(resizedImage, "jpg", outputStream)
            return outputStream.toByteArray()
        }
    }
}