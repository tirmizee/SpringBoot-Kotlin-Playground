package com.tirmizee

import com.tirmizee.properties.SftpProperty
import com.tirmizee.services.SftpServiceImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootSftpSharedSessionApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootSftpSharedSessionApplication>(*args)
	val sftpProperty = application.getBean(SftpProperty::class.java)
	val sftpService = application.getBean(SftpServiceImpl::class.java)
	println(sftpProperty)
	sftpService.uploadFile()
	sftpService.uploadFile()
	sftpService.downloadFile()
	sftpService.listEntries()
	sftpService.listNames()
}
