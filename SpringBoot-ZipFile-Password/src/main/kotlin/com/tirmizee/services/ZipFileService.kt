package com.tirmizee.services

import net.lingala.zip4j.io.outputstream.ZipOutputStream
import net.lingala.zip4j.model.ZipParameters
import net.lingala.zip4j.model.enums.AesKeyStrength
import net.lingala.zip4j.model.enums.CompressionLevel
import net.lingala.zip4j.model.enums.CompressionMethod
import net.lingala.zip4j.model.enums.EncryptionMethod
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream


@Service
class ZipFileService {

    suspend fun generateZipBytes() : ByteArray {

        val zipParameters = ZipParameters()
        zipParameters.compressionMethod = CompressionMethod.DEFLATE
        zipParameters.compressionLevel = CompressionLevel.NORMAL
        zipParameters.fileNameInZip = "hello.txt"
        zipParameters.isEncryptFiles = true
        zipParameters.encryptionMethod = EncryptionMethod.AES
        zipParameters.aesKeyStrength = AesKeyStrength.KEY_STRENGTH_256

        val byteOutputStream = ByteArrayOutputStream()
        val zipOutputStream = ZipOutputStream(byteOutputStream, "password".toCharArray())
        zipOutputStream.putNextEntry(zipParameters)
        zipOutputStream.write(fileContentBytes())
        zipOutputStream.flush()
        zipOutputStream.closeEntry()
        zipOutputStream.close()

        return byteOutputStream.toByteArray()
    }

    private fun fileContentBytes() =
        "hello world".toByteArray()

}


