package com.tirmizee.services

import com.jcraft.jsch.ChannelSftp.LsEntry
import org.springframework.integration.file.remote.session.Session
import org.springframework.integration.file.remote.session.SessionFactory
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.*

@Service
class SftpServiceImpl(
    private val sftpSessionFactory: SessionFactory<LsEntry>
) : SftpService {

    override fun uploadFile() {

        val sftpSession: Session<LsEntry> = sftpSessionFactory.session

        try {
            val file = File.createTempFile(UUID.randomUUID().toString(), ".txt")
            sftpSession.write(FileInputStream(file), "/upload/" + file.name)
            println("upload success")
        } catch (e: Exception) {

        } finally {

        }
    }

    override fun downloadFile() {

        val sftpSession: Session<LsEntry> = sftpSessionFactory.session

        try {
            val outputStream = ByteArrayOutputStream()
            sftpSession.read("/upload/hello.txt", outputStream)
            println("download ${String(outputStream.toByteArray())}")
        } catch (e: java.lang.Exception) {

        } finally {

        }
    }

    override fun listEntries() {

        val sftpSession: Session<LsEntry> = sftpSessionFactory.session

        try {
            val entries = sftpSession.list("/upload")
            for (entry in entries){
                println("${entry.filename} ${entry.longname} ${entry.attrs}")
            }
        } catch (e: java.lang.Exception) {

        } finally {

        }
    }

    override fun listNames() {

        val sftpSession: Session<LsEntry> = sftpSessionFactory.session

        try {
            val fileNames = sftpSession.listNames("/upload")
            for (fileName in fileNames){
                println("$fileName ")
            }
        } catch (e: java.lang.Exception) {

        } finally {

        }
    }

}