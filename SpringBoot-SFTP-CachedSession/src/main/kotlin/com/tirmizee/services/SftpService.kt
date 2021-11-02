package com.tirmizee.services

interface SftpService {

    fun uploadFile()

    fun downloadFile()

    fun listEntries()

    fun listNames()

}